package md.maib.comparator.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonPropertyOrder({ "id", "sumDeposit", "customSumDeposit" })
public class Result {
    private String id;
    private long sumDeposit;
    private long customSumDeposit;


    public Result(String id, long sumDeposit, long customSumDeposit) {
        this.id = id;
        this.sumDeposit = sumDeposit;
        this.customSumDeposit = customSumDeposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return sumDeposit == result.sumDeposit && customSumDeposit == result.customSumDeposit && Objects.equals(id, result.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sumDeposit, customSumDeposit);
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

    public long getCustomSumDeposit() {
        return customSumDeposit;
    }

    public void setCustomSumDeposit(long customSumDeposit) {
        this.customSumDeposit = customSumDeposit;
    }
}
