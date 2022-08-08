package ma.ehtp.gestionrisqueit.controllers;


import ma.ehtp.gestionrisqueit.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.modelles.*;
import ma.ehtp.gestionrisqueit.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class Phase1 {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    DescriptionService descriptionService;

    @Autowired
    TeamProjectMemberService teamProjectMemberService;


    @Autowired
    StakeholdersService stakeholdersService;

    @Autowired
    ImplementationInfoService implementationInfoService;

    Organization organization = null;

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
        organization = new Organization();
        organization.setName(orgName);
        organization = organizationService.save(organization);


        ImplementationInfo implementationInfo = implementationInfoService.save(new ImplementationInfo());
        organization.setImplementationInfo(implementationInfo);
        organizationService.save(organization);
        // organization.setImplementationInfo(implementationInfo);


        //implementationInfoService.save(implementationInfo);

        if (organization == null ){

            System.out.println("============================================");
            System.out.println("null");
            System.out.println("============================================");

        }else {
            System.out.println("============================================");
            System.out.println(organization);
            System.out.println("============================================");

        }
        return new RedirectView("allsteps");
    }

    @GetMapping("/allsteps")
    public String allsteps(){
        //organizationService.findAll().forEach(organization -> System.out.println(organization));
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
            description.setOrganization(organization);
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
            description.setOrganization(organization);
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
        Description desc = new Description(null , 1 , DescTypes.DESCRIPTION , "description de phase 1" , description , organization);
        descriptionService.save(desc);
        return "allsteps";
    }

    @GetMapping("/team_project")
    public String teamProject(){

        return "teamProject";
    }


    @PostMapping(value={"/team_project" })
    public ResponseEntity<ResponseMessage> SaveteamProject(@RequestBody List<TeamProjectMember> tpmList) {
        String message = "";
        //storageService.setSousFolder("newOrg2");
        try {
            tpmList.forEach(teamProjectMember -> {
                teamProjectMember.setOrganization(organization);
            });

            teamProjectMemberService.saveAll(tpmList);
            System.out.println("==========================================");
            teamProjectMemberService.findAll().forEach(teamProjectMember -> System.out.println(teamProjectMember));
            System.out.println("==========================================");


            message = "save Team Project Members uccessfully: " ;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail tosave Team Project Members !";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/stakeholders")
    public String stakeholders(){

        return "stakeholders";
    }


    @PostMapping(value={"/stakeholders" })
    public ResponseEntity<ResponseMessage> saveStakeholders(@RequestBody List<Stakeholders> stakeholdersList) {
        String message = "";
        //storageService.setSousFolder("newOrg2");
        try {
            stakeholdersList.forEach(teamProjectMember -> {
                teamProjectMember.setOrganization(organization);
            });

            stakeholdersService.saveAll(stakeholdersList);


            message = "save the stakeholders successfully: " ;

            System.out.println("==========================================");
            stakeholdersService.findAll().forEach(stakeholders -> System.out.println(stakeholders));
            System.out.println("==========================================");


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save the stakeholders!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/phasesGanttDiagram")
    public String phasesGanttDiagram(){

        return  "phasesGanttDiagram";
    }

    @GetMapping("/Projectscopingnote")
    public String projectScopingNote(){

        return "Projectscopingnote";
    }

    @GetMapping("/levelofdetails")
    public String levelOfDetails(){

        return "levelofdetails";
    }

   @PostMapping("/levelofdetailsSaved")
    public String saveLevelOfDetails(@RequestBody String levelofdetails, Model model){



        try {

            organization.getImplementationInfo().setLevelofdetails(levelofdetails);

           // organizationService.save(organization);

            System.out.println(organization);

            model.addAttribute("level" ,"level"+levelofdetails);
            System.out.println("Post saved  level"+levelofdetails);

        }

        catch (Exception e){

            model.addAttribute("level" ,"level"+levelofdetails);
            System.out.println("Post not saved  level"+levelofdetails);
        }







       return "levelofdetailsSaved";
    }

    @GetMapping("/levelofdetailsSaved")
    public String savedLevelOfDetails(Model model){
        try {
            String l = organization.getImplementationInfo().getLevelofdetails();
            if (!(l == null)){
                model.addAttribute("level" ,"level"+l);
                System.out.println("levelofdetailsSaved  level"+l);
            }
        }catch(Exception e) {
            model.addAttribute("level" ,"level"+4);

            System.out.println("default levelofdetailsSaved  4");


        }

        return "levelofdetailsSaved";
    }

    @GetMapping("/methodologicalReference")
    public String methodologicalReference(){

        return  "methodologicalReference";
    }















}


