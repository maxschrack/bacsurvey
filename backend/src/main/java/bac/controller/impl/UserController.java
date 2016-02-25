package bac.controller.impl;

import bac.dto.UserDto;
import bac.exception.ServiceException;
import bac.rest.UserRest;
import bac.service.UserService;
import bac.util.DtoFactory;
import bac.util.ModelFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
@Api(value = "/users", description = "User Administration")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(){
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a new User", notes = "")
    public ResponseEntity<UserRest> create(@RequestBody UserRest user, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (userService == null)
            throw new HttpRequestMethodNotSupportedException("POST");

        UserDto response = userService.create(DtoFactory.toDto(user));
        UserRest newUser = ModelFactory.user(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/users/{userId}")
                        .buildAndExpand(response.getId().toString()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(newUser, headers, HttpStatus.CREATED);
    }

    // READ
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    @ApiOperation(value = "Retrieve an User", notes = "")
    public ResponseEntity<UserRest> read(@PathVariable Long userId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (userService == null)
            throw new HttpRequestMethodNotSupportedException("GET");

        // retrieve user from db
        UserDto response = userService.read(new UserDto(userId));
        UserRest user = ModelFactory.user(response);

        // send response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    // UPDATE
    @RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
    @ApiOperation(value = "Update a User", notes = "")
    public ResponseEntity<UserRest> update(@PathVariable Long userId, @RequestBody UserRest user, UriComponentsBuilder builder) throws ServiceException, InstantiationException, IllegalAccessException, HttpRequestMethodNotSupportedException {
        if (userService == null)
            throw new HttpRequestMethodNotSupportedException("PUT");

        UserDto toUpdate = DtoFactory.toDto(user);
        toUpdate.setId(userId);
        UserDto response = userService.update(toUpdate);
        UserRest updatedUser = ModelFactory.user(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/users/{userId}")
                        .buildAndExpand(response.getId().toString()).toUri());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(updatedUser, headers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    @ApiOperation(value = "Delete a User", notes = "")
    public ResponseEntity<UserRest> delete(@PathVariable Long userId) throws ServiceException, HttpRequestMethodNotSupportedException {
        if (userService == null)
            throw new HttpRequestMethodNotSupportedException("DELETE");

        UserDto response = userService.delete(new UserDto(userId));
        UserRest c = ModelFactory.user(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(c, headers, HttpStatus.OK);
    }
}
