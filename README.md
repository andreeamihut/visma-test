# visma-test

The application provides 3 types of endpoints:

http://95.77.22.141:8080/customer - returns all customers from database 
      + /byId/{customerId} - returns customer by Id (now available Ids: 1 and 2)
      + /byName/{customerName} - returns customer by Name (now available IKEA and MobExpert)
http://95.77.22.141:8080/product - returns all products
      + /{productId} - returns product by Id (now available 3 and 4)
      + /price/{productId}/{customerName}/{amount} - calculates the final price of the product afetr aplying the rebates
http://95.77.22.141:8080/rebates - returns all types of rebates

Project structure:

  - entities: there are 3 types of entities: Customer, Product and Rebate, which are stored in a "in-memory" database called H2. 
              Rebate entity is an abstract class extended by each particular entity, all types of rebates being mapped on the same H2     
              table. The database schema is found in /resources/schema.sql.
  - repository: for each type of entity there is a repository interface extending CrudRepository<<EntityType>, Integer> which implements 
              the 2 most common methods: findAll() and findById()
  - service: there are 3 types of rest controllers where the endpoints for each entity are implemented: CustomerService, ProductService              and RebateService. This is where the repository is called and then the service responses is in JSON format. Also, here 
             is the place where the static data is saved in database using 
             @Bean
             InitializingBean init<EntityType>Database()
  - spring boot application: the configuration for the spring-boot application is done in OnlineStoreServiceInitializer class, found in                 /webapp/config package. The spring boot application is starting a tomcat server and a hikari pool conection. After       
                performing mvn install command, a jar file is generated which can be the run in a Linux machine with java -jar command.
  - maven dependencies are defined in pom.xml
              <dependency>
                  <groupId>com.h2database</groupId>
                  <artifactId>h2</artifactId>
                  <scope>runtime</scope>
              </dependency>
 
Points of extension:
  - Rebate types: currently, there are 3 types of rebates: Volume, SpecialOffer and FinalSale. To add another type of rebate, just  
                  define another class extending abstract class Rebate and implement public abstract double calculate(Product product, 
                  Integer amount);
  - Customer: this is where the final price is calculated from the rebates list. For the current customer implementation, all rebates 
              are calculated on the whole price and then the final price is the minimum of all the rebates. If another type of final  
              price wants to be added, then the same pattern used in Rebates should be implemented in Customer: define Customer as an 
              abstract class with public double calculatePrice(Product product, Integer amount) defined as abstract and extend a new  
              class for each implementation of calculatePrice.
              
 Not present in current implementation:
   - unit testing;
   - checks for Optional.isPresent() each time Optional.get() is used;
   - put methods for inserting new data in database.
         
