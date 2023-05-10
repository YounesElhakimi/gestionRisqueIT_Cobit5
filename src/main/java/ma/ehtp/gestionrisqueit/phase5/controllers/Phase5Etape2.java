package ma.ehtp.gestionrisqueit.phase5.controllers;

import ma.ehtp.gestionrisqueit.phase0.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase1.modelles.ImplementationInfo;
import ma.ehtp.gestionrisqueit.phase1.modelles.MajorRisks;
import ma.ehtp.gestionrisqueit.phase1.modelles.Objective;
import ma.ehtp.gestionrisqueit.phase1.modelles.StrategicOrientations;
import ma.ehtp.gestionrisqueit.phase1.services.ImplementationInfoService;
import ma.ehtp.gestionrisqueit.phase1.services.MajorRisksService;
import ma.ehtp.gestionrisqueit.phase1.services.ObjectiveService;
import ma.ehtp.gestionrisqueit.phase1.services.StrategicOrientationsService;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskEvent;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.modelles.dto.RisqueAvancement;
import ma.ehtp.gestionrisqueit.phase3.services.RiskEventService;
import ma.ehtp.gestionrisqueit.phase3.services.RiskMappingService;
import ma.ehtp.gestionrisqueit.phase4.modelles.MetricsOfRiskGovernance;
import ma.ehtp.gestionrisqueit.phase4.modelles.dto.ControlesAssuranceQualiteDTO;
import ma.ehtp.gestionrisqueit.phase4.services.ControlesAssuranceQualiteService;
import ma.ehtp.gestionrisqueit.phase5.modelles.ObjectifProgression;
import ma.ehtp.gestionrisqueit.phase5.modelles.ProblemDescription;
import ma.ehtp.gestionrisqueit.phase5.modelles.dto.KriMeasuresDTO;
import ma.ehtp.gestionrisqueit.phase5.services.KriMeasuresService;
import ma.ehtp.gestionrisqueit.phase5.services.ObjectifProgressionService;
import ma.ehtp.gestionrisqueit.phase5.services.ProblemDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Phase5Etape2 {


    @Autowired
    OrganizationService organizationService;

    @Autowired
    KriMeasuresService kriMeasuresService;
    @Autowired
    ObjectifProgressionService objectifProgressionService;
    @Autowired
    ProblemDescriptionService problemDescriptionService;
    @Autowired
    RiskMappingService riskMappingService;
    @Autowired
    RiskEventService riskEventService;



    Organization organization;


    @GetMapping("/update_risk_mapping")
    public String update_risk_mapping(HttpSession session , Model model){
        initOrg(session);

        List<RiskMapping> riskMappingList = riskMappingService.findByOrganizationWithoutSP(organization);
        model.addAttribute("riskMappingList" , riskMappingList);

        String level;
        try {
            level = implementationInfoService.findByOrganization(organization).getLevelofdetails();
        }catch (Exception e){
            U.ptxt("get Cartographie_des_risques try to find level e :"+e.getMessage());

            level = "null";
        }

        model.addAttribute("level" , level);

        return "phase3/etape1/Cartographie_des_risques";

    }
    @PostMapping("/update_risk_mapping")
    public ResponseEntity<ResponseMessage> save_update_risk_mapping(@RequestBody List<RiskMapping> riskMappingList , HttpSession session){

        initOrg(session);

        String message = "";
        try {

            riskMappingList.forEach(riskMapping -> riskMapping.setOrganization(organization));
            riskMappingService.updateAll(riskMappingList);

            message = "save successfully: ";

            //   riskMappingService.findAll().forEach(concurrent -> System.out.println(concurrent));

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";
            U.ptxt(message +" risk Mapping "+ e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }



    @GetMapping("/update_measures_kri")
    public String update_measures_kri(HttpSession session , Model model){
        initOrg(session);

        List<KriMeasuresDTO> kriMeasuresDTOList = kriMeasuresService.findByOrganization(organization);
        model.addAttribute("kriMeasuresDTOList" ,kriMeasuresDTOList);



        return "phase5/etape2/update_measures_kri";
    }

    @PostMapping("/update_measures_kri")
    public ResponseEntity<ResponseMessage> save_update_measures_kri(@RequestBody List<KriMeasuresDTO> kriMeasuresDTOList){



        String message = "";
        try {


            kriMeasuresService.saveAll(kriMeasuresDTOList);


            message = "save successfully: ";

            //   riskMappingService.findAll().forEach(concurrent -> System.out.println(concurrent));

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";
            U.ptxt(message +" risk Mapping "+ e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }



    @GetMapping("/objectif_progression")
    public String objectif_progression(HttpSession session , Model model){
        initOrg(session);
        List<ObjectifProgression> objectifProgressionList = objectifProgressionService.findByOrganization(organization);
        model.addAttribute("objectifProgressionList" , objectifProgressionList);




        List<RisqueAvancement> raList = new ArrayList<>();
        riskMappingService.findByOrganizationWithoutSP(organization).forEach(riskMapping -> {

                    raList.add(new RisqueAvancement(riskMapping));
                }
        );

        model.addAttribute("raList" ,raList);
        return "phase5/etape2/objectif_progression";
    }

    @PostMapping("/objectif_progression")
    public ResponseEntity<ResponseMessage> save_objectif_progression(@RequestBody List<ObjectifProgression> objectifProgressionList){



        String message = "";
        try {

            U.ptxt("lenght : "+objectifProgressionList.size());

            objectifProgressionService.deleteAll(
                    objectifProgressionService.findByOrganization(organization)
            );

            objectifProgressionList.forEach(objectifProgression ->{

                        objectifProgression.setOrganization(organization);
                        objectifProgression.setId(null);

                    }
            );
            objectifProgressionService.saveAll(objectifProgressionList);


            message = "save successfully: ";

            //   riskMappingService.findAll().forEach(concurrent -> System.out.println(concurrent));

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";
            U.ptxt(message +" risk Mapping "+ e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }



    @GetMapping("/problem_description")
    public String problem_description(Model model , HttpSession session){

        initOrg(session);
        ProblemDescription problemDescription = problemDescriptionService.findByOrganization(organization);
        if (problemDescription != null){
            U.ptxt("get");
            U.p(problemDescription);
            model.addAttribute("ProblemDescription" ,problemDescription );

        }
        else
            model.addAttribute("ProblemDescription" ,new ProblemDescription() );


        return "phase5/etape2/problemDescription";
    }



    @PostMapping("/problem_description")
    public ResponseEntity<ResponseMessage> save_problem_description(@RequestBody ProblemDescription problemDescription , Model model , HttpSession session){
        initOrg(session);
        String message = "";

        //storageService.setSousFolder("newOrg2");
        try {
            try {
                problemDescriptionService.delete(
                        problemDescriptionService.findByOrganization(organization)
                );
            }catch (Exception e){
                U.ptxt("delete faild e : "+e.getMessage());
            }
            U.ptxt("description : "+problemDescription.getDescription());
            problemDescription.setOrganization(organization);
            U.p(problemDescription);
            U.ptxt("save");
            problemDescription = problemDescriptionService.save(problemDescription);
            model.addAttribute("ProblemDescription" ,problemDescription);
            message = "save the stakeholders successfully: " ;


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save the stakeholders!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @Autowired
    ImplementationInfoService implementationInfoService;

    @GetMapping("/Livrable_phase5_etape2")
    public String Livrable_phase5_etape2(HttpSession session , Model model){
        initOrg(session);


        List<RiskMapping> rm = riskMappingService.findByOrganizationWithoutSP(organization);
        model.addAttribute("rm" , rm);

        List<KriMeasuresDTO> kriMeasuresDTOList = kriMeasuresService.findByOrganization(organization);
        model.addAttribute("kriMeasuresDTOList" ,kriMeasuresDTOList);

        List<RiskEvent> riskEventList = riskEventService.findByOrganizationOrderByImpacteMAD(organization);
        int lastIndex = 10 > riskEventList.size() ? riskEventList.size() : 10;
        model.addAttribute("riskEventList" , riskEventList.subList(0,lastIndex));


        List<ObjectifProgression> objectifProgressionList = objectifProgressionService.findByOrganization(organization);
        model.addAttribute("objectifProgressionList" , objectifProgressionList);

        ProblemDescription problemDescription = problemDescriptionService.findByOrganization(organization);

        model.addAttribute("ProblemDescription" ,problemDescription );


        for (KriMeasuresDTO kri:
                kriMeasuresDTOList) {
                riskMappingService.findById(kri.getRmId()).ifPresent(riskMapping ->{
                    MetricsOfRiskGovernance m = riskMapping.getMetricsOfRiskGovernance();
                    kri.setFormuleDeCalcul(m.getFormuleDeCalcul());
                    kri.setEntiteProductrice(m.getEntiteProductrice());
                    kri.setPeriodiciteDeMesure(m.getPeriodiciteDeMesure());
                    kri.setProcessusDObtention(m.getProcessusDObtention());
                } );

        }


        model.addAttribute("kriMeasuresDTOListUpd" ,kriMeasuresDTOList);

        try {
            ImplementationInfo imp =  implementationInfoService.findByOrganization(organization);
            if (imp != null){
                U.ptxt("ImplementationInfo id : "+imp.getId()+" orgid : "+imp.getOrganization().getId());
                imp.setP5e2(true);
                imp.setP5e3(true);
                implementationInfoService.save(imp);
            }

        }catch (Exception e){
            U.ptxt("ImplementationInfo save error  e : "+e.getMessage());
        }


        return "phase5/etape2/Livrable_phase5_etape2";
    }


    public void initOrg(HttpSession session){


        try {
            U.ptxt("try load organization from   session");
            this.organization = (Organization) session.getAttribute("organization");

            if (this.organization != null) {

                session.setAttribute("organization", this.organization);
            }else {
                U.ptxt("loaded  from session but it's null");
                U.ptxt("try load organization from   Dtabase");

                List<Organization> organizationList = organizationService.findAll() ;
                this.organization = organizationList.get(organizationList.size() -1);
                if (this.organization != null)
                    session.setAttribute("organization" , this.organization );
                else
                    this.organization = organizationService.save(new Organization(null,"defaultOrg" ,null));

            }

        } catch (Exception exception) {
            U.ptxt("Exception while trying to load organization from   session");

            List<Organization> organizationList = organizationService.findAll() ;
            this.organization = organizationList.get(organizationList.size() -1);
            if (this.organization != null)
                session.setAttribute("organization" , this.organization );
            else
                this.organization = organizationService.save(new Organization(null,"defaultOrg" ,null));

        }



        U.ptxt("initOrg function : organization id = "+organization.getId() );

    }

}
