package ma.ehtp.gestionrisqueit.phase4.controllers;


import ma.ehtp.gestionrisqueit.phase0.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.services.FilesStorageService;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase1.modelles.ImplementationInfo;
import ma.ehtp.gestionrisqueit.phase1.services.ImplementationInfoService;
import ma.ehtp.gestionrisqueit.phase4.modelles.CommunicationPlan;
import ma.ehtp.gestionrisqueit.phase4.modelles.MechanismsDescription;
import ma.ehtp.gestionrisqueit.phase4.modelles.MetricsOfRiskGovernance;
import ma.ehtp.gestionrisqueit.phase4.modelles.Photo;
import ma.ehtp.gestionrisqueit.phase4.modelles.dto.ControlesAssuranceQualiteDTO;
import ma.ehtp.gestionrisqueit.phase4.modelles.dto.MetricsOfRiskGovernanceDTO;
import ma.ehtp.gestionrisqueit.phase4.repositories.PhotoRepository;
import ma.ehtp.gestionrisqueit.phase4.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class Phase4Etape1 {


    @Autowired
    CommunicationPlanServive communicationPlanServive;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    MechanismsDescriptionService mechanismsDescriptionService;

    @Autowired
    MetricsOfRiskGovernanceService metricsOfRiskGovernanceService;

    @Autowired
    ControlesAssuranceQualiteService assuranceQualiteService;


    Organization organization = null;

    @PostConstruct
    public void init() {

    }


    @GetMapping("/communication_plan")
    public  String communication_plan(Model model , HttpSession session){
        initOrg(session);
        List<CommunicationPlan> communicationPlans = communicationPlanServive.findByOrganization(organization);
        model.addAttribute("communicationPlans",communicationPlans);

        return "phase4/etape1/communication_plan";
    }

    @PostMapping("/communication_plan")
    public ResponseEntity<ResponseMessage> save_riskEvent(@RequestBody List<CommunicationPlan> communicationPlanList , HttpSession session){


        initOrg(session);

        String message = "";




        try {
            communicationPlanServive.deleteAll(
                    communicationPlanServive.findByOrganization(organization)
            );

            communicationPlanList.forEach(communicationPlan -> {
                communicationPlan.setOrganization(organization);
            });

            communicationPlanServive.saveAll(communicationPlanList);


            message = "save successfully: ";


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";

            U.ptxt(message + "  e : "+e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/mechanisms_description")
    public  String mechanisms_description(Model model , HttpSession session){
    initOrg(session);

    MechanismsDescription mechanismsDescription = mechanismsDescriptionService.findByOrganization(organization);
    model.addAttribute("entries" , mechanismsDescription );
        return "phase4/etape1/mechanisms_description";
    }



    @PostMapping("/mechanisms_description")
    public ResponseEntity<ResponseMessage> save_mechanisms_description(@RequestBody MechanismsDescription mechanismsDescription ,HttpSession session){


        initOrg(session);

        String message = "";


        try {
            mechanismsDescriptionService.delete(
                    mechanismsDescriptionService.findByOrganization(organization)
            );
        }catch (Exception e){

            U.ptxt("Exception  in delete  : "+e.getMessage());
        }

        try {

            mechanismsDescription.setOrganization(organization);

            mechanismsDescriptionService.save(mechanismsDescription);


            message = "save successfully: ";


            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";


            System.out.println("**************************************************");
            U.ptxt("Exception :"+e.getMessage());

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @Autowired
    FilesStorageService storageService;

    @GetMapping("/logigramme")
    public String Logigramme(Model model , HttpSession session){

        initOrg(session);
       // String sousFolder = "logigramme_" + organization.getId();

        // U.ptxt("sousFolder :"+sousFolder);
       // storageService.setSousFolder(sousFolder);

        try{
            String sousFolder = "logigramme_" + organization.getId();

            // U.ptxt("sousFolder :"+sousFolder);
            storageService.setSousFolder(sousFolder);

        }catch (Exception e){

            U.ptxt("ppst logigramme setSousFolder e : "+e.getMessage());
        }

        String fileName;

        try {
            Path path = storageService.loadAll().toList().get(0);
            fileName = path.getFileName().toString();
        }catch (Exception e){

            U.ptxt("get logigramme error "+e.getMessage());
            fileName = null;

        }

        model.addAttribute("Photo" , fileName);
        return "phase4/etape1/logigramme";
    }



    @PostMapping("/logigramme")
    public ResponseEntity<ResponseMessage> saveUser(Photo user,@RequestParam("image") MultipartFile  file , HttpSession session) {

        initOrg(session);

        String message = "";





        try {
        try{
            String sousFolder = "logigramme_" + organization.getId();

            // U.ptxt("sousFolder :"+sousFolder);
            storageService.setSousFolder(sousFolder);

        }catch (Exception e){

            U.ptxt("ppst logigramme setSousFolder e : "+e.getMessage());
        }



        try {
            storageService.deleteAll();

        } catch (Exception e) {
            U.ptxt("deleteAll error :"+e.getMessage());

        }

        try {
            String sousFolder = "logigramme_" + organization.getId();
            storageService.setSousFolder(sousFolder);
        }
        catch (Exception e){
            U.ptxt("in post sousFolder error :"+e.getMessage());
        }



            storageService.saveIn(file , "logigramme");
            U.ptxt("logigramme after save");
            message = "save successfully: ";


            U.ptxt("ok ..............................");
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";

            U.ptxt("error ..............................");



            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }    }

    @PostMapping("/logigramme/save")
    public RedirectView savelogigramme(Photo user,@RequestParam("image") MultipartFile  file , HttpSession session) {

        initOrg(session);

        try{
            String sousFolder = "logigramme_" + organization.getId();

            // U.ptxt("sousFolder :"+sousFolder);
            storageService.setSousFolder(sousFolder);

        }catch (Exception e){

            U.ptxt("ppst logigramme setSousFolder e : "+e.getMessage());
        }



        try {
            storageService.deleteAll();

        } catch (Exception e) {
            U.ptxt("deleteAll error :"+e.getMessage());

        }

        try {
            String sousFolder = "logigramme_" + organization.getId();
            storageService.setSousFolder(sousFolder);
        }
        catch (Exception e){
            U.ptxt("in post sousFolder error :"+e.getMessage());
        }



        try {

            storageService.saveIn(file , "logigramme");

        } catch (Exception e) {
            U.ptxt("save(file) error :"+e.getMessage());

        }
        U.ptxt("logigramme after save");




        return new RedirectView("/logigramme");
    }

    @GetMapping("/kri_board")
    public String kri_board(HttpSession session , Model model){
        initOrg(session);

        List<MetricsOfRiskGovernanceDTO> metricsOfRiskGovernanceDTOList = metricsOfRiskGovernanceService.findByOrganization(organization);
        model.addAttribute("kriList" , metricsOfRiskGovernanceDTOList);

        return "phase4/etape1/kri_board";
    }

    @PostMapping("/kri_board")
    public ResponseEntity<ResponseMessage> save_kri_board(@RequestBody List<MetricsOfRiskGovernanceDTO> metricsOfRiskGovernanceDTOList ,HttpSession session){


        initOrg(session);


        String message = "";





        try {



            message = "save successfully: ";

           List<MetricsOfRiskGovernance>   metricsOfRiskGovernanceList =  metricsOfRiskGovernanceService.saveAll(metricsOfRiskGovernanceDTOList);

           U.ptxt("ok ..............................");
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to save!";

            U.ptxt("error ..............................");



            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }



    @Autowired
    ImplementationInfoService implementationInfoService;

    @GetMapping("/Livrable_phase4_etape1")
    public String Livrable_phase4_etape1(HttpSession session , Model model){
        initOrg(session);

        MechanismsDescription mechanismsDescription = mechanismsDescriptionService.findByOrganization(organization);
        model.addAttribute("mechanismsDescription" , mechanismsDescription );

        String fileName;
        try {
            String sousFolder = "logigramme_" + organization.getId();

            // U.ptxt("sousFolder :"+sousFolder);
            storageService.setSousFolder(sousFolder);

        }catch (Exception e){
            U.ptxt("e :"+e.getMessage());
        }


        try {
            Path path = storageService.loadAll().toList().get(0);
            fileName = path.getFileName().toString();
        }catch (Exception e){

            U.ptxt("get logigramme error "+e.getMessage());
            fileName = null;

        }

        List<MetricsOfRiskGovernanceDTO> metricsOfRiskGovernanceDTOList = metricsOfRiskGovernanceService.findByOrganization(organization);
        model.addAttribute("kriList" , metricsOfRiskGovernanceDTOList);

        try {
            model.addAttribute("Photo" , fileName);

        }catch (Exception e){

            U.ptxt("get logigramme error "+e.getMessage());
            fileName = null;

        }


        List<CommunicationPlan> communicationPlans = communicationPlanServive.findByOrganization(organization);
        model.addAttribute("communicationPlans",communicationPlans);

        ImplementationInfo imp =  implementationInfoService.findByOrganization(organization);
        imp.setP4e1(true);
        implementationInfoService.save(imp);
        return "phase4/etape1/Livrable_phase4_etape1";
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
