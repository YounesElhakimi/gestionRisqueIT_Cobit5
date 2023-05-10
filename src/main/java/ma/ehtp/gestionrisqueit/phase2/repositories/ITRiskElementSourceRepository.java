package ma.ehtp.gestionrisqueit.phase2.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase2.modelles.ITRiskElementSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ITRiskElementSourceRepository extends JpaRepository<ITRiskElementSource , Long> {
    public List<ITRiskElementSource> findByOrganization(Organization organization);

}
