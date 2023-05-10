package ma.ehtp.gestionrisqueit.phase1.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase1.modelles.ImplementationInfo;
import ma.ehtp.gestionrisqueit.phase1.repositories.ImplementationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImplementationInfoServiceImp implements ImplementationInfoService {

    @Autowired
    ImplementationInfoRepository implementationInfoRepository;


    @Override
    public ImplementationInfo save(ImplementationInfo implementationInfo) {
        U.ptxt("id  :" + implementationInfo.getId());
        ImplementationInfo imi = implementationInfoRepository.findByOrganization(implementationInfo.getOrganization());
        if (imi == null){
            return implementationInfoRepository.save(implementationInfo);
        }else{
            imi.setLevelofdetails(implementationInfo.getLevelofdetails());
            return implementationInfoRepository.save(imi);
        }



    }

    @Override
    public ImplementationInfo findByOrganization(Organization organization) {
        return implementationInfoRepository.findByOrganization(organization);
    }
/*
    @Override
    public ImplementationInfo findByOrganization(Organization organization) {
        return implementationInfoRepository.findByOrganization(organization);
    }

 */
}
