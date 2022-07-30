package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Concurrent;

import java.util.List;

public interface ConcurrentService {

    public Concurrent save(Concurrent concurrent);
    public List<Concurrent> findAll();
    public List<Concurrent> saveAll(List<Concurrent> concurrentList);
}
