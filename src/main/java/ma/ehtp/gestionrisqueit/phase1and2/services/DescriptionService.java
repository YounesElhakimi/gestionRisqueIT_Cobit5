package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Description;

import java.util.List;

public interface DescriptionService {
    public Description save(Description des);
    public List<Description> findAll();
    public List<Description> saveAll(List<Description> descriptions);
}
