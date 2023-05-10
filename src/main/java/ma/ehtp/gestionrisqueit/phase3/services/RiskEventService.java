package ma.ehtp.gestionrisqueit.phase3.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskEvent;

import java.util.List;

public interface RiskEventService {
    public List<RiskEvent> saveAll(List<RiskEvent> riskEventList);
    public List<RiskEvent> findAll();
    public List<RiskEvent> findByOrganization(Organization organization);
    public RiskEvent save(RiskEvent riskEvent);
    public void deleteAll(List<RiskEvent> riskEventList);
    public List<RiskEvent> findByOrganizationOrderByImpacteMAD(Organization organization);
}
