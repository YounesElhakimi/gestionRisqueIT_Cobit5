package ma.ehtp.gestionrisqueit.phase2.repositories;

import ma.ehtp.gestionrisqueit.phase2.modelles.Concurrent;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ConcurrentRepository extends JpaRepository<Concurrent, Long> {

    public List<Concurrent> findByOrganization(Organization organization);

}
