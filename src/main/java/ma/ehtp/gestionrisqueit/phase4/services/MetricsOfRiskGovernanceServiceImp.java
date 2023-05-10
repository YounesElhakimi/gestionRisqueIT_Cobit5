package ma.ehtp.gestionrisqueit.phase4.services;


import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.services.RiskMappingService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase4.modelles.MetricsOfRiskGovernance;
import ma.ehtp.gestionrisqueit.phase4.modelles.dto.MetricsOfRiskGovernanceDTO;
import ma.ehtp.gestionrisqueit.phase4.repositories.MetricsOfRiskGovernanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetricsOfRiskGovernanceServiceImp implements MetricsOfRiskGovernanceService{

    @Autowired
    RiskMappingService riskMappingService;

    @Autowired
    MetricsOfRiskGovernanceRepository metricsOfRiskGovernanceRepository;

    @Override
    public List<MetricsOfRiskGovernanceDTO> findByOrganization(Organization organization) {
        List<MetricsOfRiskGovernanceDTO> metricsOfRiskGovernanceDTOList = new ArrayList<>();
        List<RiskMapping> riskMappingList = riskMappingService.findByOrganizationWithoutSP(organization);
        for (RiskMapping rm:riskMappingList) {
            if (rm.getMetricsOfRiskGovernance() == null){
                U.ptxt("try to save mg in rm");
                MetricsOfRiskGovernance mg = new MetricsOfRiskGovernance() ;
                mg.setRiskMapping(rm);
                mg = metricsOfRiskGovernanceRepository.save(mg);
                rm.setMetricsOfRiskGovernance(mg);
                riskMappingService.save(rm);
                U.ptxt("end of try to save mg in rm");

            }
            MetricsOfRiskGovernanceDTO metricsOfRiskGovernanceDTO  = new MetricsOfRiskGovernanceDTO();
            if (rm.getKRI() == null){
                metricsOfRiskGovernanceDTO.setKri("kri is null");
                System.out.println("========  kri is null findByOrganization MetricsOfRiskGovernanceServiceImp =======");
            }
            else {
                MetricsOfRiskGovernance mg = rm.getMetricsOfRiskGovernance();

                metricsOfRiskGovernanceDTO.setKri(rm.getKRI());
                metricsOfRiskGovernanceDTO.setId(String.valueOf(mg.getId()));
                metricsOfRiskGovernanceDTO.setEntiteProductrice(mg.getEntiteProductrice());
                metricsOfRiskGovernanceDTO.setPeriodiciteDeMesure(mg.getPeriodiciteDeMesure());
                metricsOfRiskGovernanceDTO.setProcessusDObtention(mg.getProcessusDObtention());
                metricsOfRiskGovernanceDTO.setFormuleDeCalcul(mg.getFormuleDeCalcul());

                //chno howa fi risk maping
                metricsOfRiskGovernanceDTO.setRisque(rm.getRisque());


            }

            metricsOfRiskGovernanceDTOList.add(metricsOfRiskGovernanceDTO);

        }
        return metricsOfRiskGovernanceDTOList;
    }

    @Override
    public List<MetricsOfRiskGovernance> saveAll(List<MetricsOfRiskGovernanceDTO> metricsOfRiskGovernanceDTOList) {
        List<MetricsOfRiskGovernance> metricsOfRiskGovernances = new ArrayList<>();
        for (MetricsOfRiskGovernanceDTO mgDTO:metricsOfRiskGovernanceDTOList) {

          metricsOfRiskGovernanceRepository.findById(Long.valueOf(mgDTO.getId())).ifPresent(mg2 -> {
                mg2.setFormuleDeCalcul(mgDTO.getFormuleDeCalcul());
                mg2.setEntiteProductrice(mgDTO.getEntiteProductrice());
                mg2.setProcessusDObtention(mgDTO.getProcessusDObtention());
                mg2.setPeriodiciteDeMesure(mgDTO.getPeriodiciteDeMesure());

              metricsOfRiskGovernances.add(mg2);
          });




        }

        if (metricsOfRiskGovernances.size() == 0)
        return null;

        return metricsOfRiskGovernanceRepository.saveAll(metricsOfRiskGovernances);
    }

    @Override
    public MetricsOfRiskGovernance save(MetricsOfRiskGovernanceDTO mgDTO) {
        MetricsOfRiskGovernance mg2 = null;
        mg2.setFormuleDeCalcul(mgDTO.getFormuleDeCalcul());
        mg2.setEntiteProductrice(mgDTO.getEntiteProductrice());
        mg2.setProcessusDObtention(mgDTO.getProcessusDObtention());
        mg2.setPeriodiciteDeMesure(mgDTO.getPeriodiciteDeMesure());

        return metricsOfRiskGovernanceRepository.save(mg2);
    }

    @Override
    public MetricsOfRiskGovernance save(MetricsOfRiskGovernance mg2) {
         return metricsOfRiskGovernanceRepository.save(mg2);
    }
}
