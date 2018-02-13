package com.restsample.web.controller;

import com.restsample.data.dto.ResponseDTO;
import com.restsample.data.model.User;
import com.restsample.message.MessageByLocaleService;
import com.restsample.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Arrays;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@Api(value="/users" , description = "Operations for users")
@RequestMapping(value = "/users")
public class UserController {

    @Inject
    private UserService userService;

    @Autowired
    private MessageByLocaleService messageManagerService;

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates one user")
    @ApiResponses(value = { @ApiResponse(code = 201, message=""), @ApiResponse(code = 400, message = "Invalid User")})
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody User user) {
        User newUser = userService.create(user);
        String id = newUser == null ? "" : newUser.getId();
        URI uri = URI.create("/festivity/" + id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Location", Arrays.asList(uri.toString()));

        return new ResponseEntity(
                new ResponseDTO(
                        id,
                        "User Created"),
                    httpHeaders,
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifies a user")
    @ApiResponses(value = { @ApiResponse(code = 200, message=""), @ApiResponse(code = 400, message = "Invalid User")})
    public ResponseEntity update(@PathVariable String username, @Valid @RequestBody User user) {
        User paramUser = userService.findByUsername(username);
        if (paramUser == null) {
            return new ResponseEntity<>(new ResponseDTO(paramUser.getId(),"User not found"),
                HttpStatus.NOT_FOUND);
        }
        user.setId(paramUser.getId());
        User newUser = userService.update(user);
        return new ResponseEntity<>(new ResponseDTO(
            newUser.getId(),
            "User Modified"), HttpStatus.OK);
    }

    @RequestMapping( value = "/{username}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gets one user")
    @ApiResponses(value = { @ApiResponse(code = 200, message=""), @ApiResponse(code = 400, message = "Bad request")})
    public ResponseEntity find(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(new ResponseDTO("","User not found"),
                HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping( value = "/{username}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deletes one user")
    @ApiResponses(value = { @ApiResponse(code = 200, message=""), @ApiResponse(code = 400, message = "Bad request")})
    public ResponseEntity delete( @PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(new ResponseDTO("","User not found"),
                HttpStatus.NOT_FOUND);
        }
        String id = user.getId();
        userService.delete(id);
        return new ResponseEntity<>(new ResponseDTO(id, "User Deleted"), HttpStatus.OK);
    }

}

