package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Description;
import ma.ehtp.gestionrisqueit.repositories.DescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescriptionServiceImp implements DescriptionService{

    @Autowired
    DescriptionRepository descriptionRepository;

    @Override
    public Description save(Description des) {
        return descriptionRepository.save(des);
    }

    @Override
    public List<Description> findAll() {
        return descriptionRepository.findAll();
    }

    @Override
    public List<Description> saveAll(List<Description> descriptions) {
        return descriptionRepository.saveAll(descriptions);
    }
}
