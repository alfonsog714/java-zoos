package local.alfonso.zoo.repos;

import local.alfonso.zoo.model.Zoo;
import org.springframework.data.repository.CrudRepository;

public interface ZooRepository extends CrudRepository<Zoo, Long> {
}
