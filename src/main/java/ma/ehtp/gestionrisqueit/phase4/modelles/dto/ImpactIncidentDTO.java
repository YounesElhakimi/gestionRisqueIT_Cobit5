package ma.ehtp.gestionrisqueit.phase4.modelles.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ImpactIncidentDTO {
    private String risque = "";

    private String old_frequence = "";
    private String old_impact = "";
    private String old_criticalityLevel = "";
    private String old_efficacite = "";

    private String frequence = "";
    private String impact = "";
    private String criticalityLevel = "";
    private String efficacite = "";


}
