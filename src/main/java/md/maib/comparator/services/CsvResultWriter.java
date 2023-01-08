package md.maib.comparator.services;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import md.maib.comparator.model.Result;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CsvResultWriter {
    final CsvMapper csvMapper = new CsvMapper();
    public void write(List<Result> resultList) {
        ObjectWriter objectWriter = csvMapper.writerWithSchemaFor(Result.class);
        SequenceWriter sequenceWriter = null;
        try {
            sequenceWriter = objectWriter.writeValues(new File("./result.csv"));
            sequenceWriter.writeAll(resultList);
            sequenceWriter.flush();
            sequenceWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
