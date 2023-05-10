package ma.ehtp.gestionrisqueit.phase4.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase4.modelles.MechanismsDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MechanismsDescriptionRepository  extends JpaRepository<MechanismsDescription , Long> {
    public MechanismsDescription findByOrganization(Organization organization);

}
