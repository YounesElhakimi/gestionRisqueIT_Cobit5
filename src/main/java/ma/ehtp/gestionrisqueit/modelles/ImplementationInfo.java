package ma.ehtp.gestionrisqueit.modelles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ImplementationProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private 

}
