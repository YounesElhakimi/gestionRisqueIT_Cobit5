package ma.ehtp.gestionrisqueit.phase4.repositories;

import ma.ehtp.gestionrisqueit.phase4.modelles.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
