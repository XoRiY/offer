package com.ktr.offer.domain;


import com.ktr.offer.domain.user.model.UserBean;
import com.ktr.offer.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author Tahar Kerdoud
 * @apiNote Rest controller
 */
@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/{id}", produces = {"application/json"})
    public ResponseEntity<UserBean> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<UserBean> post(@RequestBody UserBean userBean) {
        return new ResponseEntity<>(userService.saveUser(userBean), HttpStatus.CREATED);
    }

}
