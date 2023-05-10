package ma.ehtp.gestionrisqueit.phase4.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase4.modelles.MetricsOfRiskGovernance;
import ma.ehtp.gestionrisqueit.phase4.modelles.dto.MetricsOfRiskGovernanceDTO;

import java.util.List;

public interface MetricsOfRiskGovernanceService {
   public List<MetricsOfRiskGovernanceDTO> findByOrganization(Organization organization);
   public List<MetricsOfRiskGovernance> saveAll(List<MetricsOfRiskGovernanceDTO> metricsOfRiskGovernanceDTOList);
   public MetricsOfRiskGovernance save(MetricsOfRiskGovernanceDTO metricsOfRiskGovernanceDTO);
   public MetricsOfRiskGovernance save(MetricsOfRiskGovernance mg);

}
