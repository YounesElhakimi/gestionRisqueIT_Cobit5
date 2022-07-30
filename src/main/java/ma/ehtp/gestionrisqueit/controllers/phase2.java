package ma.ehtp.gestionrisqueit.controllers;


import ma.ehtp.gestionrisqueit.modelles.MajorFact;
import ma.ehtp.gestionrisqueit.modelles.MethodologyToHandleRisk;
import ma.ehtp.gestionrisqueit.modelles.Organization;
import ma.ehtp.gestionrisqueit.services.ConcurrentService;
import ma.ehtp.gestionrisqueit.services.MajorFactService;
import ma.ehtp.gestionrisqueit.services.MethodologyToHandleRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class phase2 {
    Organization organization = null;
    @Autowired
    MethodologyToHandleRiskService methodologyToHandleRiskService;

    @Autowired
    MajorFactService majorFactService;

    @Autowired
    ConcurrentService concurrentService;


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
        methodologyToHandleRiskService.findAll().forEach(methodologyToHandleRisk1 -> System.out.println(methodologyToHandleRisk1));
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


        majorFactService.findAll().forEach(majorFact -> System.out.println(majorFact));

        return new RedirectView("/concurrentsMajorRisks");



    }

    @GetMapping("/concurrentsMajorRisks")
    public String concurrentsMajorRisks(){
    return  "concurrentsMajorRisks";
    }












}
