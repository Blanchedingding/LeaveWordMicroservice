package com.leaveword.controller;

import com.leaveword.service.serviceApi.UserService;
import org.fdse.commonservice.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 注册新用户
     *
     * @param userName
     * @param userPassword
     * @return
     */
    @PostMapping(value = "/userRegister")
    public Response userRegister(@RequestParam("userName")String userName,
                                 @RequestParam("userPassword")String userPassword){
        return userService.userRegister(userName,userPassword);
    }
}
