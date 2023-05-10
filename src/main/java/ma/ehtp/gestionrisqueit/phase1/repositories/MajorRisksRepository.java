package ma.ehtp.gestionrisqueit.phase1.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.MajorRisks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MajorRisksRepository extends JpaRepository<MajorRisks, Long> {
    public List<MajorRisks> findByOrganization(Organization organization);
}
