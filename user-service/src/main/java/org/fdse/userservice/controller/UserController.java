package org.fdse.userservice.controller;

import org.fdse.commonservice.utils.Response;
import org.fdse.userservice.service.serviceApi.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/user/filter")
    public Response user(@RequestParam(name = "userName")String userName){
        return userServiceApi.getUserByName(userName);
    }

    /**
     *
     * @param userName
     * @param userPassword
     * @return
     */
    @PostMapping(value = "/user")
    public Response user(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword){
        return userServiceApi.addUser(userName, userPassword);
    }

}
