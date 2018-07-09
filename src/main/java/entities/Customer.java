package entities;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Rebate> rebateList;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rebate> getRebateList() {
        return rebateList;
    }

    public void setRebateList(List<Rebate> rebateList) {
        this.rebateList = rebateList;
    }

    public double calculatePrice(Product product, Integer amount) {
        Optional<Double> optional = rebateList.stream().map(r -> r.calculate(product, amount)).min(Comparator.comparing(Double::valueOf));
        return optional.isPresent() ? optional.get() : Double.MAX_VALUE;
    }

    @Override
    public String toString() {
        return String.format("Customer [id=%s, name=%s, rebates=%s]", id, name, rebateList);
    }
}
