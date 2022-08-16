package ma.ehtp.gestionrisqueit.repositories;

import ma.ehtp.gestionrisqueit.modelles.Organization;
import ma.ehtp.gestionrisqueit.modelles.PotentialRisksOfTheCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PotentialRisksOfTheCompanyRepository extends JpaRepository<PotentialRisksOfTheCompany, Long> {

    public List<PotentialRisksOfTheCompany> findAByOrganization(Organization organization);

}
