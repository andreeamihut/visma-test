package webapp.response;

import entities.Product;

import java.io.Serializable;

public class PriceResponse implements Serializable {

    private Product product;

    private CustomerResponse customer;

    private Double finalPrice;

    public PriceResponse() {
    }

    public PriceResponse(Product product, CustomerResponse customer, Double finalPrice) {
        this.product = product;
        this.customer = customer;
        this.finalPrice = finalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CustomerResponse getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponse customer) {
        this.customer = customer;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
