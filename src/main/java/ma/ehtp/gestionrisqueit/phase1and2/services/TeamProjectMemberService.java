package ma.ehtp.gestionrisqueit.services;

import ma.ehtp.gestionrisqueit.modelles.Description;
import ma.ehtp.gestionrisqueit.modelles.TeamProjectMember;

import java.util.List;

public interface TeamProjectMemberService {
    public TeamProjectMember save(TeamProjectMember des);
    public List<TeamProjectMember> findAll();
    public List<TeamProjectMember> saveAll(List<TeamProjectMember> teamProjectMembers);
}
