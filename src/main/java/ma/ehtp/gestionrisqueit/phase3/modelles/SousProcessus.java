package ma.ehtp.gestionrisqueit.phase3.modelles;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SousProcessus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition="LONGTEXT")
    private String sousProcessus= "";
    @Column(columnDefinition="LONGTEXT")
    private String applicationsIT= "";
    @Column(columnDefinition="LONGTEXT")
    private String processusIT= "";
    @Column(columnDefinition="LONGTEXT")
    private String infrastructuresIT= "";
    @Column(columnDefinition="LONGTEXT")
    private String critique= "";




    @ManyToOne
    private RiskMapping riskMapping;

    @JsonBackReference
    public RiskMapping getRiskMapping() {
        return riskMapping;
    }
}
