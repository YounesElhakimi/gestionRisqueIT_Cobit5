package ma.ehtp.gestionrisqueit.phase3.services;

import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.modelles.SousProcessus;
import ma.ehtp.gestionrisqueit.phase3.repositories.SousProcessusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SousProcessusServiceImp implements SousProcessusService{

    @Autowired
    SousProcessusRepository sousProcessusRepository;


    @Override
    public SousProcessus save(SousProcessus sousProcessus) {
        return sousProcessusRepository.save(sousProcessus);
    }

    @Override
    public List<SousProcessus> saveAll(List<SousProcessus> sousProcessusList) {
        return sousProcessusRepository.saveAll(sousProcessusList);
    }

    @Override
    public List<SousProcessus> findAll() {
        return sousProcessusRepository.findAll();
    }

    @Override
    public List<SousProcessus> findByRiskMapping(RiskMapping riskMapping) {
        return sousProcessusRepository.findByRiskMapping(riskMapping);
    }
}
