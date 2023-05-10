package ma.ehtp.gestionrisqueit.phase1.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.StrategicOrientations;

import java.util.List;

public interface StrategicOrientationsService {
    public StrategicOrientations save(StrategicOrientations so);
    public List<StrategicOrientations> findAll();
    public List<StrategicOrientations> saveAll(List<StrategicOrientations> strategicOrientations);
    public List<StrategicOrientations> findAllByOrganization(Organization organization);
    public void deleteAll(List<StrategicOrientations> strategicOrientations);
}
