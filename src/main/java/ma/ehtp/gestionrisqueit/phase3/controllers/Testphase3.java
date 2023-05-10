package ma.ehtp.gestionrisqueit.phase3.controllers;


import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase2.modelles.ITRiskElementSource;
import ma.ehtp.gestionrisqueit.phase2.services.ITRiskElementSourceService;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskEvent;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.modelles.dto.NbrOfRMByCat;
import ma.ehtp.gestionrisqueit.phase3.services.RiskEventService;
import ma.ehtp.gestionrisqueit.phase3.services.RiskMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class Testphase3 {

    Organization organization = null;

    @Autowired
    RiskMappingService riskMappingService;
    @GetMapping("/api/RiskMapping")
    public List<RiskMapping> getAllRiskMapping(HttpSession session){
        initOrg(session);
        return riskMappingService.findAllDevenuCritique(organization);
    }


    @Autowired
    RiskEventService riskEventService;

    @GetMapping("/api/re")
    public List<RiskEvent> getRE(HttpSession session){
        initOrg(session);
        return riskEventService.findByOrganizationOrderByImpacteMAD(organization);
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

                this.organization = organizationService.findAll().get(0) ;
                if (this.organization != null)
                    session.setAttribute("organization" , this.organization );
                else
                    this.organization = organizationService.save(new Organization(null,"defaultOrg" ,null));

            }

        } catch (Exception exception) {
            U.ptxt("Exception while trying to load organization from   session");

            this.organization = organizationService.findAll().get(0) ;
            if (this.organization != null)
                session.setAttribute("organization" , this.organization );
            else
                this.organization = organizationService.save(new Organization(null,"defaultOrg" ,null));

        }



        U.ptxt("initOrg function : organization id = "+organization.getId() );

    }

}
