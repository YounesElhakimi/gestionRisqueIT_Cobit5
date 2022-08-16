package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Organization;
import ma.ehtp.gestionrisqueit.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImp implements OrganizationService{

    @Autowired
    private OrganizationRepository organizationRepository;


    @Override
    public Organization save(Organization org) {
        return organizationRepository.save(org);
    }

    @Override
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }
}
