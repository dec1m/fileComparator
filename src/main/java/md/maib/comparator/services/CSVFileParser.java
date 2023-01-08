package md.maib.comparator.services;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import md.maib.comparator.model.CsvRow;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVFileParser {
    private final Map<String, Long> result = new HashMap<>();

    public Map<String, Long> parse(File file) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.builder()
                .addColumn("id")
                .addColumn("surname")
                .addColumn("name")
                .addColumn("unknownNumber")
                .addColumn("sumDeposit")
                .addColumn("currency")
                .build().
                withColumnSeparator('#');
        MappingIterator<CsvRow> it = mapper.readerFor(CsvRow.class)
                .with(schema)
                .readValues(file);
        while (it.hasNext()) {
            CsvRow line = it.next();
            result.put(line.getId(), line.getSumDeposit());
        }
        return result;
    }
}
