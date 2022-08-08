package ma.ehtp.gestionrisqueit.modelles;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConcurrentRisk {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String risk;

    private  String mitigationPlans;




    @ManyToOne
    @JoinColumn(name = "concurrent_id")
    private Concurrent concurrent;

    @JsonBackReference
    public Concurrent getConcurrent() {
        return concurrent;
    }
}
