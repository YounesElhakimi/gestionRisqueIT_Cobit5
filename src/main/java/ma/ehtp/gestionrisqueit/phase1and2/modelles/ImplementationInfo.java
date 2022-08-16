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
public class ImplementationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String levelofdetails ;

    private String progress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    @JsonIgnore
    private Organization organization;


}
