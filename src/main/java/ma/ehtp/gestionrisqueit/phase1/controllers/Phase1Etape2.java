package ma.ehtp.gestionrisqueit.phase1.controllers;


import ma.ehtp.gestionrisqueit.phase0.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase1.modelles.*;
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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Phase1Etape2 {
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



    @GetMapping("/team_project")
    public String teamProject(HttpSession session , Model model){
        initOrg(session);
        List<TeamProjectMember> teamProjectMembers = teamProjectMemberService.findByOrganization(organization);
        model.addAttribute("entries" ,teamProjectMembers);
        return "phase1/etape2/teamProject";
    }

    @PostMapping(value={"/team_project" })
    public ResponseEntity<ResponseMessage> SaveteamProject(@RequestBody List<TeamProjectMember> tpmList ) {
        String message = "";
        try {

            teamProjectMemberService.deleteAll(teamProjectMemberService.findByOrganization(organization));
            tpmList.forEach(teamProjectMember -> {
                teamProjectMember.setOrganization(organization);
            });

            teamProjectMemberService.saveAll(tpmList);


            message = "save Team Project Members uccessfully: " ;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail tosave Team Project Members !";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/stakeholders")
    public String stakeholders(HttpSession  session ,Model model){
        initOrg(session);
        List<Stakeholders> stakeholders = stakeholdersService.findByOrganization(organization);
        model.addAttribute("entries" ,stakeholders);
        return "phase1/etape2/stakeholders";
    }


    @PostMapping(value={"/stakeholders" })
    public ResponseEntity<ResponseMessage> saveStakeholders(@RequestBody List<Stakeholders> stakeholdersList) {
        String message = "";
        //storageService.setSousFolder("newOrg2");
        try {
            stakeholdersService.deleteAll(
                    stakeholdersService.findByOrganization(organization)
            );

        }catch (Exception e){
            U.ptxt("e : "+e.getMessage());
        }
        try {

            stakeholdersService.saveAll(
                    stakeholdersService.findByOrganization(organization)
            );
            stakeholdersList.forEach(teamProjectMember -> {
                teamProjectMember.setOrganization(organization);
            });

            stakeholdersService.saveAll(stakeholdersList);


            message = "save the stakeholders successfully: " ;


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save the stakeholders!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @Autowired
    GanttService ganttService;

    @GetMapping("/phasesGanttDiagram")
    public String phasesGanttDiagram(HttpSession session , Model model){
        initOrg(session);
        Gantt gantt = ganttService.findByOrganization(organization);
      //  Gantt gantt = new Gantt();
        if (gantt == null)
            gantt = new Gantt();

        U.ptxt("get gantt id : "+gantt.getId());

        model.addAttribute("Gantt" , gantt);

        return  "phase1/etape2/phasesGanttDiagram";
    }
    @PostMapping("/phasesGanttDiagram")
    public ResponseEntity<ResponseMessage>  save_phasesGanttDiagram(@RequestBody Gantt gantt ,HttpSession session , Model model){
        initOrg(session);

        String message = "";
        //storageService.setSousFolder("newOrg2");
        try {
            gantt.setOrganization(organization);
            U.ptxt("get gantt id : "+gantt.getId());

            gantt = ganttService.save(gantt);

            message = "save the stakeholders successfully: " ;


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save the stakeholders!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/GanttDiagram")
    public String ganttDiagram(HttpSession session ,Model model){


        initOrg(session);
        Gantt gantt = ganttService.findByOrganization(organization);

        model.addAttribute("Gantt" , gantt);


        return "phase1/etape2/GanttDiagram";
    }





    @GetMapping("/Projectscopingnote")
    public String projectScopingNote(HttpSession session , Model model){

        initOrg(session);
        List<StrategicOrientations> strategicOrientations = strategicOrientationsService.findAllByOrganization(organization);
        model.addAttribute("strategicOrientations" ,strategicOrientations);

        List<MajorRisks> majorRisks = majorRisksService.findByOrganization(organization);
        model.addAttribute("majorRisks" , majorRisks);

        Objective objective = objectiveService.findByOrganization(organization);
        model.addAttribute("Objective" ,objective );

        Gantt gantt = ganttService.findByOrganization(organization);
        model.addAttribute("Gantt" , gantt);

        List<Stakeholders> stakeholders = stakeholdersService.findByOrganization(organization);
        model.addAttribute("stakeholders" ,stakeholders);

        List<TeamProjectMember> teamProjectMembers = teamProjectMemberService.findByOrganization(organization);
        model.addAttribute("teamProjectMembers" ,teamProjectMembers);

        ImplementationInfo imp =  implementationInfoService.findByOrganization(organization);



        if (imp == null){
            imp  = new ImplementationInfo();
            imp.setOrganization(organization);
        }

         imp.setP1e2(true);
         implementationInfoService.save(imp);
        return "phase1/etape2/Projectscopingnote";
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
