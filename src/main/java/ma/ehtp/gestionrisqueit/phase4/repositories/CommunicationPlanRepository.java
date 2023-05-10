package ma.ehtp.gestionrisqueit.phase4.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase4.modelles.CommunicationPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommunicationPlanRepository extends JpaRepository<CommunicationPlan , Long> {
    public List<CommunicationPlan> findByOrganization(Organization organization);
}
