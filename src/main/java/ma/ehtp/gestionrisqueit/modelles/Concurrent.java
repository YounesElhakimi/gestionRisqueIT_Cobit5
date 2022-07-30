package ma.ehtp.gestionrisqueit.modelles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Concurrent {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name ;


    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Concurrent> concurrentList;




}
