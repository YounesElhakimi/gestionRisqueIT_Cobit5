package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Description;
import ma.ehtp.gestionrisqueit.modelles.MajorRisks;

import java.util.List;

public interface MajorRisksService {
    public MajorRisks save(MajorRisks mr);
    public List<MajorRisks> findAll();
    public List<MajorRisks> saveAll(List<MajorRisks> majorRisks);
}
