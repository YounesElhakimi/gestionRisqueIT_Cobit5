package ma.ehtp.gestionrisqueit.phase3.modelles;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RiskEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(columnDefinition="LONGTEXT")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private Date date;

    @Column(columnDefinition="LONGTEXT")
    private String cause;

    @Column(columnDefinition="LONGTEXT")
    private String impacte;

    private String impacteMAD;

    @Column(columnDefinition="LONGTEXT")

    private String risque;


    @Column(columnDefinition="LONGTEXT")
    private String actionPlan;

    private String avancement;


    @ManyToOne
    @JoinColumn(name = "organization_id")
    @ToString.Exclude
    private Organization organization;

}
