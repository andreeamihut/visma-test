package webapp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import webapp.service.CustomerService;
import webapp.service.ProductService;
import webapp.service.RebateService;


@SpringBootApplication
@EnableAutoConfiguration
@Import({CustomerService.class, ProductService.class, RebateService.class})
public class OnlineStoreServiceInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreServiceInitializer.class, args);
    }

}
