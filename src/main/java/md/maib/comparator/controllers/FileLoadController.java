package md.maib.comparator.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import md.maib.comparator.model.Result;
import md.maib.comparator.services.CSVFileParser;
import md.maib.comparator.services.CsvResultWriter;
import md.maib.comparator.services.XLSXFileAggregator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FileLoadController {
    @FXML
    private Button fileOpenButtonA;
    @FXML
    private Button fileOpenButtonB;
    @FXML
    private Button compareButton;

    private Map<String, Long> customDepositSum;
    private Map<String, Long> depositSum;


    @FXML
    protected void onOpenFileA() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.xlsx"));
        File file = fc.showOpenDialog(null);
        if (Objects.nonNull(file)) {
            XLSXFileAggregator fileAggregator = new XLSXFileAggregator();
            try {
                customDepositSum = fileAggregator.group(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    protected void onOpenFileB() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.csv"));
        File file = fc.showOpenDialog(null);
        if (Objects.nonNull(file)) {
            CSVFileParser csvFileParser = new CSVFileParser();
            try {
                depositSum = csvFileParser.parse(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    protected void onProcess() {
        List<Result> resultList = new ArrayList<>();
        CsvResultWriter csvResultWriter = new CsvResultWriter();
        if (Objects.nonNull(depositSum) && Objects.nonNull(customDepositSum)) {
            for (Map.Entry<String, Long> stringLongEntry : customDepositSum.entrySet()) {
                String depositId = stringLongEntry.getKey();
                Long customSumDeposit = stringLongEntry.getValue();
                Long sumDeposit = this.depositSum.get(depositId);
                if (!sumDeposit.equals(customSumDeposit)) {
                    resultList.add(new Result(depositId, sumDeposit, customSumDeposit));
                }
            }
        }
        if (!resultList.isEmpty()) {
            csvResultWriter.write(resultList);
        }
    }
}