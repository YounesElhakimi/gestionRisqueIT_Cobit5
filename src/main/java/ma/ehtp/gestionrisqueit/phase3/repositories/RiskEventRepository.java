package ma.ehtp.gestionrisqueit.phase3.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RiskEventRepository extends JpaRepository<RiskEvent, Long> {

    public List<RiskEvent> findByOrganization(Organization organization);
    public List<RiskEvent> findByOrganizationOrderByImpacteMADDesc(Organization organization);
}
