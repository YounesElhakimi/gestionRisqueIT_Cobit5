package ma.ehtp.gestionrisqueit.phase0.conrollers;


import ma.ehtp.gestionrisqueit.phase0.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase1.modelles.ImplementationInfo;
import ma.ehtp.gestionrisqueit.phase1.modelles.Objective;
import ma.ehtp.gestionrisqueit.phase1.services.ImplementationInfoService;
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
import org.w3c.dom.stylesheets.LinkStyle;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Phase0 {

    @Autowired
    OrganizationService organizationService;


    @Autowired
    ImplementationInfoService implementationInfoService;

    Organization organization = null;

    @GetMapping("/testfiles")
    public String getfiles(){

        return "fragments/filesList";
    }

    @GetMapping("")
    public String index(HttpSession session){
        session.setAttribute("organization" , null);
        return "phase0/index";
    }



    @GetMapping("/newOrg")
    public String newOrgpage(Model model , HttpSession session){
        Organization organization = (Organization) session.getAttribute("organization");

        if (organization != null)
        U.ptxt("organization from session id : "+organization.getId() + " namr : "+organization.getName());


        if (organization == null)
           organization = new Organization();

        U.ptxt("after test get name : "+organization.getName()+" id : "+organization.getId());

        model.addAttribute("Organization" , organization);
        U.ptxt("after addAttribute model organization from session id : "+organization.getId() + " namr : "+organization.getName());
        return "phase0/neworganization";
    }

    @PostMapping("/newOrg")
    public ResponseEntity<ResponseMessage> createNewOrg(@RequestBody Organization organization  , HttpSession session){


        String message = "";
        try {
            message = "save successfully: ";
            U.ptxt(" befor do eny Post   name : "+organization.getName()+" id : "+organization.getId());


            organization = organizationService.save(organization);


            ImplementationInfo implementationInfo = new ImplementationInfo();
            implementationInfo.setOrganization(organization);
            implementationInfoService.save(implementationInfo);


            session.setAttribute("organization" , organization);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        }catch (Exception e){
            message = "Fail to save!";
            U.ptxt("error .............................." + e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }

    @GetMapping("/ExOrg")
    public String getAllExOrg(Model model , HttpSession session){
        List<Organization> organizationList = organizationService.findAll();
        model.addAttribute("organizationList" , organizationList);
        return "phase0/exorganization";
    }
    @PostMapping("/ExOrg")
    public ResponseEntity<ResponseMessage> selectExOrg(@RequestBody Organization organization  , HttpSession session){


        String message = "";
        try {


            organizationService.findById(organization.getId()).ifPresent(organization1 -> {
                session.setAttribute("organization" , organization1);

            });
            message = "init org successfully: ";

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        }catch (Exception e){
            message = "Fail to init org!";
            U.ptxt("error .............................." + e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }


    @GetMapping("/allsteps")
    public String allsteps(HttpSession session , Model model){
        //organizationService.findAll().forEach(organization -> System.out.println(organization));
        initOrg(session);
        ImplementationInfo implementationInfo = implementationInfoService.findByOrganization(organization);
        model.addAttribute("implementationInfo" , implementationInfo);
        return "phase0/allsteps";
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
