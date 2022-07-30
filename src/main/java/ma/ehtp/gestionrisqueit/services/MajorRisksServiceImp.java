package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.MajorRisks;
import ma.ehtp.gestionrisqueit.repositories.MajorRisksRepository;
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
}
