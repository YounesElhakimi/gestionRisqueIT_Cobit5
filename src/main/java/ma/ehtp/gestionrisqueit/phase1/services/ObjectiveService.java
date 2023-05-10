package ma.ehtp.gestionrisqueit.phase1.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.Objective;

public interface ObjectiveService {
    public Objective findByOrganization(Organization organization);
    public Objective save( String s ,   Organization organization);
    public Objective save( Objective objective);

}
