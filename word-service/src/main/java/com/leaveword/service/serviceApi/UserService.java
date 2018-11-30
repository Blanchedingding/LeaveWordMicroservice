package com.leaveword.service.serviceApi;

import org.fdse.commonservice.utils.Response;

public interface UserService {
//    Response getUser(Integer userId);
    Response userRegister(String userName,String userPassword);
    Response userLogin(String userName,String userPassword);
}
