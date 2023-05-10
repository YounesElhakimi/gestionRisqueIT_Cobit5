package ma.ehtp.gestionrisqueit.phase1.modelles;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ImplementationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String levelofdetails ;

    private Boolean p1e1 = false;
    private Boolean p1e2 = false;
    private Boolean p1e3 = false;

    private Boolean p2 = false;
    private Boolean p3e1 = false;
    private Boolean p3e2 = false;
    private Boolean p4e1 = false;
    private Boolean p4e2 = false;
    private Boolean p5e1 = false;
    private Boolean p5e2 = false;
    private Boolean p5e3 = false;



    @OneToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;


}
