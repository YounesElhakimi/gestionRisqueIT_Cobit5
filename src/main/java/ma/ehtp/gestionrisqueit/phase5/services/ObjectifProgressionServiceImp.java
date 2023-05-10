package ma.ehtp.gestionrisqueit.phase5.services;


import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.repositories.ObjectiveRepository;
import ma.ehtp.gestionrisqueit.phase5.modelles.ObjectifProgression;
import ma.ehtp.gestionrisqueit.phase5.repositories.ObjectifProgressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjectifProgressionServiceImp implements ObjectifProgressionService{
    @Autowired
    ObjectifProgressionRepository objectifProgressionRepository;

    @Override
    public List<ObjectifProgression> findByOrganization(Organization organization) {
        return objectifProgressionRepository.findByOrganization(organization);
    }

    @Override
    public ObjectifProgression save(ObjectifProgression objectifProgression) {

        return objectifProgressionRepository.save(objectifProgression);
    }

    @Override
    public List<ObjectifProgression> saveAll(List<ObjectifProgression> objectifProgressionList) {
        return objectifProgressionRepository.saveAll(objectifProgressionList);
    }

    @Override
    public Optional<ObjectifProgression> findById(Long id) {
        return objectifProgressionRepository.findById(id);
    }

    @Override
    public void deleteAll(List<ObjectifProgression> objectifProgressionList) {
        objectifProgressionRepository.deleteAll(objectifProgressionList);
    }
}
