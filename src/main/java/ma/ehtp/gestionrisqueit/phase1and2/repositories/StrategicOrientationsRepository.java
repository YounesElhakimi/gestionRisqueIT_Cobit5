package ma.ehtp.gestionrisqueit.repositories;

import ma.ehtp.gestionrisqueit.modelles.StrategicOrientations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StrategicOrientationsRepository extends JpaRepository<StrategicOrientations , Long> {
}
