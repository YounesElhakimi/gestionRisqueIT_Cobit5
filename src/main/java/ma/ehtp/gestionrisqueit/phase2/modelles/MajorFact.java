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
public class MajorFact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    private String title;

    @Column(columnDefinition="LONGTEXT")
    private  String description;

    private  Boolean isInternal;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
