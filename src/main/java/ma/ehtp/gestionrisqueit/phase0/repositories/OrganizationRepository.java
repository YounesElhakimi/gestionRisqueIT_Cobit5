package ma.ehtp.gestionrisqueit.phase0.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    public Optional<Organization> findById(Long id);
}
