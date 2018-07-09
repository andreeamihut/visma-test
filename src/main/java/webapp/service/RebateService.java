package webapp.service;

import entities.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;
import repositories.CustomerRepository;
import repositories.RebateRepository;

import java.util.List;

@RestController
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan("entities")
public class RebateService {

    @Autowired
    RebateRepository repository;
    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping(value = "/rebate",  method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    List<Rebate> getAllRebates() {
        return repository.findAll();
    }

    @RequestMapping(value = "/rebate/{rebateId}",  method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    Rebate getRebateById(@PathVariable Integer rebateId) {
        return repository.findById(rebateId).get();
    }

    @Bean
    InitializingBean initRebateDatabase() {
        return () -> {
            Customer customer = customerRepository.findById(1).get();
            Customer customer2 = customerRepository.findById(2).get();
            repository.save(new FinalSaleRebate(customer));
            repository.save(new SpecialOfferRebate(customer));
            repository.save(new VolumeRebate(customer2));
        };
    }
}
