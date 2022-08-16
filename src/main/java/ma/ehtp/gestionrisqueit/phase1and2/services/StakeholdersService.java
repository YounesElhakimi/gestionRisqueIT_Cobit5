package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Description;
import ma.ehtp.gestionrisqueit.modelles.Stakeholders;

import java.util.List;

public interface StakeholdersService {
    public Stakeholders save(Stakeholders sh);
    public List<Stakeholders> findAll();
    public List<Stakeholders> saveAll(List<Stakeholders> stakeholders);
}
