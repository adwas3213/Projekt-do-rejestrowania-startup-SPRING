package com.example.registerstartupproject.registerAndEmailValidate;

import com.example.registerstartupproject.Repository.Entity.Address;
import com.example.registerstartupproject.Repository.Entity.Member;
import com.example.registerstartupproject.Repository.Entity.RegisterTeam;
import com.example.registerstartupproject.registerAndEmailValidate.DTO.AddressDtoOuter;
import com.example.registerstartupproject.registerAndEmailValidate.DTO.MemberDtoOuter;
import com.example.registerstartupproject.registerAndEmailValidate.DTO.RegisterDtoOuter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RegisterMapper {

    RegisterTeam mapToRegisterTeamRegisterDtoOuter(RegisterDtoOuter registerDtoOuter) {
        RegisterTeam registerTeam = new RegisterTeam();
        registerTeam.setEmail(registerDtoOuter.getEmail());
        registerTeam.setEnabled(false);
        registerTeam.setPassword(registerDtoOuter.getPassword());
        registerTeam.setUsername(registerDtoOuter.getUserName());
        registerTeam.setMembers(registerDtoOuter.getMembers()
                .stream().map(this::mapToMemberMemberDtoOuter)
                .collect(Collectors.toList()));


        return registerTeam;
    }

    Address mapToAddressAddressDtoOuter(AddressDtoOuter addressDtoOuter) {
        ///OK
        Address address = new Address();
        address.setCity(addressDtoOuter.getCity());
        address.setNumber(addressDtoOuter.getNumber());
        address.setPostal(addressDtoOuter.getPostal());
        address.setStreet(addressDtoOuter.getStreet());
        return address;
    }

    Member mapToMemberMemberDtoOuter(MemberDtoOuter memberDtoOuter) {

        Member member = new Member();
        member.setAddress(mapToAddressAddressDtoOuter(memberDtoOuter.getAdress()));
        member.setEmail(memberDtoOuter.getEmail());
        member.setLeader(memberDtoOuter.getIsLeader());

        member.setSchool(memberDtoOuter.getSchool());
        member.setName(memberDtoOuter.getName());
        member.setSurname(memberDtoOuter.getSurname());
        member.setPhoneNumber(memberDtoOuter.getPhoneNumber());

        return member;
    }


}
