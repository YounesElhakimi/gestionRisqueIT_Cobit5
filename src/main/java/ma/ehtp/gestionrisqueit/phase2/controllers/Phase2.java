package ma.ehtp.gestionrisqueit.phase2.controllers;


import ma.ehtp.gestionrisqueit.phase0.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase1.modelles.ImplementationInfo;
import ma.ehtp.gestionrisqueit.phase1.services.ImplementationInfoService;
import ma.ehtp.gestionrisqueit.phase2.modelles.*;
import ma.ehtp.gestionrisqueit.phase2.services.*;
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
import java.util.List;

@Controller
public class Phase2 {
    Organization organization = null;

    @Autowired
    MethodologyToHandleRiskService methodologyToHandleRiskService;

    @Autowired
    MajorFactService majorFactService;


    @Autowired
    ConcurrentService concurrentService;

    @Autowired
    PotentialRisksOfTheCompanyService potentialRisksOfTheCompanyService;



    @GetMapping("/methodologyToHandleRisk")
    public String methodologyToCollect(HttpSession session , Model model){
        initOrg(session);
        MethodologyToHandleRisk methodologyToHandleRisk = methodologyToHandleRiskService.findByOrganization(organization);
      if (methodologyToHandleRisk == null)
          methodologyToHandleRisk= new MethodologyToHandleRisk();

       model.addAttribute("MethodologyToHandleRisk" , methodologyToHandleRisk);


    return "phase2/methodologyToHandleRisk";
    }



