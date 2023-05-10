package ma.ehtp.gestionrisqueit.phase5.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase5.modelles.ProblemDescription;

import java.util.List;

public interface ProblemDescriptionService {
    public ProblemDescription findByOrganization(Organization organization);
    public ProblemDescription save(ProblemDescription problemDescription);
    public void delete(ProblemDescription problemDescription);
}
