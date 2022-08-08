package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Organization;
import ma.ehtp.gestionrisqueit.modelles.PotentialRisksOfTheCompany;
import ma.ehtp.gestionrisqueit.repositories.PotentialRisksOfTheCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PotentialRisksOfTheCompanyListServiceImp implements PotentialRisksOfTheCompanyService{

    @Autowired
    PotentialRisksOfTheCompanyRepository potentialRisksOfTheCompanyRepository;

    @Override
    public PotentialRisksOfTheCompany save(PotentialRisksOfTheCompany potentialRisksOfTheCompany) {
        return potentialRisksOfTheCompanyRepository.save(potentialRisksOfTheCompany);
    }

    @Override
    public List<PotentialRisksOfTheCompany> saveAll(List<PotentialRisksOfTheCompany> potentialRisksOfTheCompanyList) {
        return potentialRisksOfTheCompanyRepository.saveAll(potentialRisksOfTheCompanyList);
    }

    @Override
    public List<PotentialRisksOfTheCompany> findAll() {
        return potentialRisksOfTheCompanyRepository.findAll();
    }

    @Override
    public List<PotentialRisksOfTheCompany> findAByOrganization(Organization organization) {
        return potentialRisksOfTheCompanyRepository.findAByOrganization(organization);
    }
}
