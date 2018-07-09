package entities;

import javax.persistence.Entity;

@Entity(name = "VolumeRebate")
public class VolumeRebate extends Rebate {
    private static final int MIN_FOR_DISCOUNT = 100;
    private static Integer DISCOUNT = 10;

    public VolumeRebate() {
        super();
    }

    public VolumeRebate(Customer customer) {
        super(customer);
    }

    @Override
    public double calculate(Product product, Integer amount) {
        if (amount <= MIN_FOR_DISCOUNT) {
            return product.getPrice();
        }
        return product.getPrice() * (100 - DISCOUNT) / 100;
    }

    @Override
    public String getName() {
        return "VolumeRebate";
    }
}
