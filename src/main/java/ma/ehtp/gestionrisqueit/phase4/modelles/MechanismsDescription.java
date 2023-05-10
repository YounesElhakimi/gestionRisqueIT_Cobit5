package ma.ehtp.gestionrisqueit.phase4.modelles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MechanismsDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(columnDefinition="LONGTEXT")
    private String  object;

    @Column(columnDefinition="LONGTEXT")
    private String  perimetre;

    @Column(columnDefinition="LONGTEXT")
    private String  regleDeGestion;

    @Column(columnDefinition="LONGTEXT")
    private String  principauxIntervenants;

    @Column(columnDefinition="LONGTEXT")
    private String  logigrammeDuProcessus;

    @Column(columnDefinition="LONGTEXT")
    private String  descriptionDuprocessus;

    @Column(columnDefinition="LONGTEXT")
    private String  reportingPeriodique ;

    @Column(columnDefinition="LONGTEXT")
    private String  gestionDesIncidents ;

    @Column(columnDefinition="LONGTEXT")
    private String  suiviDesKRI ;



    @OneToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

}
