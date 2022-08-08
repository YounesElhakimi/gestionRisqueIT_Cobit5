package ma.ehtp.gestionrisqueit.repositories;

import ma.ehtp.gestionrisqueit.modelles.MajorFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MajorFacteRepository extends JpaRepository<MajorFact, Long> {

    public List<MajorFact> findByIsInternal(Boolean type);
}
