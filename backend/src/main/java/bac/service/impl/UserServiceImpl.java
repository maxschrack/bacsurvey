package bac.service.impl;

import bac.converter.UserConverter;
import bac.dto.DtoList;
import bac.dto.UserDto;
import bac.exception.ServiceException;
import bac.model.User;
import bac.repository.UserRepository;
import bac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    public UserServiceImpl() {
    }

    /*public UserServiceImpl(UserRepository userRepository,
                           UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }*/

    @Override
    @Transactional
    public UserDto create(UserDto toCreate) throws ServiceException {
        if(toCreate == null)
            throw new IllegalArgumentException("Illegal Argument: Null");

        // validate
        // TODO : VALIDATION

        // set Registration date
        toCreate.setRegistrationDate(new Date());

        // convert
        User user = userConverter.toEntity(toCreate);

        // create
        user = userRepository.save(user);

        // convert back and return
        return userConverter.toDto(user);
    }

    @Override
    public UserDto read(UserDto toRead) throws ServiceException {

        // validate

        // search
        User user = userRepository.findOne(toRead.getId());

        // convert and return
        return userConverter.toDto(user);
    }

    @Override
    @Transactional
    public UserDto update(UserDto toUpdate) throws ServiceException {

        // validate

        // convert
        User oldUser = userConverter.toEntity(toUpdate);

        // update
        User newUser = userRepository.save(oldUser);

        // convert and return
        return userConverter.toDto(newUser);
    }

    @Override
    @Transactional
    public UserDto delete(UserDto toDelete) throws ServiceException {

        // validate

        // retrieve user
        User user = userRepository.findOne(toDelete.getId());

        // delete user
        userRepository.delete(toDelete.getId());

        return userConverter.toDto(user);
    }

    @Override
    public DtoList<UserDto> readAll() throws ServiceException {

        // read all
        List<User> users = userRepository.findAll();

        // convert and return
        return userConverter.toDtoList(users);
    }
}
