package ecf_spring.repository;
import ecf_spring.entity.Partie;
import ecf_spring.entity.Tournoi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartieRepository extends CrudRepository<Partie,Integer> {
    boolean existsByTournoiAndUser1IdAndUser2Id(Tournoi tournoi, int user1Id, int user2Id);
    Optional<Object> findById(Partie id);
}
