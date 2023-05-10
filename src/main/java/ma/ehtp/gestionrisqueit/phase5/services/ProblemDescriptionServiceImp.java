package ma.ehtp.gestionrisqueit.phase5.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase5.modelles.ProblemDescription;
import ma.ehtp.gestionrisqueit.phase5.repositories.ProblemDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProblemDescriptionServiceImp implements  ProblemDescriptionService{
    @Autowired
    ProblemDescriptionRepository problemDescriptionRepository;
    @Override
    public ProblemDescription findByOrganization(Organization organization) {
        return problemDescriptionRepository.findByOrganization(organization);
    }

    @Override
    public ProblemDescription save(ProblemDescription problemDescription) {
        return problemDescriptionRepository.save(problemDescription);
    }

    @Override
    public void delete(ProblemDescription problemDescription) {
        problemDescriptionRepository.save(problemDescription);
    }
}
