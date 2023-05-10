package ma.ehtp.gestionrisqueit.phase5.modelles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BusinessContinuityPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition="LONGTEXT")
    private String contexte = "";

    @Column(columnDefinition="LONGTEXT")
    private String formaliser = "";

    @Column(columnDefinition="LONGTEXT")
    private String gerreRisque = "";

    @Column(columnDefinition="LONGTEXT")
    private String scenarios = "";

    @Column(columnDefinition="LONGTEXT")
    private String formaliserProcedures = "";

    @Column(columnDefinition="LONGTEXT")
    private String strategieContinuite = "";

    @Column(columnDefinition="LONGTEXT")
    private String criseCommunication = "";

    @Column(columnDefinition="LONGTEXT")
    private String planContinuite = "";

    @Column(columnDefinition="LONGTEXT")
    private String capacitePlan = "";

    @Column(columnDefinition="LONGTEXT")
    private String exercices = "";


    @OneToOne
    @JoinColumn(name = "organization_id")
    @ToString.Exclude
    private Organization organization;
}
