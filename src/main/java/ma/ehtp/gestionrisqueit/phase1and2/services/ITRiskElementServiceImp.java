package ma.ehtp.gestionrisqueit.services;


import ma.ehtp.gestionrisqueit.modelles.ITRiskElement;
import ma.ehtp.gestionrisqueit.modelles.Organization;
import ma.ehtp.gestionrisqueit.repositories.ITRiskElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITRiskElementServiceImp implements ITRiskElementService {

    @Autowired
    ITRiskElementRepository itRiskElementRepository;


    @Override
    public ITRiskElement save(ITRiskElement itRiskElement) {
        return itRiskElementRepository.save(itRiskElement);
    }

    @Override
    public List<ITRiskElement> saveAll(List<ITRiskElement> itRiskElementList) {
        return itRiskElementRepository.saveAll(itRiskElementList);
    }

    @Override
    public List<ITRiskElement> findAll() {
        return itRiskElementRepository.findAll();
    }

    @Override
    public List<ITRiskElement> findByOrganization(Organization organization) {
        return itRiskElementRepository.findByOrganization(organization);
    }
}
