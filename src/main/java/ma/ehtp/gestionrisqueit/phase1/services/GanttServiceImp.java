package ma.ehtp.gestionrisqueit.phase1.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase1.modelles.Gantt;
import ma.ehtp.gestionrisqueit.phase1.repositories.GanttRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GanttServiceImp implements GanttService{
    @Autowired
    GanttRepository ganttRepository;
    @Override
    public Gantt findByOrganization(Organization organization) {
        return ganttRepository.findByOrganization(organization);
    }

    @Override
    public Gantt save(Gantt gantt) {
        // service

        try {

            ganttRepository.delete(
                    ganttRepository.findByOrganization(gantt.getOrganization())
            );

        }catch (Exception e){
            U.ptxt("save f delete gantt e "+e.getMessage());
        }

        return ganttRepository.save(gantt);
    }
}
