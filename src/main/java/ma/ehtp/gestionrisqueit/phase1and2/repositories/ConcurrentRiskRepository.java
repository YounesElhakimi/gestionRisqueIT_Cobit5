package ma.ehtp.gestionrisqueit.repositories;


import ma.ehtp.gestionrisqueit.modelles.Concurrent;
import ma.ehtp.gestionrisqueit.modelles.ConcurrentRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcurrentRiskRepository extends JpaRepository<ConcurrentRisk , Long> {

    public List<ConcurrentRisk> findByConcurrent(Concurrent concurrent);
}
