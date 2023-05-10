package ma.ehtp.gestionrisqueit.phase1.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.TeamProjectMember;

import java.util.List;

public interface TeamProjectMemberService {
    public TeamProjectMember save(TeamProjectMember des);
    public List<TeamProjectMember> findAll();
    public List<TeamProjectMember> saveAll(List<TeamProjectMember> teamProjectMembers);
    public List<TeamProjectMember> findByOrganization(Organization organization);
    public void deleteAll(List<TeamProjectMember> teamProjectMembers);
}
