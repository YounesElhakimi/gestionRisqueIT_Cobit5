package ma.ehtp.gestionrisqueit.phase3.controllers;


import ma.ehtp.gestionrisqueit.phase0.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase1.modelles.ImplementationInfo;
import ma.ehtp.gestionrisqueit.phase1.services.ImplementationInfoService;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskEvent;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.services.RiskEventService;
import ma.ehtp.gestionrisqueit.phase3.services.RiskMappingService;
import ma.ehtp.gestionrisqueit.phase3.services.SousProcessusService;
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
public class Phase3Etape1 {


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





    @Autowired
    ImplementationInfoService implementationInfoService;

    @GetMapping("/Cartographie_des_risques")
    public String cartographieDesRisques(HttpSession session , Model model){
        initOrg(session);


        List<RiskMapping> riskMappingList = new ArrayList<>();

        try {
            riskMappingList = riskMappingService.findByOrganizationWithoutSP(organization);
        }catch (Exception e){
            U.ptxt("e : "+e.getMessage());
        }

        model.addAttribute("riskMappingList" , riskMappingList);
        String level;
        try {
             level = implementationInfoService.findByOrganization(organization).getLevelofdetails();
        }catch (Exception e){
            U.ptxt("get Cartographie_des_risques try to find level e :"+e.getMessage());

            level = "null";
        }

        model.addAttribute("level" , level);

        return "phase3/etape1/Cartographie_des_risques";
    }

    @PostMapping("/Cartographie_des_risques")
    public ResponseEntity<ResponseMessage> saveCartographieDesRisques(@RequestBody List<RiskMapping> riskMappingList , HttpSession session){

        initOrg(session);

        String message = "";
        try {

            riskMappingList.forEach(riskMapping -> {

                U.ptxt("id : "+riskMapping.getId()+" del "+riskMapping.getIsDel());
            });
             /*
            riskMappingService.saveAll(riskMappingList);

             */

            riskMappingList.forEach(riskMapping -> riskMapping.setOrganization(organization));
            riskMappingService.updateAll(riskMappingList);
            message = "save successfully: ";

         //   riskMappingService.findAll().forEach(concurrent -> System.out.println(concurrent));

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";
            System.out.println(message +" risk Mapping "+ e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/RiskMapping")
    public String riskMapping(HttpSession session , Model model){
        initOrg(session);


        List<RiskMapping> riskMappingList = new ArrayList<>();

        try {
            riskMappingList = riskMappingService.findByOrganizationWithoutSP(organization);
        }catch (Exception e){
            U.ptxt("e : "+e.getMessage());
        }

        model.addAttribute("riskMappingList" , riskMappingList);

        ImplementationInfo imp =  implementationInfoService.findByOrganization(organization);
        imp.setP3e1(true);
        implementationInfoService.save(imp);
        return "phase3/etape1/RiskMapping";
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
