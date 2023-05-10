package ma.ehtp.gestionrisqueit.phase2.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase2.modelles.PotentialRisksOfTheCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PotentialRisksOfTheCompanyRepository extends JpaRepository<PotentialRisksOfTheCompany, Long> {

    public List<PotentialRisksOfTheCompany> findAByOrganization(Organization organization);
    public void deleteAllByOrganization(Organization organization);

}
