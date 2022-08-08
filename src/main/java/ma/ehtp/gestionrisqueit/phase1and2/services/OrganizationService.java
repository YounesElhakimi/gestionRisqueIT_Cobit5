package ma.ehtp.gestionrisqueit.services;


import ma.ehtp.gestionrisqueit.modelles.Organization;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrganizationService {
    public Organization save(Organization org);
    public List<Organization> findAll();
}
