package ma.ehtp.gestionrisqueit.phase2.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase2.modelles.MethodologyToHandleRisk;
import ma.ehtp.gestionrisqueit.phase2.repositories.MethodologyToHandleRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MethodologyToHandleRiskServiceImp implements MethodologyToHandleRiskService{
    @Autowired
    MethodologyToHandleRiskRepository methodologyToHandleRiskRepository;
    @Override
    public MethodologyToHandleRisk save(MethodologyToHandleRisk methodologyToHandleRisk) {
        return methodologyToHandleRiskRepository.save(methodologyToHandleRisk);
    }

    @Override
    public List<MethodologyToHandleRisk> findAll() {
        return methodologyToHandleRiskRepository.findAll();
    }

    @Override
    public MethodologyToHandleRisk findByOrganization(Organization organization) {
        return methodologyToHandleRiskRepository.findByOrganization(organization);
    }

    @Override
    public List<MethodologyToHandleRisk> saveAll(List<MethodologyToHandleRisk> methodologyToHandleRisks) {
        return methodologyToHandleRiskRepository.saveAll(methodologyToHandleRisks);
    }

    @Override
    public void delete(MethodologyToHandleRisk mr) {
        methodologyToHandleRiskRepository.delete(mr);
    }

    @Override
    public void deleteAll(List<MethodologyToHandleRisk> methodologyToHandleRisks) {
        methodologyToHandleRiskRepository.deleteAll(methodologyToHandleRisks);
    }
}
