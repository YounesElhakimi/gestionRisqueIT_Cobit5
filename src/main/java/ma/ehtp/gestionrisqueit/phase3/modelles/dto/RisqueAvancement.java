package ma.ehtp.gestionrisqueit.phase3.modelles.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RisqueAvancement  {

    private Long rmId;
    private String risque;
    private String avancement;

    public RisqueAvancement(RiskMapping riskMapping) {
        this.rmId = riskMapping.getId();
        this.risque = riskMapping.getRisque();
        this.avancement = riskMapping.getAvacncement();
    }


}
