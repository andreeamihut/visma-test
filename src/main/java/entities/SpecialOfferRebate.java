package entities;

import javax.persistence.Entity;

@Entity(name = "SpecialOfferRebate")
public class SpecialOfferRebate extends Rebate{

    public SpecialOfferRebate() {
        super();
    }

    public SpecialOfferRebate(Customer customer) {
        super(customer);
    }

    @Override
    public double calculate(Product product, Integer amount) {
        return 10;
    }

    @Override
    public String getName() {
        return "SpecialOfferRebate";
    }
}
