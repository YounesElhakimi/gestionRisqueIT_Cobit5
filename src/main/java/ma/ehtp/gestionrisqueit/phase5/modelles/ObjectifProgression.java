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
public class ObjectifProgression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="LONGTEXT")
    private String objectif;

    @Column(columnDefinition="LONGTEXT")
    private String risque;


    private String cible;
    private String realisation;
    private String analyseEcart;


    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
