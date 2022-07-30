package ma.ehtp.gestionrisqueit.modelles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MethodologyToHandleRisk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String description;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

}
