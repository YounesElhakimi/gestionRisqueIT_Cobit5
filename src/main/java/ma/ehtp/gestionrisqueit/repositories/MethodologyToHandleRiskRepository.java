package ma.ehtp.gestionrisqueit.repositories;

import ma.ehtp.gestionrisqueit.modelles.MethodologyToHandleRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MethodologyToHandleRiskRepository extends JpaRepository<MethodologyToHandleRisk , Long> {
}
