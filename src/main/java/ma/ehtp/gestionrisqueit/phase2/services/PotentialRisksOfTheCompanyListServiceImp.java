package ma.ehtp.gestionrisqueit.phase2.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase2.modelles.PotentialRisksOfTheCompany;
import ma.ehtp.gestionrisqueit.phase2.repositories.PotentialRisksOfTheCompanyRepository;
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

    @Override
    public void deletAllByOrganization(Organization organization) {
      //  potentialRisksOfTheCompanyRepository.deleteAllByOrganization(organization);
        U.ptxt("befor delete" +  potentialRisksOfTheCompanyRepository.findAByOrganization(organization).size());
        potentialRisksOfTheCompanyRepository.deleteAll(
                potentialRisksOfTheCompanyRepository.findAByOrganization(organization)
        );
        U.ptxt("after delete" +  potentialRisksOfTheCompanyRepository.findAByOrganization(organization).size());

    }
}
