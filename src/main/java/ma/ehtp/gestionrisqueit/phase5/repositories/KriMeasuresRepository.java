package ma.ehtp.gestionrisqueit.phase5.repositories;

import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase5.modelles.KriMeasures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KriMeasuresRepository extends JpaRepository<KriMeasures , Long> {
    public KriMeasures findByRiskMapping(RiskMapping riskMapping);
}
