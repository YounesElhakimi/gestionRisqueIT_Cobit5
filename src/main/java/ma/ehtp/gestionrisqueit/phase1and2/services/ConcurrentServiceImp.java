package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Concurrent;
import ma.ehtp.gestionrisqueit.modelles.ConcurrentRisk;
import ma.ehtp.gestionrisqueit.modelles.Organization;
import ma.ehtp.gestionrisqueit.repositories.ConcurrentRepository;
import ma.ehtp.gestionrisqueit.repositories.ConcurrentRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConcurrentServiceImp implements ConcurrentService{
    @Autowired
    ConcurrentRepository concurrentRepository;

  @Autowired
    ConcurrentRiskRepository concurrentRiskRepository;

    @Override
    public Concurrent save(Concurrent concurrent) {
        Concurrent concurrent1 = concurrentRepository.save(concurrent);
        List<ConcurrentRisk> concurrentRiskList = concurrent.getConcurrentRiskList();
        for (ConcurrentRisk cr:concurrentRiskList
        ) {
           cr.setConcurrent(concurrent1);
        }
        concurrent1.setConcurrentRiskList(concurrentRiskRepository.saveAll(concurrentRiskList));
        return  concurrent1;
    }

    @Override
    public List<Concurrent> findAll() {
        List<Concurrent> concurrentList = concurrentRepository.findAll();
        for (Concurrent c:concurrentList) {
            c.setConcurrentRiskList(concurrentRiskRepository.findByConcurrent(c));
        }
        return concurrentList;
    }

    @Override
    public List<Concurrent> saveAll(List<Concurrent> concurrentList) {

        List<Concurrent> concurrentList1 = concurrentRepository.saveAll(concurrentList);
        for (Concurrent concurrent: concurrentList1
             ) {
            List<ConcurrentRisk> concurrentRiskList = concurrent.getConcurrentRiskList();
            for (ConcurrentRisk cr:concurrentRiskList
            ) {
                cr.setConcurrent(concurrent);
            }
            concurrent.setConcurrentRiskList(concurrentRiskRepository.saveAll(concurrentRiskList));

        }

        return concurrentList1;
    }

    @Override
    public List<Concurrent> findByOrganization(Organization organization) {
        List<Concurrent> concurrentList = concurrentRepository.findByOrganization(organization);
        for (Concurrent c:concurrentList) {
            c.setConcurrentRiskList(concurrentRiskRepository.findByConcurrent(c));
        }
        return concurrentList;
    }


}
