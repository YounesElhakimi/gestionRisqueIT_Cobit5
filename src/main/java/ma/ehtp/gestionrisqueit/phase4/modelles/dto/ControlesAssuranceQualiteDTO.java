package ma.ehtp.gestionrisqueit.phase4.modelles.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ControlesAssuranceQualiteDTO {

    private String id = "";

    private String risque = "";
    private String frequence = "";
    private String impact = "";
    private String criticalityLevel = "";
    private String efficacite = "";


}
