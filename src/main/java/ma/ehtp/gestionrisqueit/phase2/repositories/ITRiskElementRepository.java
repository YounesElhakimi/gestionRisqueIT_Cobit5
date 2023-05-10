package ma.ehtp.gestionrisqueit.phase2.repositories;

import ma.ehtp.gestionrisqueit.phase2.modelles.ITRiskElement;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase2.modelles.ITRiskElementSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ITRiskElementRepository extends JpaRepository<ITRiskElement, Long> {

    public List<ITRiskElement> findByOrganization(Organization organization);
    public List<ITRiskElement> findBySource(ITRiskElementSource source);


}
