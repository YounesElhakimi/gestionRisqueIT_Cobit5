package ma.ehtp.gestionrisqueit.phase5.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase5.modelles.ObjectifProgression;

import java.util.List;
import java.util.Optional;

public interface ObjectifProgressionService {
    public List<ObjectifProgression> findByOrganization(Organization organization);
    public ObjectifProgression save(ObjectifProgression objectifProgression);
    public List<ObjectifProgression> saveAll(List<ObjectifProgression> objectifProgressionList);
    public Optional<ObjectifProgression> findById(Long id);
    public void deleteAll(List<ObjectifProgression> objectifProgressionList);
}
