package ma.ehtp.gestionrisqueit.phase5.modelles.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class KriMeasuresDTO {



    private Long id;
    private Long rmId;

    private String kRI = "";
    private String risque = "";


    private String formuleDeCalcul = "";
    private String entiteProductrice = "";
    private String processusDObtention = "";
    private String periodiciteDeMesure = "";



    private String mesure1 = "";
    private String cible1 = "";
    private String ecart1 = "";
    private String mesure2 = "";
    private String cible2 = "";
    private String ecart2 = "";

    private String mesure3 = "";
    private String cible3 = "";
    private String ecart3 = "";

    private String mesure4 = "";
    private String cible4 = "";
    private String ecart4 = "";


    private String explicationsDesEcarts = " ";
    private String mesuresCorrectives = " ";



}
