package com.be.sso.backend.dto;

import com.be.sso.backend.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
    Long id;
    String email;
    String password;
    String name;
    String address;
    String department;
    String role;

    @Autowired
    private static ModelMapper modelMapper;

    static{
        modelMapper = new ModelMapper();
    }

    public static Users convertDtoToEntity(UsersDTO usersDTO){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(usersDTO, Users.class);
    }
    public static UsersDTO convertEntityToDto(Users users) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(users, UsersDTO.class);
    }

}
