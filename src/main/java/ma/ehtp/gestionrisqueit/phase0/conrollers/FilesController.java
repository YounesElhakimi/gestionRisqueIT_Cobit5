package ma.ehtp.gestionrisqueit.phase0.conrollers;

import java.util.*;
import java.util.stream.Collectors;

import ma.ehtp.gestionrisqueit.phase0.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.phase0.modelles.FileInfo;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.services.FilesStorageService;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase4.modelles.MetricsOfRiskGovernance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin("http://localhost:8080")
public class FilesController {
    @Autowired
    FilesStorageService storageService;

    @Autowired
    OrganizationService organizationService;

    Organization organization =null;
    @GetMapping("/uploads/{phase}")
    public String uploadfilesDocs(Model model , @PathVariable("phase") String phase){
        /*
        String docsEtapes[] = { "DOC_PHASE1_ITAPE1",
                                "DOC_PHASE1_ITAPE2",
                                "DOC_PHASE1_ITAPE3",
                                "DOC_PHASE2_ITAPE1",
                                "DOC_PHASE3_ITAPE1",
                                "DOC_PHASE3_ITAPE2",
                                "DOC_PHASE4_ITAPE1",
                                "DOC_PHASE4_ITAPE2",
                                "DOC_PHASE4_AUDITS_CONTROLS",
                                "DOC_PHASE5_ITAPE1",
                                "DOC_PHASE5_ITAPE"};


         */
        String title = "Not found ";
        Map<String , String> nextDocsUp = new HashMap<>();
        nextDocsUp.put("DOC_PHASE1_ITAPE1", "/strategicOrientations");
        nextDocsUp.put("DOC_PHASE1_ITAPE2", "/team_project");
        nextDocsUp.put("DOC_PHASE1_ITAPE3", "/levelofdetails ");
        nextDocsUp.put("DOC_PHASE2_ITAPE1", "/methodologyToHandleRisk");
        nextDocsUp.put("DOC_PHASE3_ITAPE1", "/Cartographie_des_risques");
        nextDocsUp.put("DOC_PHASE3_ITAPE2", "/complete_the_process_mapping");
        nextDocsUp.put("DOC_PHASE4_ITAPE1", "/communication_plan");
        nextDocsUp.put("DOC_PHASE4_ITAPE2", "/uploads/DOC_PHASE4_AUDITS_CONTROLS");
        nextDocsUp.put("DOC_PHASE4_AUDITS_CONTROLS", "/controles_assurance_qualite");
        nextDocsUp.put("DOC_PHASE5_ITAPE1", "/business_continuity_plan");
        nextDocsUp.put("DOC_PHASE5_ITAPE2", "/update_risk_mapping ");

        String docsEtapes[] = nextDocsUp.keySet().toArray(new String[nextDocsUp.keySet().size()]);

        System.out.println(docsEtapes.toString());
        String title2 = "";
        if (Arrays.toString(docsEtapes).toLowerCase().contains(phase.toLowerCase())) {
            if (phase.equalsIgnoreCase("DOC_PHASE4_AUDITS_CONTROLS")){
                title = "Please upload reports of evaluations, audits and controls:";
            }else {
                title = "Please specify the different documents needed for the implementation of the roadmap";
                title2 = "For example : Annual reports, universal record documents, financial reports, strategic plans, breifing notes…";

            }

            model.addAttribute("next" , nextDocsUp.get(phase.toUpperCase()));

        }

        model.addAttribute("title" , title);
        model.addAttribute("title2" , title2);

        return "phase0/uploadfiles" ;
    }



     @PostMapping("/deleteFile")
     public ResponseEntity<ResponseMessage> deleteFile(@RequestBody FileInfo fileInfo){

         String message = "";

         try {
             message = "delete successfully: ";
                storageService.delet(fileInfo.getName());
             U.ptxt("ok ..............................");
             return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
         } catch (Exception e) {
             message = "Fail to delete!";
             U.ptxt("error .............................. "+e.getMessage());
             return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
         }
     }


    @PostMapping(value={"/upload" , "uploads/upload"})
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files , HttpSession session) {
        String message = "";
        initOrg(session);
        String sousFolder = ""+organization.getId();
        U.ptxt("sousFolder :"+sousFolder);
        storageService.setSousFolder(sousFolder);
        try {
            List<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file -> {
                try {
                    storageService.save(file);
                }catch (Exception e){
                    U.ptxt("Exception storageService.save e : "+e.getMessage());
                }

                fileNames.add(file.getOriginalFilename());
            });
            message = "Uploaded the files successfully: " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    @GetMapping("/Allfiles")
    public String getAllListFiles(Model model , HttpSession session) {
        initOrg(session);
        try{
            String sousFolder = "" + organization.getId();

            // U.ptxt("sousFolder :"+sousFolder);
            storageService.setSousFolder(sousFolder);

        }catch (Exception e){

            U.ptxt("ppst Allfiles setSousFolder e : "+e.getMessage());
        }
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        model.addAttribute("fileInfos" ,fileInfos);
        return "fragments/filesList";
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