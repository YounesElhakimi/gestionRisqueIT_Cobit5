package ma.ehtp.gestionrisqueit.phase2.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase2.modelles.ITRiskElement;
import ma.ehtp.gestionrisqueit.phase2.modelles.ITRiskElementSource;
import ma.ehtp.gestionrisqueit.phase2.repositories.ITRiskElementRepository;
import ma.ehtp.gestionrisqueit.phase2.repositories.ITRiskElementSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ITRiskElementSourceServiceImp implements ITRiskElementSourceService{
    @Autowired
    ITRiskElementSourceRepository sourceRepository;

    @Autowired
    ITRiskElementRepository elementRepository;

    @Override
    public ITRiskElementSource save(ITRiskElementSource itRiskElementSource) {
        ITRiskElementSource irs = sourceRepository.save(itRiskElementSource);
        List<ITRiskElement> itRiskElementList = itRiskElementSource.getItRiskElementList();
        for (ITRiskElement ire:itRiskElementList
             ) {
            ire.setSource(irs);
        }

        irs.setItRiskElementList(elementRepository.saveAll(itRiskElementList));
        return irs;
    }

    @Override
    public List<ITRiskElementSource> findAll() {
        return null;
    }

    @Override
    public List<ITRiskElementSource> saveAll(List<ITRiskElementSource> itRiskElementSources) {
        List<ITRiskElementSource> irsList = sourceRepository.saveAll(itRiskElementSources);
        for (ITRiskElementSource irs:irsList) {
            List<ITRiskElement> ireList = irs.getItRiskElementList();
            for (ITRiskElement ire :ireList) {
                ire.setSource(irs);
            }
            irs.setItRiskElementList( elementRepository.saveAll(ireList));

        }

        return irsList;
    }

    @Override
    public List<ITRiskElementSource> findByOrganization(Organization organization) {
        List<ITRiskElementSource> irsList = sourceRepository.findByOrganization(organization);
        for (ITRiskElementSource irs:irsList) {
            List<ITRiskElement> ireList = elementRepository.findBySource(irs);
            irs.setItRiskElementList(ireList);
        }
        return irsList;
    }

    @Override
    public void deleteAll(List<ITRiskElementSource> irsList) {
        for (ITRiskElementSource irs:irsList) {

            elementRepository.deleteAll(
                    elementRepository.findBySource(irs)

            );

            sourceRepository.deleteAll(irsList);

        }

        U.ptxt("fin de deleteAll");

    }

    @Override
    public List<ITRiskElement> findBySource(ITRiskElementSource itRiskElementSource) {
        List<ITRiskElement> itRiskElementList = elementRepository.findBySource(itRiskElementSource);
        return itRiskElementList;
    }
}
