package ma.ehtp.gestionrisqueit.phase4.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase4.modelles.CommunicationPlan;
import ma.ehtp.gestionrisqueit.phase4.repositories.CommunicationPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


 @Service
public class CommunicationPlanServiveImp implements CommunicationPlanServive{

    @Autowired
    CommunicationPlanRepository communicationPlanRepository;
    @Override
    public List<CommunicationPlan> findAll() {
        return communicationPlanRepository.findAll();
    }

    @Override
    public List<CommunicationPlan> findByOrganization(Organization organization) {
        return communicationPlanRepository.findByOrganization(organization);
    }

    @Override
    public List<CommunicationPlan> saveAll(List<CommunicationPlan> communicationPlanList) {
        return communicationPlanRepository.saveAll(communicationPlanList);
    }

    @Override
    public CommunicationPlan save(CommunicationPlan communicationPlan) {
        return communicationPlanRepository.save(communicationPlan);
    }

     @Override
     public void deleteAll(List<CommunicationPlan> communicationPlanList) {
         communicationPlanRepository.deleteAll(communicationPlanList);
     }
 }
