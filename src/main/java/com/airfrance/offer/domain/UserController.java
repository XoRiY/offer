package com.airfrance.offer.domain;


import com.airfrance.offer.domain.common.model.QueryResponse;
import com.airfrance.offer.domain.model.UserBean;
import com.airfrance.offer.domain.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(path = "/{id}", produces = {"application/json"})
    public ResponseEntity<QueryResponse<UserBean>> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);

    }


    @PostMapping(produces = {"application/json"}, consumes = {"application/json"} )
    public ResponseEntity<QueryResponse<UserBean>> post(@RequestBody @NotNull UserBean userBean) {
        return new ResponseEntity<>(userService.saveUser(userBean), HttpStatus.ACCEPTED);

    }

}
