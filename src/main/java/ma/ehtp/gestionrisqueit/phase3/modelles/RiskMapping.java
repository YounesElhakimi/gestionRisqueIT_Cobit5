package ma.ehtp.gestionrisqueit.phase3.modelles;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase4.modelles.MetricsOfRiskGovernance;
import ma.ehtp.gestionrisqueit.phase5.modelles.KriMeasures;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RiskMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(columnDefinition="LONGTEXT")
    private String activite = "";
    @Column(columnDefinition="LONGTEXT")
    private String macroProcessus = "";
    @Column(columnDefinition="LONGTEXT")
    private String processus = "";
    private String category = "";
    private String risque = "";
    private String kRI = "";
    @Column(columnDefinition="LONGTEXT")
    private String facteur = "";
    @Column(columnDefinition="LONGTEXT")
    private String consequences = "";
    @Column(columnDefinition="LONGTEXT")
    private String controlActivities = "";
    private String efficacite = "";
    private String nbIncidents = "";
    private String frequence = "";
    private String impact = "";
    private String exposition = "";
    private String criticalityLevel = "";

    private String optionAcceptCout = "";
    private String optionAvoidCout = "";
    private String optionMitigateCout = "";
    private String optionTransferCout = "";
    private String beneficeAcceptCout = "";
    private String beneficeAvoidCout = "";
    private String beneficeMitigateCout = "";
    private String beneficeTransferCout = "";


    private String optionOptimaleCal = "";

    @Column(columnDefinition="LONGTEXT")
    private String optionOptimale = "";
    @Column(columnDefinition="LONGTEXT")
    private String besoins = "";
    private String avacncement = "";

    private Boolean isHidden = false;

    @Transient
    private Boolean isDel = false ;


    @ManyToOne
    @JoinColumn(name = "organization_id")
    @ToString.Exclude
    private Organization organization;


    @Transient
    @ToString.Exclude
    private List<SousProcessus> sousProcessusList;

    @JsonManagedReference
    public List<SousProcessus> getSousProcessusList() {
        return sousProcessusList;
    }




    @OneToOne
    @JoinColumn(name = "metrics_of_risk_governance_id" , referencedColumnName = "id")
    private MetricsOfRiskGovernance metricsOfRiskGovernance ;


    @JsonManagedReference
    public MetricsOfRiskGovernance getMetricsOfRiskGovernance() {
        return metricsOfRiskGovernance;
    }

    @OneToOne
    @JoinColumn(name = "kri_measures_id" , referencedColumnName = "id")
    private KriMeasures kriMeasures;

    @JsonManagedReference
    public KriMeasures getKriMeasures() {
        return kriMeasures;
    }
}
