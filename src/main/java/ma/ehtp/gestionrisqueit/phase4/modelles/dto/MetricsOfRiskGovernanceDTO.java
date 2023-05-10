package ma.ehtp.gestionrisqueit.phase4.modelles.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MetricsOfRiskGovernanceDTO {

    private String id = "";

    private String kri = "";
    private String risque = "";
    private String formuleDeCalcul = "";
    private String entiteProductrice = "";
    private String processusDObtention = "";
    private String periodiciteDeMesure = "";
    /*
    private String mesure1;
    private String cible1;
    private String ecart1;
    private String mesure2;
    private String cible2;
    private String ecart2;

    private String mesure3;
    private String cible3;
    private String ecart3;

    private String mesure4;
    private String cible4;
    private String ecart4;


    private String explicationsDesEcarts;
    private String mesuresCorrectives;

     */
}
