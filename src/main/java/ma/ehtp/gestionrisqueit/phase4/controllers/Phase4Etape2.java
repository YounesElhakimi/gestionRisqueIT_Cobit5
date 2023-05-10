package ma.ehtp.gestionrisqueit.phase4.controllers;


import ma.ehtp.gestionrisqueit.phase0.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase1.modelles.ImplementationInfo;
import ma.ehtp.gestionrisqueit.phase1.services.ImplementationInfoService;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.repositories.RiskMappingRepository;
import ma.ehtp.gestionrisqueit.phase3.services.RiskEventService;
import ma.ehtp.gestionrisqueit.phase3.services.RiskMappingService;
import ma.ehtp.gestionrisqueit.phase4.modelles.CommunicationPlan;
import ma.ehtp.gestionrisqueit.phase4.modelles.MechanismsDescription;
import ma.ehtp.gestionrisqueit.phase4.modelles.MetricsOfRiskGovernance;
import ma.ehtp.gestionrisqueit.phase4.modelles.dto.ControlesAssuranceQualiteDTO;
import ma.ehtp.gestionrisqueit.phase4.modelles.dto.MetricsOfRiskGovernanceDTO;
import ma.ehtp.gestionrisqueit.phase4.services.CommunicationPlanServive;
import ma.ehtp.gestionrisqueit.phase4.services.ControlesAssuranceQualiteService;
import ma.ehtp.gestionrisqueit.phase4.services.MechanismsDescriptionService;
import ma.ehtp.gestionrisqueit.phase4.services.MetricsOfRiskGovernanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Phase4Etape2 {


    @Autowired
    CommunicationPlanServive communicationPlanServive;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    MechanismsDescriptionService mechanismsDescriptionService;

    @Autowired
    MetricsOfRiskGovernanceService metricsOfRiskGovernanceService;

    @Autowired
    ControlesAssuranceQualiteService assuranceQualiteService;


    @Autowired
    RiskMappingService riskMappingService;

    @Autowired
    RiskEventService riskEventService;



    Organization organization = null;


    @Autowired
    ImplementationInfoService implementationInfoService;

    @GetMapping("/controles_assurance_qualite")
    public String controles_assurance_qualite(HttpSession session , Model model){

        initOrg(session);


        List<ControlesAssuranceQualiteDTO> caqList = assuranceQualiteService.findByOrganization(organization);

        model.addAttribute("caqList" , caqList);
        String level;
        try {
            level = implementationInfoService.findByOrganization(organization).getLevelofdetails();

        }catch (Exception e){
            U.ptxt("get Cartographie_des_risques try to find level e :"+e.getMessage());

            level = "null";
        }

        model.addAttribute("level" , level);


        return "phase4/etape2/controles_assurance_qualite";
    }


    @PostMapping("/controles_assurance_qualite")
    public ResponseEntity<ResponseMessage> save_controles_assurance_qualite(@RequestBody List<ControlesAssuranceQualiteDTO> caqList ,HttpSession session){
        U.ptxt("Controller controles_assurance_qualite");
        U.pgt();
        System.out.println(caqList.get(0));
        U.pet();


        initOrg(session);


        String message = "";





        try {



            message = "save successfully: ";

            assuranceQualiteService.saveAll(caqList);

            U.ptxt("ok ..............................");
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";

            U.ptxt("error .............................." + e.getMessage());



            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

        }
    }



    @GetMapping("/Livrable_phase4_etape2")
    public String Livrable_phase4_etape2(HttpSession session , Model model){

        initOrg(session);
        try {

            model.addAttribute("risqueParCat" , riskMappingService.countAllByCategory(organization));
            model.addAttribute("risqueParFreAndImp" , riskMappingService.countAllByFrequenceAndImpact(organization));
            model.addAttribute("topRisques" , riskMappingService.findByOrganizationOrderByExpositionDesc(organization));
            model.addAttribute("topIncedent" ,riskEventService.findByOrganizationOrderByImpacteMAD(organization));
            model.addAttribute("countRisqueParEfeca" , riskMappingService.countAllByEfficacite(organization));
            model.addAttribute("level" , implementationInfoService.findByOrganization(organization).getLevelofdetails());
            model.addAttribute("zoomSurLesRisque" , riskMappingService.zoomSurLesRisque(organization));
            model.addAttribute("caqOldNew",assuranceQualiteService.findOldAndNew(organization));
            model.addAttribute("risquesDevenuCritique" , riskMappingService.findAllDevenuCritique(organization));
            List<RiskMapping> rm = riskMappingService.findByOrganizationWithoutSP(organization);
            model.addAttribute("rm" , rm);
        }catch (Exception e){
U.ptxt("e : "+e.getMessage());
        }

        ImplementationInfo imp =  implementationInfoService.findByOrganization(organization);
        imp.setP4e2(true);
        implementationInfoService.save(imp);

        return "phase4/etape2/Livrable_phase4_etape2";
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
