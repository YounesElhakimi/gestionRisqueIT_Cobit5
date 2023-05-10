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
@ToString
@Data
public class CommunicationPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="LONGTEXT")
    private String action = "";

    @Column(columnDefinition="LONGTEXT")
    private String objectif = "";

    @Column(columnDefinition="LONGTEXT")
    private String cible = "";

    @Column(columnDefinition="LONGTEXT")
    private String responsable = "";
    private String echeance = "";


    @ManyToOne
    @JoinColumn(name = "organization_id")
    @ToString.Exclude
    private Organization organization;
}
