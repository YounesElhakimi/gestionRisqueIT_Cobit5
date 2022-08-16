package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.ImplementationInfo;
import ma.ehtp.gestionrisqueit.modelles.Organization;
import ma.ehtp.gestionrisqueit.repositories.ImplementationInfoRepository;
import ma.ehtp.gestionrisqueit.services.ImplementationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImplementationInfoServiceImp implements ImplementationInfoService {

    @Autowired
    ImplementationInfoRepository implementationInfoRepository;


    @Override
    public ImplementationInfo save(ImplementationInfo implementationInfo) {
        return  implementationInfoRepository.save(implementationInfo);
    }
/*
    @Override
    public ImplementationInfo findByOrganization(Organization organization) {
        return implementationInfoRepository.findByOrganization(organization);
    }

 */
}
