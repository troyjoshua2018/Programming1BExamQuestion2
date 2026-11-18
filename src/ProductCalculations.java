public class ProductCalculations implements IProductCalculations {
    private static final double VAT_RATE = 0.15;
    private static final double COMMISSION_RATE = 0.05;
    private final double price;

    public ProductCalculations(double price) {
        this.price = price;
    }

    @Override
    public double GetVat() {
        return price * VAT_RATE;
    }

    @Override
    public double GetCommission() {
        return price * COMMISSION_RATE;
    }

    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }
}