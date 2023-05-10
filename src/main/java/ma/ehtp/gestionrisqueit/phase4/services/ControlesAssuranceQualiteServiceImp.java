package ma.ehtp.gestionrisqueit.phase4.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.services.RiskMappingService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase4.modelles.HistoriqueControlesAssuranceQualite;
import ma.ehtp.gestionrisqueit.phase4.modelles.dto.ControlesAssuranceQualiteDTO;
import ma.ehtp.gestionrisqueit.phase4.modelles.dto.ImpactIncidentDTO;
import ma.ehtp.gestionrisqueit.phase4.repositories.HistoriqueControlesAssuranceQualiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;




@Service
public class ControlesAssuranceQualiteServiceImp implements ControlesAssuranceQualiteService{

    @Autowired
    RiskMappingService riskMappingService;

    @Autowired
    HistoriqueControlesAssuranceQualiteRepository historiqueControlesAssuranceQualiteRepository;

    @Override
    public List<ControlesAssuranceQualiteDTO> saveAll(List<ControlesAssuranceQualiteDTO> cTAOList) {

        for (ControlesAssuranceQualiteDTO cdto:cTAOList) {
            Long id = Long.valueOf(cdto.getId());
            riskMappingService.findById(id).ifPresent(rm -> {
                HistoriqueControlesAssuranceQualite hcaq = new HistoriqueControlesAssuranceQualite();

                hcaq.setFrequence(rm.getFrequence());
                hcaq.setImpact(rm.getImpact());
                hcaq.setRiskMapping(rm);

                hcaq.setRisque(rm.getRisque());
                hcaq.setCriticalityLevel(rm.getCriticalityLevel());
                hcaq.setEfficacite(rm.getEfficacite());

                U.ptxt("try to save historique Controle sAssurance Qualite ");
                historiqueControlesAssuranceQualiteRepository.save(hcaq);

                U.pgt();
                System.out.println(cdto);
                U.pet();
                rm.setFrequence(cdto.getFrequence());
                rm.setImpact(cdto.getImpact());
                rm.setCriticalityLevel(cdto.getCriticalityLevel());
                rm.setEfficacite(cdto.getEfficacite());
                Integer imp = Integer.valueOf(cdto.getImpact());
                Integer fre = Integer.valueOf(cdto.getFrequence());
                Integer exp = imp * fre;
                rm.setExposition(""+exp);

                U.ptxt("tru to update the risk Mapping  ");
                rm.setSousProcessusList(new ArrayList<>());
                riskMappingService.save(rm);



            });


        }
         return cTAOList;
    }

    @Override
    public List<ControlesAssuranceQualiteDTO> findByOrganization(Organization organization) {
        List<RiskMapping> riskMappingList = riskMappingService.findByOrganizationWithoutSP(organization);
        List<ControlesAssuranceQualiteDTO> caqList = new ArrayList<>();

        for (RiskMapping rm:riskMappingList) {

            ControlesAssuranceQualiteDTO caq = new ControlesAssuranceQualiteDTO();

            caq.setFrequence(rm.getFrequence());
            caq.setImpact(rm.getImpact());
            caq.setRisque(rm.getRisque());
            caq.setCriticalityLevel(rm.getCriticalityLevel());
            caq.setEfficacite(rm.getEfficacite());
            caq.setId(String.valueOf(rm.getId()));

            caqList.add(caq);


        }

        return caqList;
    }

    @Override
    public List<ImpactIncidentDTO> findOldAndNew(Organization organization) {
        List<RiskMapping> riskMappingList = riskMappingService.findByOrganizationWithoutSP(organization);
        List<ImpactIncidentDTO> caqList = new ArrayList<>();

        for (RiskMapping rm:riskMappingList) {

            ImpactIncidentDTO caq = new ImpactIncidentDTO();
            caq.setFrequence(rm.getFrequence());
            caq.setImpact(rm.getImpact());
            caq.setRisque(rm.getRisque());
            caq.setCriticalityLevel(rm.getCriticalityLevel());
            caq.setEfficacite(rm.getEfficacite());

            try {
                HistoriqueControlesAssuranceQualite haq = historiqueControlesAssuranceQualiteRepository.findByRiskMapping(rm).get(0);
                caq.setOld_frequence(haq.getFrequence());
                caq.setOld_impact(haq.getImpact());
                caq.setOld_criticalityLevel(haq.getCriticalityLevel());
                caq.setOld_efficacite(haq.getEfficacite());
            }catch (Exception e){

            }


            caqList.add(caq);


        }

        return caqList;
    }

}
