package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Description;
import ma.ehtp.gestionrisqueit.modelles.StrategicOrientations;

import java.util.List;

public interface StrategicOrientationsService {
    public StrategicOrientations save(StrategicOrientations so);
    public List<StrategicOrientations> findAll();
    public List<StrategicOrientations> saveAll(List<StrategicOrientations> strategicOrientations);
}
