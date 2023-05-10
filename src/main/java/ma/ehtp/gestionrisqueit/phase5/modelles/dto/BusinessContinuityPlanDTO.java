package ma.ehtp.gestionrisqueit.phase5.modelles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BusinessContinuityPlanDTO {


    private Long id;

    private String kri = "";
    private String contexte = "";
    private String formaliser = "";
    private String gerreRisque = "";
    private String scenarios = "";
    private String formaliserProcedures = "";
    private String strategieContinuite = "";
    private String criseCommunication = "";
    private String planContinuite = "";
    private String capacitePlan = "";
    private String exercices = "";

}
