package ma.ehtp.gestionrisqueit.phase1.modelles;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Gantt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDatePhase1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDatePhase2;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDatePhase3;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDatePhase4;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDatePhase5;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDatePhase1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDatePhase2;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDatePhase3;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDatePhase4;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDatePhase5;

    /*

    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private Date startDatePhase1;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private Date startDatePhase2;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private Date startDatePhase3;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private Date startDatePhase4;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private Date startDatePhase5;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private Date endDatePhase1;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private Date endDatePhase2;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private Date endDatePhase3;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private Date endDatePhase4;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private Date endDatePhase5;


     */

    @OneToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

}
