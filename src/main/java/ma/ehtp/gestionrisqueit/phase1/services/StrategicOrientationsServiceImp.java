package ma.ehtp.gestionrisqueit.phase1.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.StrategicOrientations;
import ma.ehtp.gestionrisqueit.phase1.repositories.StrategicOrientationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StrategicOrientationsServiceImp implements StrategicOrientationsService{

    @Autowired
    StrategicOrientationsRepository strategicOrientationsRepository;

    @Override
    public StrategicOrientations save(StrategicOrientations so) {
        return strategicOrientationsRepository.save(so);
    }

    @Override
    public List<StrategicOrientations> findAll() {
        return strategicOrientationsRepository.findAll();
    }

    @Override
    public List<StrategicOrientations> saveAll(List<StrategicOrientations> strategicOrientations) {
        return strategicOrientationsRepository.saveAll(strategicOrientations);
    }

    @Override
    public List<StrategicOrientations> findAllByOrganization(Organization organization) {
        return strategicOrientationsRepository.findAllByOrganization(organization);
    }

    @Override
    public void deleteAll(List<StrategicOrientations> strategicOrientations) {
        strategicOrientationsRepository.deleteAll(strategicOrientations);
    }
}
