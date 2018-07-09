package repositories;

import entities.Customer;
import entities.Rebate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RebateRepository extends CrudRepository<Rebate, Integer> {
    Optional<Rebate> findById(Integer id);
    Optional<Rebate> findByCustomer(Customer customer);
    List<Rebate> findAll();
}
