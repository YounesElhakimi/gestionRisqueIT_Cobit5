package ma.ehtp.gestionrisqueit.modelles;

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
public class PotentialRisksOfTheCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String incident;

    private  String mitigationPlans;



    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization ;


}
