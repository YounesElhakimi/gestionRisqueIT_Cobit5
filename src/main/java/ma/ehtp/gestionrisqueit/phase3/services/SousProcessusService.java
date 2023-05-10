package ma.ehtp.gestionrisqueit.phase3.services;

import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.modelles.SousProcessus;

import java.util.List;

public interface SousProcessusService {

    public SousProcessus save(SousProcessus sousProcessus);
    public  List<SousProcessus> saveAll(List<SousProcessus> sousProcessusList);
    public  List<SousProcessus> findAll();
    public List<SousProcessus> findByRiskMapping(RiskMapping riskMapping);
}
