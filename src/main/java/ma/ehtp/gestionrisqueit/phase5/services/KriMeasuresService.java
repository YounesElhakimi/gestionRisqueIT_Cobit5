package ma.ehtp.gestionrisqueit.phase5.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase5.modelles.KriMeasures;
import ma.ehtp.gestionrisqueit.phase5.modelles.dto.KriMeasuresDTO;

import java.util.List;

public interface KriMeasuresService {
    public KriMeasures findByRiskMapping(RiskMapping riskMapping);
    public KriMeasures save(KriMeasuresDTO kriMeasuresDTO);
    public List<KriMeasures> saveAll(List<KriMeasuresDTO> kriMeasuresDTOList);
    public List<KriMeasuresDTO> findByOrganization(Organization organization);

}
