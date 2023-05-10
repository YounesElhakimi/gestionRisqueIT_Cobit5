package ma.ehtp.gestionrisqueit.phase5.modelles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ProblemDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id ;

    @Column(columnDefinition="LONGTEXT")
    private  String description = "";

    @OneToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
