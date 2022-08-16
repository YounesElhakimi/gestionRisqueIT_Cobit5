package ma.ehtp.gestionrisqueit.services;


import ma.ehtp.gestionrisqueit.modelles.TeamProjectMember;
import ma.ehtp.gestionrisqueit.repositories.TeamProjectMemberRepository;
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
}
