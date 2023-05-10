package ma.ehtp.gestionrisqueit.phase2.modelles;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Concurrent {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name ;


    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Transient
    private List<ConcurrentRisk> concurrentRiskList;

    @JsonManagedReference
    public List<ConcurrentRisk> getConcurrentRiskList() {
        return concurrentRiskList;
    }
}
