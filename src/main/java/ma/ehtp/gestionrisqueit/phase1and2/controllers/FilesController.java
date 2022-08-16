package ma.ehtp.gestionrisqueit.controllers;

import java.util.*;
import java.util.stream.Collectors;

import ma.ehtp.gestionrisqueit.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.modelles.FileInfo;
import ma.ehtp.gestionrisqueit.services.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.annotation.PostConstruct;

@Controller
@CrossOrigin("http://localhost:8080")
public class FilesController {
    @Autowired
    FilesStorageService storageService;


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
        nextDocsUp.put("DOC_PHASE4_ITAPE2", "");
        nextDocsUp.put("DOC_PHASE4_AUDITS_CONTROLS", "");
        nextDocsUp.put("DOC_PHASE5_ITAPE1", "");
        nextDocsUp.put("DOC_PHASE5_ITAP", "");

        String docsEtapes[] = nextDocsUp.keySet().toArray(new String[nextDocsUp.keySet().size()]);

        System.out.println(docsEtapes.toString());

        if (Arrays.toString(docsEtapes).toLowerCase().contains(phase.toLowerCase())) {
            if (phase.equalsIgnoreCase("DOC_PHASE4_AUDITS_CONTROLS")){
                title = "Please upload reports of evaluations, audits and controls:";

            }else {
                title = "Please specify the different documents needed for the implementation of the roadmap:";

            }

            model.addAttribute("next" , nextDocsUp.get(phase.toUpperCase()));

        }

        model.addAttribute("title" , title);

        return "uploadfiles" ;
    }



    @PostMapping(value={"/upload" , "uploads/upload"})
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        String message = "";
        //storageService.setSousFolder("newOrg2");
        try {
            List<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file -> {
                storageService.save(file);
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



}