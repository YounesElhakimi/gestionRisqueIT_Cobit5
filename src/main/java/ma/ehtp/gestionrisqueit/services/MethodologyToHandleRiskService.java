package ma.ehtp.gestionrisqueit.services;



import ma.ehtp.gestionrisqueit.modelles.MethodologyToHandleRisk;

import java.util.List;

public interface MethodologyToHandleRiskService {
    public MethodologyToHandleRisk save(MethodologyToHandleRisk methodologyToHandleRisk);
    public List<MethodologyToHandleRisk> findAll();
    public List<MethodologyToHandleRisk> saveAll(List<MethodologyToHandleRisk> methodologyToHandleRisks);
}
