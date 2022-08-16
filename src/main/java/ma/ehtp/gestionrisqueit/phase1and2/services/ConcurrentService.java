package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Concurrent;
import ma.ehtp.gestionrisqueit.modelles.Organization;

import java.util.List;

public interface ConcurrentService {

    public Concurrent save(Concurrent concurrent);
    public List<Concurrent> findAll();
    public List<Concurrent> saveAll(List<Concurrent> concurrentList);
    public List<Concurrent> findByOrganization(Organization organization);

}
