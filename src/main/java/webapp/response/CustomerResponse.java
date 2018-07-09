package webapp.response;

import entities.Customer;
import entities.Rebate;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerResponse implements Serializable {
    private Integer id;

    private String name;

    private List<String> rebates;

    public CustomerResponse() {
    }

    public CustomerResponse(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.rebates = customer.getRebateList().stream().map(Rebate::getName).collect(Collectors.toList());
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

    public List<String> getRebates() {
        return rebates;
    }

    public void setRebates(List<String> rebates) {
        this.rebates = rebates;
    }
}
