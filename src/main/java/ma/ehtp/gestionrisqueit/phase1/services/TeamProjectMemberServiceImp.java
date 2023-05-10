package ma.ehtp.gestionrisqueit.phase1.services;


import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase1.modelles.TeamProjectMember;
import ma.ehtp.gestionrisqueit.phase1.repositories.TeamProjectMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamProjectMemberServiceImp implements TeamProjectMemberService{

    @Autowired
    TeamProjectMemberRepository teamProjectMemberRepository;

    @Override
    public TeamProjectMember save(TeamProjectMember des) {
        return teamProjectMemberRepository.save(des);
    }

    @Override
    public List<TeamProjectMember> findAll() {
        return teamProjectMemberRepository.findAll();
    }

    @Override
    public List<TeamProjectMember> saveAll(List<TeamProjectMember> teamProjectMembers) {
        return teamProjectMemberRepository.saveAll(teamProjectMembers);
    }

    @Override
    public List<TeamProjectMember> findByOrganization(Organization organization) {
        return teamProjectMemberRepository.findByOrganization(organization);
    }

    @Override
    public void deleteAll(List<TeamProjectMember> teamProjectMembers) {
        teamProjectMemberRepository.deleteAll(teamProjectMembers);
    }
}
