package entities;

public class ImportedProducts  extends Product{

    private Double customsFee;

    public ImportedProducts() {
    }

    public ImportedProducts(String name, Double price, Double customsFee) {
        super(name, price);
        this.customsFee = customsFee;
    }

    public Double getCustomsFee() {
        return customsFee;
    }

    public void setCustomsFee(Double customsFee) {
        this.customsFee = customsFee;
    }

    @Override
    public String priceTag() {
        return getName()
                + " $ "
                + totalPrice()
                + " (Customs fee: $ "
                + getCustomsFee()
                + ")";
    }

    public Double totalPrice() {
        return getPrice() + customsFee;
    }
}