    @PostMapping("/methodologyToHandleRisk")
    public ResponseEntity<ResponseMessage> saveMethodologyToCollect(@RequestBody MethodologyToHandleRisk methodologyToHandleRisk , HttpSession session){

        initOrg(session);
        String message = "";
        try {
            message = "save successfully: ";

            try {
                methodologyToHandleRiskService.delete(
                        methodologyToHandleRiskService.findByOrganization(organization)
                );

            }catch (Exception e){
                U.ptxt("faild to delete  methodologyToHandleRisk e : " + e.getMessage());
            }

            methodologyToHandleRisk.setOrganization(organization);
            methodologyToHandleRisk = methodologyToHandleRiskService.save(methodologyToHandleRisk);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }catch (Exception e){
            message = "Fail to save!";
            U.ptxt("error .............................." + e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }


    }

@GetMapping("/internalMajorFact")
    public String majorFactInternal(Model model , HttpSession session){
        initOrg(session);


    String pageTitle = "Please specify the major facts related to the internal operating environment:";
    model.addAttribute("pageTitle" , pageTitle);
    model.addAttribute("next" ,"externalMajorFact" );
    model.addAttribute("MajorFactList" ,majorFactService.findByOrganizationAndIsInternal(organization ,true));
    return "phase2/majorFact";
}

    @PostMapping("/internalMajorFact")
    public ResponseEntity<ResponseMessage>  saveMajorFactInternal(@RequestBody List<MajorFact> majorFactList){



        String message = "";
        try {
            message = "save successfully: ";
            majorFactService.deleteAll(majorFactService.findByOrganizationAndIsInternal(organization ,true));
            majorFactList.forEach(majorFact -> majorFact.setOrganization(organization));
            majorFactService.saveAllInternal(majorFactList);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        }catch (Exception e){
            message = "Fail to save!";
            U.ptxt("error .............................." + e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }


    @GetMapping("/externalMajorFact")
    public String majorFactExternal(Model model , HttpSession session){
        initOrg(session);
        String pageTitle = "Please specify the major facts related to the external environment:";
        model.addAttribute("pageTitle" , pageTitle);
        model.addAttribute("next" ,"concurrentsMajorRisks" );
        model.addAttribute("MajorFactList" ,majorFactService.findByOrganizationAndIsInternal(organization ,false));

        return "phase2/majorFact";
    }

    @PostMapping("/externalMajorFact")
    public ResponseEntity<ResponseMessage>  save_MajorFactExternal(@RequestBody List<MajorFact> majorFactList){



        String message = "";
        try {
            message = "save successfully: ";
            majorFactService.deleteAll(majorFactService.findByOrganizationAndIsInternal(organization ,false));
            majorFactList.forEach(majorFact -> majorFact.setOrganization(organization));
            majorFactService.saveAllExternal(majorFactList);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        }catch (Exception e){
            message = "Fail to save!";
            U.ptxt("error .............................." + e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }

    @GetMapping("/concurrentsMajorRisks")
    public String concurrentsMajorRisks(HttpSession session,Model model){
        initOrg(session);
        model.addAttribute("ConcurrentList" , concurrentService.findByOrganization(organization));
    return  "phase2/concurrentsMajorRisks";
    }


    @PostMapping("/concurrentsMajorRisks")
    public ResponseEntity<ResponseMessage> saveConcurrentsMajorRisks(@RequestBody List<Concurrent> concurrentList){


        String message = "";
        try {

            try {
                concurrentService.deleteAll(
                        concurrentService.findByOrganization(organization)
                );
            }catch (Exception e){
                U.ptxt("faild to delet the old conccurent e : "+e.getMessage());
            }

            concurrentList.forEach(concurrent -> {

                concurrent.setOrganization(organization);
            });

            concurrentService.saveAll(concurrentList);


            message = "save successfully: ";


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


       @GetMapping("/potentialRisksOfTheCompany")
       public String potentialRisksOfTheCompany(HttpSession session,Model model){
        initOrg(session);
        model.addAttribute("PotList" ,potentialRisksOfTheCompanyService.findAByOrganization(organization));

        return  "phase2/potentialRisksOfTheCompany";
       }


    @PostMapping("/potentialRisksOfTheCompany")
    public ResponseEntity<ResponseMessage> savePotentialRisksOfTheCompany(@RequestBody List<PotentialRisksOfTheCompany> potentialRisksOfTheCompanyList){


        String message = "";
        try {
            try {
                potentialRisksOfTheCompanyService.deletAllByOrganization(organization);

            }catch (Exception e){
                U.ptxt("faild to delete  e : "+ e.getMessage());
            }
            potentialRisksOfTheCompanyList.forEach(potentialRisksOfTheCompany -> {

                potentialRisksOfTheCompany.setOrganization(organization);
            });

            potentialRisksOfTheCompanyService.saveAll(potentialRisksOfTheCompanyList);


            message = "save successfully: ";


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";
            U.ptxt(message +" e : "+ e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @Autowired
    ITRiskElementSourceService sourceService;
    @Autowired
    ImplementationInfoService implementationInfoService;

    @GetMapping("/ITRiskElement")
    public String  iTRiskElement(HttpSession session,Model model){
        initOrg(session);
        model.addAttribute("ITRiskElementList" ,sourceService.findByOrganization(organization));


        String level;
        try {
            level = implementationInfoService.findByOrganization(organization).getLevelofdetails();

        }catch (Exception e){
            U.ptxt("get Cartographie_des_risques try to find level e :"+e.getMessage());

            level = "nul";
        }

        model.addAttribute("level" , level);
        return "phase2/ITRiskElement";
    }


    @PostMapping("/ITRiskElement")
    public ResponseEntity<ResponseMessage> saveITRiskElement(@RequestBody List<ITRiskElementSource> itRiskElementList , HttpSession session){

        initOrg(session);
        String message = "";
        try {

            try {
                sourceService.deleteAll(sourceService.findByOrganization(organization));
            }catch (Exception e){
                U.ptxt("note deleted");
                U.ptxt("faild to delet ITRiskElement sours e :"+e.getMessage());
            }

            itRiskElementList.forEach(itRiskElement -> {

                itRiskElement.setOrganization(organization);
            });

            sourceService.saveAll(itRiskElementList);


            message = "save successfully: ";


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/LivrablePhase2")
    public String livrablePhase2(HttpSession session , Model model){

        initOrg(session);
        MethodologyToHandleRisk methodologyToHandleRisk = methodologyToHandleRiskService.findByOrganization(organization);
        model.addAttribute("MethodologyToHandleRisk" , methodologyToHandleRisk);
        model.addAttribute("MajorFactListInternal" ,majorFactService.findByOrganizationAndIsInternal(organization ,true));
        model.addAttribute("MajorFactListExternal" ,majorFactService.findByOrganizationAndIsInternal(organization ,false));
        model.addAttribute("ConcurrentList" , concurrentService.findByOrganization(organization));
        model.addAttribute("potentialRisks" ,potentialRisksOfTheCompanyService.findAByOrganization(organization));
        model.addAttribute("ITRiskElementList" ,sourceService.findByOrganization(organization));

        ImplementationInfo imp =  implementationInfoService.findByOrganization(organization);
        imp.setP2(true);
        implementationInfoService.save(imp);
        return "phase2/Livrable_phase2";
    }



    @Autowired
    OrganizationService organizationService;


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
