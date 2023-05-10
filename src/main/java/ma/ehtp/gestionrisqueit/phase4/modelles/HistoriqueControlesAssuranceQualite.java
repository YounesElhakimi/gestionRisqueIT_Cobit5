package ma.ehtp.gestionrisqueit.phase4.modelles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class HistoriqueControlesAssuranceQualite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    private String risque = "";
    private String frequence = "";
    private String impact = "";
    private String criticalityLevel = "";
    private String efficacite = "";

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "risk_mapping_id")
    private RiskMapping riskMapping;
}
