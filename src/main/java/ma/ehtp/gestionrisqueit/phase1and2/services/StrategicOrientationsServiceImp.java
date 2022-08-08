package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.StrategicOrientations;
import ma.ehtp.gestionrisqueit.repositories.StrategicOrientationsRepository;
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
}
