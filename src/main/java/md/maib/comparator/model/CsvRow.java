package md.maib.comparator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CsvRow {
    private String id;
    private long sumDeposit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CsvRow csvRow = (CsvRow) o;
        return sumDeposit == csvRow.sumDeposit && Objects.equals(id, csvRow.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sumDeposit);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSumDeposit() {
        return sumDeposit;
    }

    public void setSumDeposit(long sumDeposit) {
        this.sumDeposit = sumDeposit;
    }
}

