package ma.ehtp.gestionrisqueit.phase1.modelles;

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
public class MajorRisks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    private String title;

    @Column(columnDefinition="LONGTEXT")
    private  String description;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
