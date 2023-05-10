package ma.ehtp.gestionrisqueit.phase4.repositories;

import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase4.modelles.MetricsOfRiskGovernance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetricsOfRiskGovernanceRepository extends JpaRepository<MetricsOfRiskGovernance , Long> {

    public List<MetricsOfRiskGovernance> findByRiskMapping(RiskMapping riskMapping);
}
