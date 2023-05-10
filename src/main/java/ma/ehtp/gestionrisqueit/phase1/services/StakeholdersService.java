package ma.ehtp.gestionrisqueit.phase1.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.Stakeholders;

import java.util.List;

public interface StakeholdersService {
    public Stakeholders save(Stakeholders sh);
    public List<Stakeholders> findAll();
    public List<Stakeholders> saveAll(List<Stakeholders> stakeholders);
    public List<Stakeholders> findByOrganization(Organization organization);
    public void deleteAll(List<Stakeholders> stakeholders);
}
