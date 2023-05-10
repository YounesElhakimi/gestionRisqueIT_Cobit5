package ma.ehtp.gestionrisqueit.phase4.services;


import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase4.modelles.MechanismsDescription;
import ma.ehtp.gestionrisqueit.phase4.repositories.MechanismsDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MechanismsDescriptionServiceImp implements  MechanismsDescriptionService{
    @Autowired
    MechanismsDescriptionRepository mechanismsDescriptionRepository;


    @Override
    public MechanismsDescription findByOrganization(Organization organization) {
        return mechanismsDescriptionRepository.findByOrganization(organization);
    }

    @Override
    public MechanismsDescription save(MechanismsDescription mechanismsDescription) {
        return mechanismsDescriptionRepository.save(mechanismsDescription);
    }

    @Override
    public List<MechanismsDescription> findAll() {
        return mechanismsDescriptionRepository.findAll();

    }

    @Override
    public List<MechanismsDescription> saveAll(List<MechanismsDescription> mechanismsDescriptionList) {
        return mechanismsDescriptionRepository.saveAll(mechanismsDescriptionList);
    }

    @Override
    public void deleteAll(List<MechanismsDescription> mechanismsDescriptionList) {
        mechanismsDescriptionRepository.deleteAll(mechanismsDescriptionList);
    }

    @Override
    public void delete(MechanismsDescription mechanismsDescription) {
        mechanismsDescriptionRepository.delete(mechanismsDescription);
    }
}
