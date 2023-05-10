package ma.ehtp.gestionrisqueit.phase1.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.StrategicOrientations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StrategicOrientationsRepository extends JpaRepository<StrategicOrientations , Long> {
    public List<StrategicOrientations> findAllByOrganization(Organization organization);
}
