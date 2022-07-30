package ma.ehtp.gestionrisqueit.repositories;


import ma.ehtp.gestionrisqueit.modelles.Stakeholders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StakeholdersRepository extends JpaRepository<Stakeholders, Long> {
}
