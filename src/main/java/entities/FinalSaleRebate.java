package entities;

import javax.persistence.Entity;

@Entity(name = "FinalSaleRebate")
public class FinalSaleRebate extends Rebate {
    private static Integer DISCOUNT = 25;

    public FinalSaleRebate() {
        super();
    }

    public FinalSaleRebate(Customer customer) {
        super(customer);
    }

    @Override
    public double calculate(Product product, Integer amount) {
        return product.getPrice() * (100 - DISCOUNT) / 100;
    }

    @Override
    public String getName() {
        return "FinalSaleRebate";
    }
}
