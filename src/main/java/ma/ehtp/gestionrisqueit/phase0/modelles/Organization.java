package ma.ehtp.gestionrisqueit.phase0.modelles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase1.modelles.ImplementationInfo;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String name;


    public Organization(Long id, String name, Object o1) {
        this.id  = id;
        this.name = name;
    }
}
