package local.alfonso.zoo.repos;

import local.alfonso.zoo.model.Zoo;
import local.alfonso.zoo.views.CountAnimalsInZoo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ZooRepository extends CrudRepository<Zoo, Long> {
    @Query(value = "SELECT a.zooid, z.zooname, count(a.animalid) as countanimals FROM zooanimals a INNER JOIN zoo z ON a.zooid = z.zooid GROUP BY a.zooid, z.zooname", nativeQuery = true)
    ArrayList<CountAnimalsInZoo> getCountAnimalsInZoo();

    @Modifying
    @Query(value = "DELETE FROM zooanimals WHERE zooid = :zooid", nativeQuery = true)
    void deleteZooFromZooanimals(long zooid);
}
