package ma.ehtp.gestionrisqueit.phase4.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase4.modelles.dto.ControlesAssuranceQualiteDTO;
import ma.ehtp.gestionrisqueit.phase4.modelles.dto.ImpactIncidentDTO;

import java.util.List;

public interface ControlesAssuranceQualiteService {
    public List<ControlesAssuranceQualiteDTO> saveAll(List<ControlesAssuranceQualiteDTO> cTAOList);
    public List<ControlesAssuranceQualiteDTO> findByOrganization(Organization organization);
    public List<ImpactIncidentDTO> findOldAndNew(Organization organization);
}
