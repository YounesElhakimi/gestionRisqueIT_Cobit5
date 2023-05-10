package ma.ehtp.gestionrisqueit.phase2.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase2.modelles.PotentialRisksOfTheCompany;

import java.util.List;

public interface PotentialRisksOfTheCompanyService {
    public PotentialRisksOfTheCompany save(PotentialRisksOfTheCompany potentialRisksOfTheCompany);
    public List<PotentialRisksOfTheCompany> saveAll(List<PotentialRisksOfTheCompany> potentialRisksOfTheCompanyList);

    public List<PotentialRisksOfTheCompany> findAll();

    public List<PotentialRisksOfTheCompany> findAByOrganization(Organization organization);
    public void deletAllByOrganization(Organization organization);


}
