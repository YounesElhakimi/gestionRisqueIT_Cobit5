package ma.ehtp.gestionrisqueit.phase5.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase5.modelles.BusinessContinuityPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BusinessContinuityPlanRepository extends JpaRepository<BusinessContinuityPlan,Long> {
    public BusinessContinuityPlan findByOrganization(Organization organization);
}
