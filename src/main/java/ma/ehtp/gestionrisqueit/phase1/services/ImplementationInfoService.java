package ma.ehtp.gestionrisqueit.phase1.services;


import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.ImplementationInfo;

public interface ImplementationInfoService {
    public ImplementationInfo save(ImplementationInfo implementationInfo);
    public ImplementationInfo findByOrganization(Organization organization);


}
