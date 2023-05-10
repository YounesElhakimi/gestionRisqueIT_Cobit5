package ma.ehtp.gestionrisqueit.phase5.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase5.modelles.BusinessContinuityPlan;
import ma.ehtp.gestionrisqueit.phase5.repositories.BusinessContinuityPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BusinessContinuityPlanServiceImp implements BusinessContinuityPlanService{
    @Autowired
    BusinessContinuityPlanRepository businessContinuityPlanRepository;

    @Override
    public List<BusinessContinuityPlan> findAll() {
        return businessContinuityPlanRepository.findAll();
    }

    @Override
    public BusinessContinuityPlan save(BusinessContinuityPlan businessContinuityPlan) {
        return businessContinuityPlanRepository.save(businessContinuityPlan);
    }

    @Override
    public BusinessContinuityPlan findByOrganization(Organization organization) {
        return businessContinuityPlanRepository.findByOrganization(organization);
    }

    @Override
    public List<BusinessContinuityPlan> saveAll(List<BusinessContinuityPlan> businessContinuityPlanList) {
        return businessContinuityPlanRepository.saveAll(businessContinuityPlanList);
    }

    @Override
    public Optional<BusinessContinuityPlan> findById(Long id) {
        return businessContinuityPlanRepository.findById(id);
    }

    @Override
    public void delete(BusinessContinuityPlan businessContinuityPlan) {
        businessContinuityPlanRepository.delete(businessContinuityPlan);
    }

    @Override
    public void deleteAll(List<BusinessContinuityPlan> businessContinuityPlanList) {
        businessContinuityPlanRepository.deleteAll(businessContinuityPlanList);
    }
}
