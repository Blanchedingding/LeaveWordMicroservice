package org.fdse.userservice.service.serviceApi;

import org.fdse.commonservice.utils.Response;

public interface UserServiceApi {
    Response getUser(Integer userId);
    Response getUserByName(String userName);
    Response addUser(String userName, String userPassword);
}
