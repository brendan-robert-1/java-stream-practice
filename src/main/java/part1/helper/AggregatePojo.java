package part1.helper;

import java.math.BigDecimal;
import java.util.List;

public class AggregatePojo {
        private BigDecimal total;
    private List<BigDecimalSource> sources;

    public AggregatePojo(BigDecimal total, List<BigDecimalSource> sources) {
        this.total = total;
        this.sources = sources;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<BigDecimalSource> getSources() {
        return sources;
    }

    public void setSources(List<BigDecimalSource> sources) {
        this.sources = sources;
    }
}
