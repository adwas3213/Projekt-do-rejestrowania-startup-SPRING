package com.example.registerstartupproject.userConnectedAcctionsExceptLogin.EmailConnectedActions;

import com.example.registerstartupproject.Repository.Entity.Address;
import com.example.registerstartupproject.Repository.Entity.Member;
import com.example.registerstartupproject.Repository.Entity.RegisterTeam;
import com.example.registerstartupproject.Repository.MemberRepository;
import com.example.registerstartupproject.userConnectedAcctionsExceptLogin.DTO.AddressDtoOuter;
import com.example.registerstartupproject.userConnectedAcctionsExceptLogin.DTO.MemberDtoOuter;
import com.example.registerstartupproject.userConnectedAcctionsExceptLogin.DTO.RegisterDtoOuter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegisterMapper {
    private final MemberRepository memberRepository;

    public RegisterTeam mapToNewRegisterTeamRegisterDtoOuter(RegisterDtoOuter registerDtoOuter) {
        RegisterTeam registerTeam = new RegisterTeam();
        registerTeam.setEmail(registerDtoOuter.getEmail());
        registerTeam.setEnabled(false);
        registerTeam.setPassword(registerDtoOuter.getPassword());
        registerTeam.setUsername(registerDtoOuter.getUsername());
        registerTeam.setMembers(registerDtoOuter.getMembers()
                .stream().map(this::mapToMemberMemberDtoOuter)
                .collect(Collectors.toList()));

        return registerTeam;
    }

    @Transactional
    public RegisterTeam mapToRegisterTeamRegisterDtoOuter(RegisterDtoOuter registerDtoOuter, RegisterTeam registerTeam) {
        List<Member> members = registerTeam.getMembers();
        memberRepository.deleteAll(members);

        registerTeam.setUsername(registerDtoOuter.getUsername());
        registerTeam.setMembers(registerDtoOuter.getMembers()
                .stream().map(this::mapToMemberMemberDtoOuter)
                .collect(Collectors.toList()));
        return registerTeam;
    }

    public Address mapToAddressAddressDtoOuter(AddressDtoOuter addressDtoOuter) {
        Address address = new Address();
        address.setCity(addressDtoOuter.getCity());
        address.setNumber(addressDtoOuter.getNumber());
        address.setPostal(addressDtoOuter.getPostal());
        address.setStreet(addressDtoOuter.getStreet());
        return address;
    }

    public Member mapToMemberMemberDtoOuter(MemberDtoOuter memberDtoOuter) {

        Member member = new Member();
        member.setAddress(mapToAddressAddressDtoOuter(memberDtoOuter.getAddress()));
        member.setEmail(memberDtoOuter.getEmail());
        member.setLeader(memberDtoOuter.getIsLeader());

        member.setSchool(memberDtoOuter.getSchool());
        member.setName(memberDtoOuter.getName());
        member.setSurname(memberDtoOuter.getSurname());
        member.setPhoneNumber(memberDtoOuter.getPhoneNumber());

        return member;
    }


}
