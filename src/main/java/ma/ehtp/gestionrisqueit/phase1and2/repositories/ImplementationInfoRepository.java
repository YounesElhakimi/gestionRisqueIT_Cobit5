package ma.ehtp.gestionrisqueit.repositories;

import ma.ehtp.gestionrisqueit.modelles.ImplementationInfo;
import ma.ehtp.gestionrisqueit.modelles.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImplementationInfoRepository extends JpaRepository<ImplementationInfo , Long> {

    //public ImplementationInfo findByOrganization(Organization organization);
}
