package ma.ehtp.gestionrisqueit.phase3.repositories;

import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.modelles.SousProcessus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SousProcessusRepository extends JpaRepository<SousProcessus,Long> {
    List<SousProcessus> findByRiskMapping(RiskMapping riskMapping);
}
