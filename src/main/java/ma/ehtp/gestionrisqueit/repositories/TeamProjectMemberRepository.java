package ma.ehtp.gestionrisqueit.repositories;


import ma.ehtp.gestionrisqueit.modelles.TeamProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamProjectMemberRepository extends JpaRepository<TeamProjectMember, Long> {

}


