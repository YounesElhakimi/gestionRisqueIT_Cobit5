package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Stakeholders;
import ma.ehtp.gestionrisqueit.repositories.StakeholdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StakeholdersServiceImp implements StakeholdersService{

    @Autowired
    StakeholdersRepository stakeholdersRepository;
    @Override
    public Stakeholders save(Stakeholders sh) {
        return stakeholdersRepository.save(sh);
    }

    @Override
    public List<Stakeholders> findAll() {
        return stakeholdersRepository.findAll();
    }

    @Override
    public List<Stakeholders> saveAll(List<Stakeholders> stakeholders) {
        return stakeholdersRepository.saveAll(stakeholders);
    }
}
