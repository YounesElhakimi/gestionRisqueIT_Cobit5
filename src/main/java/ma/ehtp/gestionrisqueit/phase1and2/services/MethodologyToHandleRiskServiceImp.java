package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.MethodologyToHandleRisk;
import ma.ehtp.gestionrisqueit.repositories.MethodologyToHandleRiskRepository;
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
    public List<MethodologyToHandleRisk> saveAll(List<MethodologyToHandleRisk> methodologyToHandleRisks) {
        return methodologyToHandleRiskRepository.saveAll(methodologyToHandleRisks);
    }
}
