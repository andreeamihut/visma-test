package webapp.service;

import entities.Customer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;
import repositories.CustomerRepository;
import webapp.response.CustomerResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan("entities")
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    @RequestMapping(value = "/customer",  method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    List<CustomerResponse> getAllCustomers() {
        return repository.findAll().stream().map(CustomerResponse::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/customer/byId/{customerId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    CustomerResponse getCustomerById(@PathVariable Integer customerId) {
        return new CustomerResponse(repository.findById(customerId).get());
    }

    @RequestMapping(value = "/customer/byName/{customerName}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    CustomerResponse getCustomerByName(@PathVariable String customerName) {
        return new CustomerResponse(repository.findByName(customerName));
    }

    @Bean
    InitializingBean initCustomerDatabase() {
        return () -> {
            repository.save(new Customer("IKEA"));
            repository.save(new Customer("MobExpert"));
        };
    }
}
