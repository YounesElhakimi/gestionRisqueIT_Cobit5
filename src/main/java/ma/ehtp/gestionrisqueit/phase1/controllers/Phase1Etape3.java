package ma.ehtp.gestionrisqueit.phase1.controllers;


import ma.ehtp.gestionrisqueit.phase0.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase1.modelles.*;
import ma.ehtp.gestionrisqueit.phase1.services.*;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Phase1Etape3 {

    @Autowired
    OrganizationService organizationService;



    @Autowired
    TeamProjectMemberService teamProjectMemberService;


    @Autowired
    StakeholdersService stakeholdersService;

    @Autowired
    ImplementationInfoService implementationInfoService;

    @Autowired
    StrategicOrientationsService strategicOrientationsService;


    @Autowired
    MajorRisksService majorRisksService;


   @Autowired
           ObjectiveService objectiveService;

    Organization organization = null;



    @GetMapping("/levelofdetails")
    public String levelOfDetails(HttpSession session ,Model model){

        initOrg(session);
        String l = "";
        try {
            l = implementationInfoService.findByOrganization(organization).getLevelofdetails();
        }catch (Exception e){

            U.ptxt("level is null    e : "+e.getMessage());
        }
        model.addAttribute("level" ,"level"+l);

        return "phase1/etape3/levelofdetails";
    }

   @PostMapping("/levelofdetails")
    public ResponseEntity<ResponseMessage> saveLevelOfDetails(@RequestBody ImplementationInfo implementationInfo, Model model ,HttpSession session){

       initOrg(session);


       String message = "";
       //storageService.setSousFolder("newOrg2");
       try {
           ImplementationInfo ii = implementationInfoService.findByOrganization(organization);
           ii.setLevelofdetails(implementationInfo.getLevelofdetails());
           implementationInfoService.save(ii);
           message = "save the stakeholders successfully: " ;
           return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
       } catch (Exception e) {
           message = "Fail to save the stakeholders!";
           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
       }    }

    @GetMapping("/levelofdetailsSaved")
    public String savedLevelOfDetails(Model model , HttpSession session){
        initOrg(session);
        try {
            String l = implementationInfoService.findByOrganization(organization).getLevelofdetails();
            if (!(l == null)){
                model.addAttribute("level" ,"level"+l);
                System.out.println("levelofdetailsSaved  level"+l);
            }
        }catch(Exception e) {
            model.addAttribute("level" ,"level"+4);

            System.out.println("exeptiont => default levelofdetailsSaved  4  || e :"+e.getMessage());


        }

        return "phase1/etape3/levelofdetailsSaved";
    }

    @GetMapping("/methodologicalReference")
    public String methodologicalReference(HttpSession session , Model model){
        initOrg(session);
        try {
            String l = implementationInfoService.findByOrganization(organization).getLevelofdetails();
            if (!(l == null)){
                model.addAttribute("level" ,"level"+l);
                System.out.println("levelofdetailsSaved  level"+l);
            }
        }catch(Exception e) {
            model.addAttribute("level" ,"level"+4);
            System.out.println("exeptiont => default levelofdetailsSaved  4  || e :"+e.getMessage());
        }

        ImplementationInfo imp =  implementationInfoService.findByOrganization(organization);
        imp.setP1e3(true);
        implementationInfoService.save(imp);

        return  "phase1/etape3/methodologicalReference";
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


