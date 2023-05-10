package ma.ehtp.gestionrisqueit.phase1.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.ImplementationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImplementationInfoRepository extends JpaRepository<ImplementationInfo , Long> {

    public ImplementationInfo findByOrganization(Organization organization);
}
