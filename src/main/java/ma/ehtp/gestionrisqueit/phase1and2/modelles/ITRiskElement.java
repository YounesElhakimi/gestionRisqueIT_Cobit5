package ma.ehtp.gestionrisqueit.modelles;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    private String source;
    private String evenement;
    private String categorie;
    private String frequence;
    private String facteur;
    private String impact;


    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
