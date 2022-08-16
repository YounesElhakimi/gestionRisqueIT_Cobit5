package ma.ehtp.gestionrisqueit.controllers;


import ma.ehtp.gestionrisqueit.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.modelles.*;
import ma.ehtp.gestionrisqueit.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

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


    @Autowired
    ITRiskElementService itRiskElementService;

    @GetMapping("/methodologyToHandleRisk")
 public String methodologyToCollect(){

    return "methodologyToHandleRisk";
}



    @PostMapping("/methodologyToHandleRisk")
    public RedirectView saveMethodologyToCollect(@RequestBody String description){
        System.out.println("==============saveMethodologyToCollect==================");
        MethodologyToHandleRisk methodologyToHandleRisk = new MethodologyToHandleRisk(null , description.trim() , organization);
        methodologyToHandleRiskService.save(methodologyToHandleRisk);
        System.out.println("==================================================");
        System.out.println("==================================================");

        return new RedirectView("/internalMajorFact");
    }

@GetMapping("/internalMajorFact")
    public String majorFactInternal(Model model){
    String pageTitle = "Please specify the major facts related to the internal operating environment:";
    model.addAttribute("pageTitle" , pageTitle);
    model.addAttribute("next" ,"externalMajorFact" );
    return "majorFact";
}

    @PostMapping("/internalMajorFact")
    public RedirectView saveMajorFactInternal(@RequestBody List<MajorFact> majorFactList){

        majorFactList.forEach(majorFact -> majorFact.setOrganization(organization));
        majorFactService.saveAllInternal(majorFactList);

        return new RedirectView("/externalMajorFact");

    }


    @GetMapping("/externalMajorFact")
    public String majorFactExternal(Model model){
        String pageTitle = "Please specify the major facts related to the external environment:";
        model.addAttribute("pageTitle" , pageTitle);
        model.addAttribute("next" ,"concurrentsMajorRisks" );

        return "majorFact";
    }

    @PostMapping("/externalMajorFact")
    public RedirectView saveMajorFactExternal(@RequestBody List<MajorFact> majorFactList){


        majorFactList.forEach(majorFact -> majorFact.setOrganization(organization));
        majorFactService.saveAllExternal(majorFactList);



        return new RedirectView("/concurrentsMajorRisks");



    }

    @GetMapping("/concurrentsMajorRisks")
    public String concurrentsMajorRisks(){
    return  "concurrentsMajorRisks";
    }


    @PostMapping("/concurrentsMajorRisks")
    public ResponseEntity<ResponseMessage> saveConcurrentsMajorRisks(@RequestBody List<Concurrent> concurrentList){


        String message = "";
        try {
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
       public String potentialRisksOfTheCompany(){



        return  "potentialRisksOfTheCompany";
       }


    @PostMapping("/potentialRisksOfTheCompany")
    public ResponseEntity<ResponseMessage> savePotentialRisksOfTheCompany(@RequestBody List<PotentialRisksOfTheCompany> potentialRisksOfTheCompanyList){


        String message = "";
        try {
            potentialRisksOfTheCompanyList.forEach(potentialRisksOfTheCompany -> {

                potentialRisksOfTheCompany.setOrganization(organization);
            });

            potentialRisksOfTheCompanyService.saveAll(potentialRisksOfTheCompanyList);


            message = "save successfully: ";


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/ITRiskElement")
    public String  iTRiskElement(){

        return "ITRiskElement";
    }


    @PostMapping("/ITRiskElement")
    public ResponseEntity<ResponseMessage> saveITRiskElement(@RequestBody List<ITRiskElement> itRiskElementList){


        String message = "";
        try {
            itRiskElementList.forEach(itRiskElement -> {

                itRiskElement.setOrganization(organization);
            });

            itRiskElementService.saveAll(itRiskElementList);


            message = "save successfully: ";


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/LivrablePhase2")
    public String livrablePhase2(){


        return "Livrable_phase2";
    }






}
