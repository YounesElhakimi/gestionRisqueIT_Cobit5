package ma.ehtp.gestionrisqueit.repositories;

import ma.ehtp.gestionrisqueit.modelles.ITRiskElement;
import ma.ehtp.gestionrisqueit.modelles.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITRiskElementRepository extends JpaRepository<ITRiskElement, Long> {

    public List<ITRiskElement> findByOrganization(Organization organization);

}
