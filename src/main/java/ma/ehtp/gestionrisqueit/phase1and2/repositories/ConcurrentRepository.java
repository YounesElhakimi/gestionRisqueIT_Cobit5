package ma.ehtp.gestionrisqueit.repositories;

import ma.ehtp.gestionrisqueit.modelles.Concurrent;
import ma.ehtp.gestionrisqueit.modelles.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ConcurrentRepository extends JpaRepository<Concurrent, Long> {

    public List<Concurrent> findByOrganization(Organization organization);

}
