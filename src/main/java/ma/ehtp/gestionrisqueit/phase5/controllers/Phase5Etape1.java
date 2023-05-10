package ma.ehtp.gestionrisqueit.phase5.controllers;

import ma.ehtp.gestionrisqueit.phase0.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase1.modelles.ImplementationInfo;
import ma.ehtp.gestionrisqueit.phase1.services.ImplementationInfoService;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskEvent;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.modelles.dto.RisqueAvancement;
import ma.ehtp.gestionrisqueit.phase3.services.RiskEventService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase3.services.RiskMappingService;
import ma.ehtp.gestionrisqueit.phase4.modelles.CommunicationPlan;
import ma.ehtp.gestionrisqueit.phase4.modelles.dto.ControlesAssuranceQualiteDTO;
import ma.ehtp.gestionrisqueit.phase4.services.ControlesAssuranceQualiteService;
import ma.ehtp.gestionrisqueit.phase5.modelles.BusinessContinuityPlan;
import ma.ehtp.gestionrisqueit.phase5.services.BusinessContinuityPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Phase5Etape1 {

    @Autowired
    BusinessContinuityPlanService businessContinuityPlanService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    RiskEventService riskEventService;

    @Autowired
    ControlesAssuranceQualiteService assuranceQualiteService;

    Organization organization;

    @GetMapping("/business_continuity_plan")
    public String business_bontinuity_plan(Model model , HttpSession session){
        initOrg(session);

        BusinessContinuityPlan  businessContinuityPlan = businessContinuityPlanService.findByOrganization(organization);
        model.addAttribute("entries" , businessContinuityPlan );

        return "phase5/etape1/business_continuity_plan";
    }




    @PostMapping("/business_continuity_plan")
    public ResponseEntity<ResponseMessage> save_business_bontinuity_plan(@RequestBody BusinessContinuityPlan businessContinuityPlan , HttpSession session){


        initOrg(session);

        String message = "";





        try {

            try {

                businessContinuityPlanService.delete(
                        businessContinuityPlanService.findByOrganization(organization)
                );

            }catch (Exception e){

                U.ptxt("falid to delete businessContinuityPlan  | e : "+e.getMessage());
            }

            businessContinuityPlan.setOrganization(organization);


            businessContinuityPlanService.save(businessContinuityPlan);


            message = "save successfully: ";


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";


            System.out.println("**************************************************");

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }



    @GetMapping("/update_data_about_IT_incidents")
    public String update_data_about_IT_incidents(HttpSession session , Model model){

        initOrg(session);
        List<RiskEvent> riskEventList = riskEventService.findByOrganization(organization);
        model.addAttribute("riskEventList" ,riskEventList);

        List<RisqueAvancement> raList = new ArrayList<>();
        riskMappingService.findByOrganizationWithoutSP(organization).forEach(riskMapping -> {

                    raList.add(new RisqueAvancement(riskMapping));
                }
        );

        model.addAttribute("raList" ,raList);

        return "phase3/etape2/riskEvent";
    }




    @PostMapping("/update_data_about_IT_incidents")
    public ResponseEntity<ResponseMessage> save_update_data_about_IT_incidents(@RequestBody List<RiskEvent> riskEventList , HttpSession session){


        initOrg(session);

        String message = "";





        try {
            riskEventService.deleteAll(
                    riskEventService.findByOrganization(organization)
            );

            riskEventList.forEach(riskEvent -> {
                riskEvent.setOrganization(organization);
                U.ptxt("riskEventList");
                System.out.println(riskEvent);
            });

            riskEventService.saveAll(riskEventList).forEach(riskEvent -> {
                U.ptxt("saved riskEventList");
                System.out.println(riskEvent);
            });

            message = "save successfully: ";


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";


            System.out.println("**************************************************");

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }




    @GetMapping("/update_controles_assurance_qualite")
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


    @PostMapping("/update_controles_assurance_qualite")
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


    @Autowired
    RiskMappingService riskMappingService;

    @Autowired
    ImplementationInfoService implementationInfoService;
    @GetMapping("/Livrable_phase5_etape1")
    public String Livrable_phase5_etape1(HttpSession session , Model model){
        initOrg(session);
        List<RiskEvent> riskEventList = riskEventService.findByOrganization(organization);
        model.addAttribute("riskEventList" , riskEventList);


        BusinessContinuityPlan  businessContinuityPlan = businessContinuityPlanService.findByOrganization(organization);
        model.addAttribute("businessContinuityPlan" , businessContinuityPlan );

        List<RiskMapping> riskMappingList = new ArrayList<>();

        try {
            riskMappingList = riskMappingService.findByOrganizationWithoutSP(organization);
        }catch (Exception e){
            U.ptxt("e : "+e.getMessage());
        }

        ImplementationInfo imp =  implementationInfoService.findByOrganization(organization);
        imp.setP5e1(true);
        implementationInfoService.save(imp);

        model.addAttribute("riskMappingList" , riskMappingList);



        return "phase5/etape1/Livrable_phase5_etape1";
    }

    @GetMapping("/Livrable_phase5_etape1v2")
    public String Livrable_phase5_etape1v2(HttpSession session , Model model){
        initOrg(session);

        BusinessContinuityPlan  businessContinuityPlan = businessContinuityPlanService.findByOrganization(organization);
        model.addAttribute("businessContinuityPlan" , businessContinuityPlan );
        return "phase5/etape1/Livrable_phase5_etape1v2";
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
