package helper;

public class SourcePojo {
    private String name;

    private double amount;


    public SourcePojo(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }



    public String getName() {
        return name;
    }



    public SourcePojo setName(String name) {
        this.name = name;
        return this;
    }



    public double getAmount() {
        return amount;
    }



    public SourcePojo setAmount(double amount) {
        this.amount = amount;
        return this;
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SourcePojo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
