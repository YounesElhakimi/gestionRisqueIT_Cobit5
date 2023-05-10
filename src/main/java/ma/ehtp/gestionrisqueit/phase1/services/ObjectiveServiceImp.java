package ma.ehtp.gestionrisqueit.phase1.services;


import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.Objective;
import ma.ehtp.gestionrisqueit.phase1.repositories.ObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ObjectiveServiceImp  implements  ObjectiveService{

    @Autowired
    ObjectiveRepository objectiveRepository;
    @Override
    public Objective findByOrganization(Organization organization) {
        return  objectiveRepository.findByOrganization(organization) ;
    }

    @Override
    public Objective save(String s , Organization organization) {
        String[] parts = s.split(Pattern.quote("="));
        String nameOrg;
        try {
            nameOrg = parts[2];
        }catch (Exception e){

            nameOrg = s;
        }
        return objectiveRepository.save(new Objective(null ,nameOrg ,organization ));
    }

    @Override
    public Objective save(Objective objective) {

        Objective o = objectiveRepository.findByOrganization(objective.getOrganization());
        if (o == null){

            return objectiveRepository.save(objective);
        }else{
            o.setDescription(objective.getDescription());
            return objectiveRepository.save(o);
        }

    }
}
