package ma.ehtp.gestionrisqueit.phase1.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.Gantt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GanttRepository extends JpaRepository<Gantt , Long> {
    public Gantt findByOrganization(Organization organization);
}
