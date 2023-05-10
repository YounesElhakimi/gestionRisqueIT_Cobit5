package ma.ehtp.gestionrisqueit.phase2.modelles;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(columnDefinition="LONGTEXT")
    private String risk;

    @Column(columnDefinition="LONGTEXT")
    private  String mitigationPlans;




    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "concurrent_id")
    private Concurrent concurrent;

    @JsonBackReference
    public Concurrent getConcurrent() {
        return concurrent;
    }
}
