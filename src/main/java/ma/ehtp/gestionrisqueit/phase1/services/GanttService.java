package ma.ehtp.gestionrisqueit.phase1.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.Gantt;

public interface GanttService {
    public Gantt findByOrganization(Organization organization);
    public Gantt save(Gantt gantt);

}
