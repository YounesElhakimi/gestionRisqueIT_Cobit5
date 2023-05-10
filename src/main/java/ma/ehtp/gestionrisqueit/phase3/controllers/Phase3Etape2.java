package ma.ehtp.gestionrisqueit.phase3.controllers;


import ma.ehtp.gestionrisqueit.phase0.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.ImplementationInfo;
import ma.ehtp.gestionrisqueit.phase1.services.ImplementationInfoService;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskEvent;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.modelles.SousProcessus;
import ma.ehtp.gestionrisqueit.phase3.modelles.dto.RisqueAvancement;
import ma.ehtp.gestionrisqueit.phase3.services.RiskEventService;
import ma.ehtp.gestionrisqueit.phase3.services.RiskMappingService;
import ma.ehtp.gestionrisqueit.phase3.services.SousProcessusService;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Phase3Etape2 {


    @Autowired
    OrganizationService organizationService;

    @Autowired
    RiskMappingService riskMappingService;


    @Autowired
    SousProcessusService sousProcessusService;

    @Autowired
    RiskEventService riskEventService;

    Organization organization = null;

    @PostConstruct
    public void init() {

    }





    @GetMapping("/complete_the_process_mapping")
    public String complete_the_process_mapping(HttpSession session , Model model){


        initOrg(session);

        List<RiskMapping> entries = riskMappingService.findByOrganization(organization);

        for (RiskMapping rm:entries) {
            if (rm.getSousProcessusList().size() == 0){
                rm.getSousProcessusList().add(new SousProcessus());
            }
        }
        model.addAttribute("entries" , entries);
        model.addAttribute("colspan" ,"2");

        return "phase3/etape2/complete_the_sous_process_mapping";
    }

    @PostMapping("/complete_the_process_mapping")
    public ResponseEntity<ResponseMessage>  saveComplete_the_process_mapping(@RequestBody List<RiskMapping> riskMappingList , HttpSession session){
        return save_Complete_the_sous_process_mapping(riskMappingList ,session);
    }


    @GetMapping("/complete_the_sous_processes_mapping")
    public String complete_the_sous_processes_mapping(Model model , HttpSession session){
        initOrg(session);

        List<RiskMapping> entries = riskMappingService.findByOrganization(organization);

        for (RiskMapping rm:entries) {
            if (rm.getSousProcessusList().size() == 0){
                rm.getSousProcessusList().add(new SousProcessus());
            }
        }
       model.addAttribute("entries" , entries);
        model.addAttribute("colspan" ,"6");

        return "phase3/etape2/complete_the_sous_process_mapping";
    }


    @PostMapping("/complete_the_sous_processes_mapping")
    public ResponseEntity<ResponseMessage>  save_Complete_the_sous_process_mapping(@RequestBody List<RiskMapping> riskMappingList , HttpSession session){


        initOrg(session);


        String message = "";




         try {
             riskMappingList.forEach(riskMapping -> {

                 riskMapping.setOrganization(organization);
             });


             riskMappingService.saveAll(riskMappingList);


            message = "save successfully: ";


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";
             System.out.println("**************************************************");
            System.out.println("Exception  :  " + e.getMessage());
             System.out.println(riskMappingList.get(0).getActivite());

             System.out.println(riskMappingList.get(0).getSousProcessusList().size());
             System.out.println(riskMappingList.get(0).getSousProcessusList().get(0).getSousProcessus());
             System.out.println(riskMappingList.get(0).getSousProcessusList().get(1).getSousProcessus());

             System.out.println("**************************************************");

             return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/riskEvent")
    public String riskEvent(Model model , HttpSession session){
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


    @PostMapping("/riskEvent")
    public ResponseEntity<ResponseMessage>  save_riskEvent(@RequestBody List<RiskEvent> riskEventList ,HttpSession session){
        initOrg(session);





        String message = "";



        try {

            riskEventService.deleteAll(
                    riskEventService.findByOrganization(organization)
            );

            riskEventList.forEach(riskMapping -> {

                riskMapping.setOrganization(organization);
            });


            riskEventService.saveAll(riskEventList);


            message = "save successfully: ";


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";
           

            U.ptxt(message + "  e :"+e.getMessage());

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @Autowired
    ImplementationInfoService implementationInfoService;


    @GetMapping("/Livrable_phase3")
    public String livrable_phase3(HttpSession session , Model model){
        initOrg(session);
        List<RiskMapping> riskMappingList = riskMappingService.findByOrganization(organization);

        for (RiskMapping rm:riskMappingList) {
            if (rm.getSousProcessusList().size() == 0){
                rm.getSousProcessusList().add(new SousProcessus());
            }
        }

        model.addAttribute("riskMappingList" , riskMappingList);

        List<RiskEvent> riskEventList = riskEventService.findByOrganization(organization);


        model.addAttribute("riskEventList" ,riskEventList);

        List<RisqueAvancement> raList = new ArrayList<>();
        riskMappingService.findByOrganizationWithoutSP(organization).forEach(riskMapping -> {

                    raList.add(new RisqueAvancement(riskMapping));
                }
        );

        model.addAttribute("raList" ,raList);

        ImplementationInfo imp =  implementationInfoService.findByOrganization(organization);
        imp.setP3e2(true);
        implementationInfoService.save(imp);

        return "phase3/etape2/Livrable_phase3";
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
