package ma.ehtp.gestionrisqueit.phase2.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase2.modelles.ITRiskElement;
import ma.ehtp.gestionrisqueit.phase2.modelles.ITRiskElementSource;

import java.util.List;

public interface ITRiskElementSourceService {
    public ITRiskElementSource save(ITRiskElementSource itRiskElementSource);
    public List<ITRiskElementSource> findAll();
    public List<ITRiskElementSource> saveAll(List<ITRiskElementSource> itRiskElementSources);
    public List<ITRiskElementSource> findByOrganization(Organization organization);
    public void deleteAll(List<ITRiskElementSource> itRiskElementSources);
    public List<ITRiskElement> findBySource(ITRiskElementSource itRiskElementSource);

}
