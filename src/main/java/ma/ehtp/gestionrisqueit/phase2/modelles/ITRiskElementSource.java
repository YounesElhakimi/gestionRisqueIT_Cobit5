package ma.ehtp.gestionrisqueit.phase2.modelles;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ITRiskElementSource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String source;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Transient
    private List<ITRiskElement>  itRiskElementList;

    @JsonManagedReference
    public List<ITRiskElement> getItRiskElementList() {
        return itRiskElementList;
    }
}
