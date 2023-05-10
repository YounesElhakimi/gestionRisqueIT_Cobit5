package ma.ehtp.gestionrisqueit.phase4.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase4.modelles.MechanismsDescription;

import java.util.List;

public interface MechanismsDescriptionService {
    public MechanismsDescription findByOrganization(Organization organization);
    public MechanismsDescription save(MechanismsDescription mechanismsDescription);
    public  List<MechanismsDescription> findAll();
    public List<MechanismsDescription> saveAll(List<MechanismsDescription>  mechanismsDescriptionList);
    public void deleteAll(List<MechanismsDescription>  mechanismsDescriptionList);
    public void delete(MechanismsDescription  mechanismsDescription);
}
