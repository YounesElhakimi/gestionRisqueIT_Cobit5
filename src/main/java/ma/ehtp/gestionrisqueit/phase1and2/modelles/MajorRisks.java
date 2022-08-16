package ma.ehtp.gestionrisqueit.modelles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private  String Concurrent;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
