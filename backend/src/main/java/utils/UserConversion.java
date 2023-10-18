package utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import com.be.sso.backend.dto.UsersDTO;
import com.be.sso.backend.entity.Users;

public class UserConversion {
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
