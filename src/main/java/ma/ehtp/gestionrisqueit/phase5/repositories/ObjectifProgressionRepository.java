package ma.ehtp.gestionrisqueit.phase5.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase5.modelles.ObjectifProgression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ObjectifProgressionRepository extends JpaRepository<ObjectifProgression,Long> {
public List<ObjectifProgression> findByOrganization(Organization organization);

}
