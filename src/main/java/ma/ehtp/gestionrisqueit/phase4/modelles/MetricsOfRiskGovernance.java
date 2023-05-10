package ma.ehtp.gestionrisqueit.phase4.modelles;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class MetricsOfRiskGovernance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  private String kri = "";
  //  private String risqueCorrespondant = "";
  @Column(columnDefinition="LONGTEXT")
    private String formuleDeCalcul = "";

    @Column(columnDefinition="LONGTEXT")
    private String entiteProductrice = "";

    @Column(columnDefinition="LONGTEXT")
    private String processusDObtention = "";

    private String periodiciteDeMesure = "";


    @OneToOne(mappedBy = "metricsOfRiskGovernance" , cascade = CascadeType.ALL)
    private RiskMapping riskMapping;


    @JsonBackReference
    public RiskMapping getRiskMapping() {
        return riskMapping;
    }
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
