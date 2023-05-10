package ma.ehtp.gestionrisqueit.phase1.controllers;


import ma.ehtp.gestionrisqueit.phase0.conrollers.Desc;
import ma.ehtp.gestionrisqueit.phase0.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase1.modelles.ImplementationInfo;
import ma.ehtp.gestionrisqueit.phase1.modelles.MajorRisks;
import ma.ehtp.gestionrisqueit.phase1.modelles.Objective;
import ma.ehtp.gestionrisqueit.phase1.modelles.StrategicOrientations;
import ma.ehtp.gestionrisqueit.phase1.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Phase1Etape1 {
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


    @GetMapping("/strategicOrientations")
    public String StrategicOrientations(HttpSession session,Model model){
        initOrg(session);
        List<StrategicOrientations> strategicOrientations = strategicOrientationsService.findAllByOrganization(organization);

        model.addAttribute("strategicOrientations" ,strategicOrientations);



        return "phase1/etape1/strategicOrientations";
    }


    @PostMapping("/strategicOrientations")
    public ResponseEntity<ResponseMessage> saveStrategicOrientations(@RequestBody List<StrategicOrientations> soList  , HttpSession session){
        initOrg(session);
        String message = "";

        try {
            strategicOrientationsService.deleteAll(
                    strategicOrientationsService.findAllByOrganization(organization)
            );
            U.ptxt("delete all strategicOrientations ");
        }catch (Exception e){
            U.ptxt("e : "+e.getMessage());
        }
        try {
            message = "save successfully: ";

            soList.forEach(description -> {

                description.setOrganization(organization);
            });

            strategicOrientationsService.saveAll(soList);
            U.ptxt("save all strategicOrientations ");

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        }catch (Exception e){
            message = "Fail to save!";
            U.ptxt("error .............................." + e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }



    @GetMapping("/majorrisks")
    public String majorRisks(Model model , HttpSession session){
        initOrg(session);
        List<MajorRisks> majorRisks = majorRisksService.findByOrganization(organization);
        model.addAttribute("majorRisks" , majorRisks);
        return "phase1/etape1/majorrisks";
    }


    @PostMapping("/majorrisks")
    public ResponseEntity<ResponseMessage>  saveMajorRisks(@RequestBody List<MajorRisks> soList , HttpSession session){

        initOrg(session);
        String message = "";

        try {

            majorRisksService.deleteAll(
                    majorRisksService.findByOrganization(organization)
            );
        }catch (Exception e){
            U.ptxt("e : "+e.getMessage());
        }

        try {


            soList.forEach(description -> {
                description.setOrganization(organization);
            });

            majorRisksService.saveAll(soList);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        }catch (Exception e){
            message = "Fail to save!";
            U.ptxt("error .............................." + e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }




    }

    @GetMapping("/description")
    public String description(Model model , HttpSession session){

        initOrg(session);
        Objective objective = objectiveService.findByOrganization(organization);
        if (objective != null){
            U.ptxt("get");
            U.p(objective);
            model.addAttribute("Objective" ,objective );

        }
        else
            model.addAttribute("Objective" ,new Objective() );


        return "phase1/etape1/description";
    }



    @PostMapping(path = "/description" ,  produces = "application/json;charset=UTF-8")
    public ResponseEntity<ResponseMessage>  saveDescription(@RequestBody Objective objective , Model model , HttpSession session){
        initOrg(session);
        String message = "";

        try {
            objective.setOrganization(organization);
            objective = objectiveService.save(objective);
            message = "save successfully: ";

            ImplementationInfo imp =  implementationInfoService.findByOrganization(organization);
            imp.setP1e1(true);
            implementationInfoService.save(imp);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        }catch (Exception e){
            message = "Fail to save!";
            U.ptxt("error .............................." + e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
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
