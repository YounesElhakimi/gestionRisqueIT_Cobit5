package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.ITRiskElement;
import ma.ehtp.gestionrisqueit.modelles.Organization;

import java.util.List;

public interface ITRiskElementService {
    public ITRiskElement save(ITRiskElement itRiskElement);
    public List<ITRiskElement> saveAll(List<ITRiskElement> itRiskElementList);
    public List<ITRiskElement> findAll();
    public List<ITRiskElement> findByOrganization(Organization organization);
}
