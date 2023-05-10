package ma.ehtp.gestionrisqueit.phase3.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.modelles.SousProcessus;
import ma.ehtp.gestionrisqueit.phase3.modelles.dto.NbrOfRMByCat;
import ma.ehtp.gestionrisqueit.phase3.repositories.RiskMappingRepository;
import ma.ehtp.gestionrisqueit.phase3.repositories.SousProcessusRepository;
import ma.ehtp.gestionrisqueit.phase4.modelles.HistoriqueControlesAssuranceQualite;
import ma.ehtp.gestionrisqueit.phase4.repositories.HistoriqueControlesAssuranceQualiteRepository;
import ma.ehtp.gestionrisqueit.phase4.repositories.MetricsOfRiskGovernanceRepository;
import ma.ehtp.gestionrisqueit.phase5.repositories.KriMeasuresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RiskMappingServiceImp implements RiskMappingService{


    @Autowired
    SousProcessusRepository sousProcessusRepository;

    @Autowired
    RiskMappingRepository riskMappingRepository;

    @Autowired
    HistoriqueControlesAssuranceQualiteRepository hcaqRepository;

    @Autowired
    KriMeasuresRepository kriMeasuresRepository;

    @Autowired
    MetricsOfRiskGovernanceRepository metricsOfRiskGovernanceRepository;


    @Override
    public RiskMapping save(RiskMapping riskMapping) {
        RiskMapping riskMapping1 = riskMappingRepository.save(riskMapping);
        if (riskMapping1.getSousProcessusList() != null){

            List<SousProcessus> sousProcessusList = riskMapping1.getSousProcessusList();
            for (SousProcessus sp:sousProcessusList
            ) {
                sp.setRiskMapping(riskMapping1);
            }
            riskMapping1.setSousProcessusList(sousProcessusRepository.saveAll(sousProcessusList));
        }
        return  riskMapping1;
    }

    @Override
    public List<RiskMapping> findByOrganization(Organization organization) {
        List<RiskMapping> riskMappingList = riskMappingRepository.findByOrganization(organization);
        for (RiskMapping rm:riskMappingList) {
            rm.setSousProcessusList(sousProcessusRepository.findByRiskMapping(rm));
        }
        return riskMappingList;

    }

    @Override
    public List<RiskMapping> findByOrganizationWithoutSP(Organization organization) {
        List<RiskMapping> riskMappingList = riskMappingRepository.findByOrganizationAndIsHidden(organization , false);
        return riskMappingList;

    }

    @Override
    public List<Object[]> countAllByCategory(Organization organization) {
        return riskMappingRepository.countAllByCategory(organization.getId());
    }

    @Override
    public List<Object[]> countAllByFrequenceAndImpact(Organization organization) {
        return riskMappingRepository.countAllByFrequenceAndImpact(organization.getId());
    }

    @Override
    public List<RiskMapping> findByOrganizationOrderByCriticalityLevel(Organization organization) {
        return riskMappingRepository.findByOrganizationOrderByCriticalityLevel(organization.getId());
    }

    @Override
    public List<RiskMapping> findByOrganizationOrderByExpositionDesc(Organization organization) {
        return riskMappingRepository.findByOrganizationOrderByExpositionDesc(organization);
    }

    @Override
    public List<Object[]> countAllByEfficacite(Organization organization) {
        return riskMappingRepository.countAllByEfficacite(organization.getId());
    }

    @Override
    public List<RiskMapping> zoomSurLesRisque(Organization organizatio) {
        return riskMappingRepository.zoomSurLesRisque(organizatio.getId());
    }

    @Override
    public List<RiskMapping> findAllDevenuCritique(Organization organization) {
        List<RiskMapping> riskMappingList = riskMappingRepository.findByOrganizationAndIsHiddenAndCriticalityLevel(organization , false , "critique");

        riskMappingRepository.findByOrganizationAndIsHiddenAndCriticalityLevel(organization , false , "majeur").forEach(riskMapping -> {
            riskMappingList.add(riskMapping);
        });

        riskMappingList.forEach(r -> {
            System.out.println("id : "+r.getId()+"  c : "+r.getCriticalityLevel() );
        });

        List<RiskMapping> rmList = new ArrayList<>();
        for (RiskMapping rm:riskMappingList) {
            List<HistoriqueControlesAssuranceQualite> hqac = hcaqRepository.findByRiskMapping(rm);

            for (HistoriqueControlesAssuranceQualite hq :hqac )
            {
                System.out.println("id : "+hq.getId()+"  c : "+hq.getCriticalityLevel() );

                String c = hq.getCriticalityLevel();
                if (c.equals("modéré") || c.equals("mineur")){
                    rmList.add(rm);
                    break;
                }
            }

        }
        return rmList;
    }


    @Override
    public List<RiskMapping> saveAll(List<RiskMapping> riskMappingList) {

        List<RiskMapping> riskMappingList1 = new ArrayList<>();
        for (RiskMapping rm:riskMappingList) {
            if (rm.getId() != null){
                 if ((rm.getActivite() == "") && (rm.getMacroProcessus() == "") && (rm.getProcessus() == "")){
                    riskMappingRepository.findById(rm.getId()).ifPresent(riskMapping -> {
                        if (riskMapping.getIsHidden()){
                            sousProcessusRepository.deleteAll(
                                    sousProcessusRepository.findByRiskMapping(riskMapping)
                            );

                            riskMappingRepository.delete(riskMapping);
                        }else{
                            sousProcessusRepository.deleteAll(
                                    sousProcessusRepository.findByRiskMapping(riskMapping)
                            );
                        }
                    });



                }else{
                    Long id = rm.getId();
                    RiskMapping finalRm = rm;
                    riskMappingRepository.findById(id).ifPresent(riskMapping -> {
                        riskMapping.setActivite(finalRm.getActivite());
                        riskMapping.setMacroProcessus(finalRm.getMacroProcessus());
                        riskMapping.setProcessus(finalRm.getProcessus());
                        riskMappingRepository.save(riskMapping);

                        //delet all rm's sous pro
                        sousProcessusRepository.deleteAll(sousProcessusRepository.findByRiskMapping(finalRm));

                        if (finalRm.getSousProcessusList() != null){
                            if (finalRm.getSousProcessusList().size() > 0 ){
                                finalRm.getSousProcessusList().forEach(sousProcessus -> sousProcessus.setRiskMapping(riskMapping));
                                sousProcessusRepository.saveAll(finalRm.getSousProcessusList());
                            }
                        }
                    });
                }
            }else{
                rm.setIsHidden(true);
                if (rm.getSousProcessusList() == null){
                    rm.setSousProcessusList(new ArrayList<>());
                }

                rm = riskMappingRepository.save(rm);
                if (rm.getSousProcessusList().size() > 0 ){
                    for (SousProcessus sp : rm.getSousProcessusList()
                    ){
                        sp.setRiskMapping(rm);
                    }
                    sousProcessusRepository.saveAll(rm.getSousProcessusList());

                }


            }


            riskMappingList1.add(rm);
        }

        return riskMappingList1;
    }

    @Override
    public List<RiskMapping> updateAll(List<RiskMapping> riskMappingList) {
        U.ptxt("updateAll");
        List<RiskMapping> riskMappingList1 = new ArrayList<>();
        for (RiskMapping rm:riskMappingList) {
            U.ptxt("for, befor if , updateAll");

            if (rm.getIsDel() != null){
                if (rm.getIsDel() == true){
                    U.ptxt("RiskMapping id :"+rm.getId()+"  is deleted");

                    //hna KHdam
                    try {
                        hcaqRepository.deleteAll(
                                hcaqRepository.findByRiskMapping(rm)
                        );

                    }catch (Exception e){
                        U.ptxt("for delete an rm , e" + e.getMessage());
                    }

                    try {
                        sousProcessusRepository.deleteAll(
                                sousProcessusRepository.findByRiskMapping(rm)
                        );

                    }catch (Exception e){
                        U.ptxt("for delete an rm , e" + e.getMessage());
                    }

                    try {
                        metricsOfRiskGovernanceRepository.deleteAll(
                                metricsOfRiskGovernanceRepository.findByRiskMapping(rm)
                        );

                    }catch (Exception e){
                        U.ptxt("for delete an rm , e" + e.getMessage());
                    }

                    try {
                        kriMeasuresRepository.delete(
                                kriMeasuresRepository.findByRiskMapping(rm)
                        );

                    }catch (Exception e){
                        U.ptxt("for delete an rm , e" + e.getMessage());
                    }
                    riskMappingRepository.delete(rm);
                }else{
                    U.ptxt("RiskMapping id :"+rm.getId()+"  is not deleted");

                }
            }


            Long id = rm.getId();
            if (id != null){
                riskMappingRepository.findById(id).ifPresent(orm -> {
                    orm.setActivite(rm.getActivite());
                    orm.setMacroProcessus(rm.getMacroProcessus());
                    orm.setProcessus(rm.getProcessus());
                    orm.setCategory(rm.getCategory());
                    orm.setRisque(rm.getRisque());
                    orm.setKRI(rm.getKRI());
                    orm.setFacteur(rm.getFacteur());
                    orm.setConsequences(rm.getConsequences());
                    orm.setControlActivities(rm.getControlActivities());
                    orm.setEfficacite(rm.getEfficacite());
                    orm.setNbIncidents(rm.getNbIncidents());
                    orm.setFrequence(rm.getFrequence());
                    orm.setImpact(rm.getImpact());
                    orm.setExposition(rm.getExposition());
                    orm.setCriticalityLevel(rm.getCriticalityLevel());
                    orm.setOptionAcceptCout(rm.getOptionAcceptCout());
                    orm.setOptionAvoidCout(rm.getOptionAvoidCout());
                    orm.setOptionMitigateCout(rm.getOptionMitigateCout());
                    orm.setOptionTransferCout(rm.getOptionTransferCout());
                    U.ptxt("BeneficeAcceptCout : " +rm.getBeneficeAcceptCout());
                    orm.setBeneficeAcceptCout(rm.getBeneficeAcceptCout());
                    orm.setBeneficeAvoidCout(rm.getBeneficeAvoidCout());
                    orm.setBeneficeMitigateCout(rm.getBeneficeMitigateCout());
                    orm.setBeneficeTransferCout(rm.getBeneficeTransferCout());
                    orm.setOptionOptimale(rm.getOptionOptimale());
                    orm.setBesoins(rm.getBesoins());
                    orm.setOptionOptimaleCal(rm.getOptionOptimaleCal());
                    orm.setAvacncement(rm.getAvacncement());
                    orm.setIsHidden(false);
                    riskMappingRepository.save(orm);
                    riskMappingList1.add(orm);
                });

            }else{

                try {
                    U.p(rm.getOrganization());
                    rm.setIsHidden(false);
                    riskMappingList1.add(riskMappingRepository.save(rm));
                }catch (Exception e){
                    U.ptxt("Risk maping srvice updateAll function save new rm faild Exp :"+e.getMessage());

                }

            }
        }

        return riskMappingList1;
    }

    @Override
    public List<RiskMapping> findAll() {

        List<RiskMapping> riskMappingList = riskMappingRepository.findAll();
        for (RiskMapping rm:riskMappingList) {
            rm.setSousProcessusList(sousProcessusRepository.findByRiskMapping(rm));
        }
        return riskMappingList;
    }

    @Override
    public Optional<RiskMapping> findById(Long id) {
        return riskMappingRepository.findById(id);
    }
}
