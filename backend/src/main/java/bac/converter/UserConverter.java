package bac.converter;

import bac.dto.UserDto;
import bac.model.User;
import bac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends Converter<UserDto, User> {

    public UserConverter(){}

    @Override
    public UserDto newDto() {
        return new UserDto();
    }

    @Override
    public User newEntity() {
        return new User();
    }


    public UserDto toDto(User entity){
        UserDto userDto = super.toDto(entity);

        return userDto;
    }

    public User toEntity(UserDto dto){
        User entity = super.toEntity(dto);

        return entity;
    }
}
