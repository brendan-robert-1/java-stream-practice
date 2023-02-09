package helper;

import java.math.BigDecimal;

public class BigDecimalSource {
    private String name;

    public BigDecimalSource(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    private BigDecimal amount;

    @Override
    public String toString() {
        return "BigDecimalSource{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
