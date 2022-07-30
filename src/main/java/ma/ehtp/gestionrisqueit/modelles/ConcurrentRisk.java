package ma.ehtp.gestionrisqueit.modelles;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class ConcurrentRisk {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String risk;

    private  String mitigationPlans;




    @ManyToOne
    @JoinColumn(name = "concurrent_id")
    @JsonIgnore
    private Concurrent concurrent;
}
