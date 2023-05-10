package ma.ehtp.gestionrisqueit.phase3.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.modelles.dto.NbrOfRMByCat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface RiskMappingService {

    public RiskMapping save(RiskMapping riskMapping);
    public List<RiskMapping> findByOrganization(Organization organization);
    public List<RiskMapping>  saveAll(List<RiskMapping> riskMappingList );
    public List<RiskMapping>  updateAll(List<RiskMapping> riskMappingList );
    public List<RiskMapping>  findAll();
    public Optional<RiskMapping> findById(Long id);
    public List<RiskMapping> findByOrganizationWithoutSP(Organization organization);
    List<Object[]> countAllByCategory(Organization organization);
    List<Object[]> countAllByFrequenceAndImpact(Organization organization);
    List<RiskMapping> findByOrganizationOrderByCriticalityLevel(Organization organization);
    List<RiskMapping> findByOrganizationOrderByExpositionDesc(Organization organization);
    List<Object[]> countAllByEfficacite(Organization organization);
    List<RiskMapping> zoomSurLesRisque(Organization organizatio);
    public List<RiskMapping> findAllDevenuCritique(Organization organization);



}
