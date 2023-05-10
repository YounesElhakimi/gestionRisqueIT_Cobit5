package ma.ehtp.gestionrisqueit.phase2.modelles;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;

import javax.persistence.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ITRiskElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String evenement;
    @Column(columnDefinition="LONGTEXT")

    private String categorie;
    private String frequence;

    @Column(columnDefinition="LONGTEXT")

    private String facteur;
    private String impact;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "source_id")
    private ITRiskElementSource source;


    @JsonBackReference

    public ITRiskElementSource getSource() {
        return source;
    }
}
