package org.fdse.userservice.controller;

import org.fdse.commonservice.utils.Response;
import org.fdse.userservice.service.serviceApi.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserServiceApi userServiceApi;

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/user/{userId}")
    public Response user(@PathVariable(name = "userId")Integer userId){
        return userServiceApi.getUser(userId);
    }

    /**
     *
     * @param userName
     * @return
     */
    @GetMapping(value = "/user/{userName}")
    public Response user(@PathVariable(name = "userName")String userName){
        return userServiceApi.getUserByName(userName);
    }

    /**
     *
     * @param userName
     * @param userPassword
     * @return
     */
    @PostMapping(value = "/user/{userName}/{userPassword}")
    public Response user(@PathVariable(name = "userName")String userName, @PathVariable(name = "userPassword")String userPassword){
        return userServiceApi.addUser(userName, userPassword);
    }

}
