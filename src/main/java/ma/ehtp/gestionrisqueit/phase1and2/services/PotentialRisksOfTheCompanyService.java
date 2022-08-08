package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Organization;
import ma.ehtp.gestionrisqueit.modelles.PotentialRisksOfTheCompany;

import java.util.List;

public interface PotentialRisksOfTheCompanyService {
    public PotentialRisksOfTheCompany save(PotentialRisksOfTheCompany potentialRisksOfTheCompany);
    public List<PotentialRisksOfTheCompany> saveAll(List<PotentialRisksOfTheCompany> potentialRisksOfTheCompanyList);

    public List<PotentialRisksOfTheCompany> findAll();

    public List<PotentialRisksOfTheCompany> findAByOrganization(Organization organization);


}
