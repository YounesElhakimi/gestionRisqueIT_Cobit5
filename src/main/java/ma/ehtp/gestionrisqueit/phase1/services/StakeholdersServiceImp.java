package ma.ehtp.gestionrisqueit.phase1.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.Stakeholders;
import ma.ehtp.gestionrisqueit.phase1.repositories.StakeholdersRepository;
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

    @Override
    public List<Stakeholders> findByOrganization(Organization organization) {
        return stakeholdersRepository.findByOrganization(organization);
    }

    @Override
    public void deleteAll(List<Stakeholders> stakeholders) {
        stakeholdersRepository.deleteAll(stakeholders);
    }
}
