package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.MajorFact;

import java.util.List;

public interface MajorFactService {
    public MajorFact save(MajorFact mr);
    public List<MajorFact> findAll();
    public List<MajorFact> saveAll(List<MajorFact> majorFacts);
    public List<MajorFact> saveAllExternal(List<MajorFact> majorFacts);
    public List<MajorFact> saveAllInternal(List<MajorFact> majorFacts);
    public List<MajorFact> findByIsInternal(Boolean type);
}
