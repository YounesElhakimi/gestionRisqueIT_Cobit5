package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Concurrent;
import ma.ehtp.gestionrisqueit.repositories.ConcurrentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConcurrentServiceImp implements ConcurrentService{
    @Autowired
    ConcurrentRepository concurrentRepository;

    @Override
    public Concurrent save(Concurrent concurrent) {
        return  concurrentRepository.save(concurrent);
    }

    @Override
    public List<Concurrent> findAll() {
        return concurrentRepository.findAll();
    }

    @Override
    public List<Concurrent> saveAll(List<Concurrent> concurrentList) {
        return concurrentRepository.saveAll(concurrentList);
    }


}
