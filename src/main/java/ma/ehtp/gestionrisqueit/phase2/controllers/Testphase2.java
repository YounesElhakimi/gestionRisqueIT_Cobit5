package ma.ehtp.gestionrisqueit.phase2.controllers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ehtp.gestionrisqueit.phase0.messages.ResponseMessage;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.services.OrganizationService;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase2.modelles.ITRiskElementSource;
import ma.ehtp.gestionrisqueit.phase2.services.ITRiskElementSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Obj implements Serializable {
    String str;
    Integer id;
}

@RestController
public class Testphase2 {

     @PostMapping("/tutf")
    public ResponseEntity<ResponseMessage> tutf(@RequestBody Obj o){
         U.ptxt(o.getStr());
         String message ="ok";
         return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
     }

}


