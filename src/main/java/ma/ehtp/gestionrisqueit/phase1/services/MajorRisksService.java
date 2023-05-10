package ma.ehtp.gestionrisqueit.phase1.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.MajorRisks;

import java.util.List;

public interface MajorRisksService {
    public MajorRisks save(MajorRisks mr);
    public List<MajorRisks> findAll();
    public List<MajorRisks> saveAll(List<MajorRisks> majorRisks);
    public List<MajorRisks> findByOrganization(Organization organization);
    public void deleteAll(List<MajorRisks> majorRisks);

}
