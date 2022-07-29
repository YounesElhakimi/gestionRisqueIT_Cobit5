package ma.ehtp.gestionrisqueit.controllers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.modelles.DescTypes;
import ma.ehtp.gestionrisqueit.modelles.Description;
import ma.ehtp.gestionrisqueit.modelles.Organization;
import ma.ehtp.gestionrisqueit.services.DescriptionService;
import ma.ehtp.gestionrisqueit.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class Phase1 {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    DescriptionService descriptionService;

    @GetMapping("")
    public String index(){

        return "index";
    }



    @GetMapping("/newOrg")
    public String newOrgpage(){

        return "neworganization";
    }

    @PostMapping("/newOrg")
    public RedirectView createNewOrg(@RequestBody String orgName){
        Organization org = new Organization(null , orgName);
        Organization o = organizationService.save(org);
        if (o == null ){

            System.out.println("============================================");
            System.out.println("null");
            System.out.println("============================================");

        }else {
            System.out.println("============================================");
            System.out.println(o);
            System.out.println("============================================");

        }
        return new RedirectView("allsteps");
    }

    @GetMapping("/allsteps")
    public String allsteps(){
        System.out.println("============================================");
        organizationService.save(new Organization(null , "test1"));
        organizationService.save(new Organization(null , "test2"));

        organizationService.findAll().forEach(organization -> System.out.println(organization));
        System.out.println("============================================");

        return "allsteps";
    }

    @GetMapping("/strategicOrientations")
    public String StrategicOrientations(){

        return "strategicOrientations";
    }


    @PostMapping("/strategicOrientations")
    public void saveStrategicOrientations(@RequestBody List<Description> soList){

        System.out.println("============================================");
        System.out.println("Post strategicOrientations");

        System.out.println("============================================");
        soList.forEach(description -> System.out.println(description));

        soList.forEach(description -> {
            description.setPhase(1);
            description.setType(DescTypes.STRATEGIC_ORIENTATIONS);
        });

        descriptionService.saveAll(soList);


        List<Description> sol = descriptionService.findAll();
        sol.forEach(description -> System.out.println(description));


    }

    @GetMapping("/majorrisks")
    public String majorRisks(){

        return "majorrisks";
    }


    @PostMapping("/majorrisks")
    public void saveMajorRisks(@RequestBody List<Description> soList){

        System.out.println("============================================");
        System.out.println("Post strategicOrientations");

        System.out.println("============================================");
        soList.forEach(description -> System.out.println(description));

        soList.forEach(description -> {
            description.setPhase(1);
            description.setType(DescTypes.MAJOR_RISKS);
        });

        descriptionService.saveAll(soList);


        List<Description> sol = descriptionService.findAll();
        sol.forEach(description -> System.out.println(description));


    }

    @GetMapping("/description")
    public String description(){

        return "description";
    }

    @PostMapping("/description")
    public String saveDescription(@RequestBody String description){
        Description desc = new Description(null , 1 , DescTypes.DESCRIPTION , "description de phase 1" , description);
        descriptionService.save(desc);
        return "allsteps";
    }

    @GetMapping("/uploadfiles/documents")
    public String uploadfilesDocs(Model model){
        String title = "Please specify the different documents needed for the implementation of the roadmap:";
       model.addAttribute("title" , title);
        return "uploadfiles" ;
    }

    @PostMapping("/uploadfiles/documents")
    public String saveUploadfilesDocs(){
        System.out.println("===============================");
        System.out.println("  Post ");
        System.out.println("===============================");
        return "ok";
    }










}


