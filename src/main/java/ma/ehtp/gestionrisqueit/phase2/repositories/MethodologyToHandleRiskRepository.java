package ma.ehtp.gestionrisqueit.phase2.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase2.modelles.MethodologyToHandleRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MethodologyToHandleRiskRepository extends JpaRepository<MethodologyToHandleRisk , Long> {
public MethodologyToHandleRisk findByOrganization(Organization organization);
}
