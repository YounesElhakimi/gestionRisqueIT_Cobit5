package ma.ehtp.gestionrisqueit.phase4.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase4.modelles.CommunicationPlan;

import java.util.List;

public interface CommunicationPlanServive {
    public List<CommunicationPlan> findAll();
    public List<CommunicationPlan> findByOrganization(Organization organization);
    public List<CommunicationPlan> saveAll(List<CommunicationPlan> communicationPlanList);
    public CommunicationPlan save(CommunicationPlan communicationPlan);
    public void deleteAll(List<CommunicationPlan> communicationPlanList);

}
