package com.leaveword.service.serviceApi;

import org.fdse.commonservice.utils.Response;

public interface UserService {
    Response userLogin(String userName,String userPassword);
}
