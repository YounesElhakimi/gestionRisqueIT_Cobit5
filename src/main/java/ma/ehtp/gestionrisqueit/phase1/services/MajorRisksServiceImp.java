package ma.ehtp.gestionrisqueit.phase1.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.MajorRisks;
import ma.ehtp.gestionrisqueit.phase1.repositories.MajorRisksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class MajorRisksServiceImp implements MajorRisksService{

    @Autowired
    MajorRisksRepository majorRisksRepository;

    @Override
    public MajorRisks save(MajorRisks mr) {
        return majorRisksRepository.save(mr);
    }

    @Override
    public List<MajorRisks> findAll() {
        return majorRisksRepository.findAll();
    }

    @Override
    public List<MajorRisks> saveAll(List<MajorRisks> majorRisks) {
        return majorRisksRepository.saveAll(majorRisks);
    }

    @Override
    public List<MajorRisks> findByOrganization(Organization organization) {
        return majorRisksRepository.findByOrganization(organization);
    }

    @Override
    public void deleteAll(List<MajorRisks> majorRisks) {
        majorRisksRepository.deleteAll(majorRisks);
    }
}
