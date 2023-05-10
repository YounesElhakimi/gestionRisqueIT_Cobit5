package ma.ehtp.gestionrisqueit.phase3.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskEvent;
import ma.ehtp.gestionrisqueit.phase3.repositories.RiskEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RiskEventServiceImp implements RiskEventService{

    @Autowired
    RiskEventRepository riskEventRepository;


    @Override
    public List<RiskEvent> saveAll(List<RiskEvent> riskEventList) {
        return riskEventRepository.saveAll(riskEventList);
    }

    @Override
    public List<RiskEvent> findAll() {
        return riskEventRepository.findAll();
    }

    @Override
    public List<RiskEvent> findByOrganization(Organization organization) {
        return riskEventRepository.findByOrganization(organization);
    }

    @Override
    public RiskEvent save(RiskEvent riskEvent) {
        return riskEventRepository.save(riskEvent);
    }

    @Override
    public void deleteAll(List<RiskEvent> riskEventList) {
        riskEventRepository.deleteAll(riskEventList);
    }

    @Override
    public List<RiskEvent> findByOrganizationOrderByImpacteMAD(Organization organization) {
        return riskEventRepository.findByOrganizationOrderByImpacteMADDesc(organization);
    }
}
