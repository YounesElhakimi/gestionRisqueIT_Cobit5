package ma.ehtp.gestionrisqueit.phase1.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.Objective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ObjectiveRepository extends JpaRepository<Objective , Long> {

    public Objective findByOrganization(Organization organization);

}
