package md.maib.comparator.services;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class XLSXFileAggregator {
    private static final String ID_COLUMN_NAME = "Id Client T24";
    private static final String CASHBACK_COLUMN_NAME = "Total Cashback";
    private int indexNumberId = -1;
    private int indexNumberCashback = -1;
    private final Map<String, Long> result = new HashMap<>();

    public Map<String, Long> group(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        Workbook workbook = StreamingReader.builder().rowCacheSize(100)
                .bufferSize(4096)
                .open(inputStream);
        for (Sheet sheet : workbook) {
            for (Row row : sheet) {
                if (row.getCell(0).getStringCellValue().equalsIgnoreCase("Data decontarii")) {
                    indexNumberId = defineColumnNumber(ID_COLUMN_NAME, row);
                    indexNumberCashback = defineColumnNumber(CASHBACK_COLUMN_NAME, row);
                } else {
                    Long sumValue = result.get(row.getCell(indexNumberId).getStringCellValue());
                    if (Objects.nonNull(sumValue)) {
                        result.put(row.getCell(indexNumberId).getStringCellValue(),
                               sumValue +  Long.parseLong(row.getCell(indexNumberCashback).getStringCellValue()));
                    } else {
                        result.put(row.getCell(indexNumberId).getStringCellValue(),
                                Long.parseLong(row.getCell(indexNumberCashback).getStringCellValue()));
                    }
                }
            }
        }
        inputStream.close();
        return result;
    }

    private int defineColumnNumber(String name, Row row) {
        for (Cell cell : row) {
            if (cell.getStringCellValue().equalsIgnoreCase(name)) {
                return cell.getColumnIndex();
            }
        }
        return -1; //Not found
    }
}
