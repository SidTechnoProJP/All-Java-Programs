package InsuranceDatabases.Repository;

import InsuranceDatabases.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Integer> {
    List<Person> findByPersonName(String  personName);
}
