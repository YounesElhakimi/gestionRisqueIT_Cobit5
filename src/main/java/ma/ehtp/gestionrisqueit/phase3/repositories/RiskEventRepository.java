package ma.ehtp.gestionrisqueit.phase3.repositories;

import ma.ehtp.gestionrisqueit.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RiskMappingRepositoryRepository extends JpaRepository<RiskMapping , Long> {

    public List<RiskMapping> findByOrganization(Organization organization);
}
