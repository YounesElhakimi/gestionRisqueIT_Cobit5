package ma.ehtp.gestionrisqueit.phase0.services;


import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    public Organization save(Organization org);
    public List<Organization> findAll();
    public Optional<Organization> findById(Long id);
}
