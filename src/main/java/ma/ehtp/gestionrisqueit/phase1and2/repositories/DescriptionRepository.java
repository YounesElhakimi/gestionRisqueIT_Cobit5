package ma.ehtp.gestionrisqueit.repositories;

import ma.ehtp.gestionrisqueit.modelles.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DescriptionRepository extends JpaRepository<Description , Long> {
}
