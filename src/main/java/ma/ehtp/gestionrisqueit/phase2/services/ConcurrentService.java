package ma.ehtp.gestionrisqueit.phase2.services;

import ma.ehtp.gestionrisqueit.phase2.modelles.Concurrent;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase2.modelles.MajorFact;

import java.util.List;

public interface ConcurrentService {

    public Concurrent save(Concurrent concurrent);
    public List<Concurrent> findAll();
    public List<Concurrent> saveAll(List<Concurrent> concurrentList);
    public List<Concurrent> findByOrganization(Organization organization);
    public void deleteAll(List<Concurrent> concurrentList);

}
