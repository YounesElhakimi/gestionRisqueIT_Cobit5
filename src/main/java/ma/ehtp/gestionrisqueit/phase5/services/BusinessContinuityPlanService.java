package ma.ehtp.gestionrisqueit.phase5.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase5.modelles.BusinessContinuityPlan;

import java.util.List;
import java.util.Optional;

public interface BusinessContinuityPlanService {
    public List<BusinessContinuityPlan> findAll();
  public  BusinessContinuityPlan save(BusinessContinuityPlan businessContinuityPlan);
    public BusinessContinuityPlan findByOrganization(Organization organization);
    public List<BusinessContinuityPlan> saveAll(List<BusinessContinuityPlan> businessContinuityPlanList);
    public Optional<BusinessContinuityPlan> findById(Long id);
    public void delete(BusinessContinuityPlan businessContinuityPlan);
    public void deleteAll(List<BusinessContinuityPlan> businessContinuityPlanList);


}
