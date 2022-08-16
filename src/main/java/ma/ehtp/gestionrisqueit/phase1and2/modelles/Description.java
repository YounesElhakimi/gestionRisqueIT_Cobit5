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
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    private int phase;
    private DescTypes type;

    private String title;
    private  String description;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;



}
