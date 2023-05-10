package ma.ehtp.gestionrisqueit.phase2.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase2.modelles.MajorFact;

import java.util.List;

public interface MajorFactService {
    public MajorFact save(MajorFact mr);
    public List<MajorFact> findAll();
    public List<MajorFact> saveAll(List<MajorFact> majorFacts);
    public List<MajorFact> saveAllExternal(List<MajorFact> majorFacts);
    public List<MajorFact> saveAllInternal(List<MajorFact> majorFacts);
    public List<MajorFact> findByIsInternal(Boolean type);
    public List<MajorFact> findByOrganizationAndIsInternal(Organization organization , Boolean type);
    public void deleteAll(List<MajorFact> majorFacts);
}
