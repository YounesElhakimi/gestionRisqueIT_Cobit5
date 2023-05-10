package ma.ehtp.gestionrisqueit.phase2.repositories;


import ma.ehtp.gestionrisqueit.phase2.modelles.Concurrent;
import ma.ehtp.gestionrisqueit.phase2.modelles.ConcurrentRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcurrentRiskRepository extends JpaRepository<ConcurrentRisk , Long> {

    public List<ConcurrentRisk> findByConcurrent(Concurrent concurrent);
}
