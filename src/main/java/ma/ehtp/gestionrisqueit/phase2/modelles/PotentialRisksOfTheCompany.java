package ma.ehtp.gestionrisqueit.phase2.modelles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;

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

    @Column(columnDefinition="LONGTEXT")
    private String incident;

    @Column(columnDefinition="LONGTEXT")
    private  String mitigationPlans;



    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization ;


}
