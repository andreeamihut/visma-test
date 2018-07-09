package webapp.service;

import entities.Customer;
import entities.Product;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;
import repositories.CustomerRepository;
import repositories.ProductRepository;
import webapp.response.CustomerResponse;
import webapp.response.PriceResponse;

import java.util.List;

@RestController
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan("entities")
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/product",  method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    List<Product> getAllProducts() {
        return repository.findAll();
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    Product getProductById(@PathVariable Integer productId) {
        return repository.findById(productId).get();
    }

    @RequestMapping(value = "/product/price/{productId}/{customerName}/{amount}", produces = "application/json")
    @ResponseBody
    PriceResponse getPriceForCustomer(@PathVariable Integer productId, @PathVariable String customerName, @PathVariable Integer amount){
        Product product = repository.findById(productId).get();
        Customer customer = customerRepository.findByName(customerName);
        double price = customer.calculatePrice(product, amount);

        return new PriceResponse(product, new CustomerResponse(customer), price);
    }

    @Bean
    InitializingBean initProductDatabase() {
        return () -> {
            repository.save(new Product("Chair", 100));
            repository.save(new Product("Table", 250));
        };
    }
}
