package ma.ehtp.gestionrisqueit.phase5.modelles;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class KriMeasures {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(columnDefinition="LONGTEXT")
    private String explicationsDesEcarts;
    @Column(columnDefinition="LONGTEXT")
    private String mesuresCorrectives;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "risk_mapping_id")
    @ToString.Exclude
    private RiskMapping riskMapping;

    @JsonBackReference
    public RiskMapping getRiskMapping() {
        return riskMapping;
    }
}
