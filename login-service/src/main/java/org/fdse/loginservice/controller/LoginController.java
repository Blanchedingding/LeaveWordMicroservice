package org.fdse.loginservice.controller;

import org.fdse.commonservice.utils.Response;
import org.fdse.loginservice.service.serviceApi.LoginServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginServiceApi loginServiceApi;

    /**
     * 用户登录
     *
     * @param userName
     * @param userPassword
     * @return
     */
    @PostMapping(value = "/userLogin")
    public Response userLogin(@RequestParam("userName")String userName,
                              @RequestParam("userPassword")String userPassword){
        return loginServiceApi.userLogin(userName,userPassword);
    }
}
