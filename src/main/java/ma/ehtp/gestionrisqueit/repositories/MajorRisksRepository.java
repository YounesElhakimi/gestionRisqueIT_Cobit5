package ma.ehtp.gestionrisqueit.repositories;

import ma.ehtp.gestionrisqueit.modelles.MajorRisks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MajorRisksRepository extends JpaRepository<MajorRisks, Long> {
}
