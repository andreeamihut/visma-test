package entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Rebate_Type")
public abstract class Rebate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    public Rebate() {
    }

    public Rebate(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public abstract double calculate(Product product, Integer amount);

    public abstract String getName();

    @Override
    public String toString() {
        return String.format("Rebate [id=%s, name=%s, customer=%s]", id, this.getClass().getName(), customer.getName());
    }

}
