package org.fdse.loginservice.service.serviceApi;

import org.fdse.commonservice.utils.Response;

public interface LoginServiceApi {
    Response userLogin(String userName, String userPassword);
}
