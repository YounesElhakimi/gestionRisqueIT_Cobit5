package ma.ehtp.gestionrisqueit.phase2.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase2.modelles.MajorFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MajorFacteRepository extends JpaRepository<MajorFact, Long> {

    public List<MajorFact> findByIsInternal(Boolean type);
    public List<MajorFact> findByOrganizationAndIsInternal(Organization organization , Boolean type);

}
