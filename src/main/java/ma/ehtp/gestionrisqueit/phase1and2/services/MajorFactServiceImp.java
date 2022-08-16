package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.MajorFact;
import ma.ehtp.gestionrisqueit.repositories.MajorFacteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorFactServiceImp implements MajorFactService{
    @Autowired
    MajorFacteRepository majorFacteRepository;
    @Override
    public MajorFact save(MajorFact mr) {
        return majorFacteRepository.save(mr);
    }

    @Override
    public List<MajorFact> findAll() {
        return majorFacteRepository.findAll();
    }

    @Override
    public List<MajorFact> saveAll(List<MajorFact> majorFacts) {
        return majorFacteRepository.saveAll(majorFacts);
    }

    @Override
    public List<MajorFact> saveAllExternal(List<MajorFact> majorFacts) {
        majorFacts.forEach(majorFact -> majorFact.setIsInternal(false));
        return saveAll(majorFacts);
    }

    @Override
    public List<MajorFact> saveAllInternal(List<MajorFact> majorFacts) {
        majorFacts.forEach(majorFact -> majorFact.setIsInternal(true));
        return saveAll(majorFacts);
    }

    @Override
    public List<MajorFact> findByIsInternal(Boolean type) {
        return null;
    }


}
