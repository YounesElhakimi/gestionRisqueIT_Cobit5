package ma.ehtp.gestionrisqueit.phase4.repositories;

import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase4.modelles.HistoriqueControlesAssuranceQualite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriqueControlesAssuranceQualiteRepository extends JpaRepository<HistoriqueControlesAssuranceQualite , Long> {

       public List<HistoriqueControlesAssuranceQualite> findByRiskMapping(RiskMapping riskMapping);

}
