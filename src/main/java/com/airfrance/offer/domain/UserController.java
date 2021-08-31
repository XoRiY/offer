package com.airfrance.offer.domain;


import com.airfrance.offer.domain.common.model.QueryResponse;
import com.airfrance.offer.domain.model.UserBean;
import com.airfrance.offer.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/{id}", produces = {"application/json"})
    public ResponseEntity<QueryResponse<UserBean>> get(@PathVariable("id") Long id) {

        QueryResponse<UserBean> userBeanQueryResponse = userService.getUser(id);

        return new ResponseEntity<>(userBeanQueryResponse, userBeanQueryResponse.getStatus());

    }


    @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<QueryResponse<UserBean>> post(@RequestBody @NotNull UserBean userBean) {

        QueryResponse<UserBean> userBeanQueryResponse = userService.saveUser(userBean);

        return new ResponseEntity<>(userBeanQueryResponse, userBeanQueryResponse.getStatus());

    }

}
