package ma.ehtp.gestionrisqueit.phase2.services;



import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase2.modelles.MethodologyToHandleRisk;

import java.util.List;

public interface MethodologyToHandleRiskService {
    public MethodologyToHandleRisk save(MethodologyToHandleRisk methodologyToHandleRisk);
    public List<MethodologyToHandleRisk> findAll();

    public MethodologyToHandleRisk findByOrganization(Organization organization);

    public List<MethodologyToHandleRisk> saveAll(List<MethodologyToHandleRisk> methodologyToHandleRisks);
    public void delete(MethodologyToHandleRisk mr);
    public void deleteAll(List<MethodologyToHandleRisk> methodologyToHandleRisks);
}
