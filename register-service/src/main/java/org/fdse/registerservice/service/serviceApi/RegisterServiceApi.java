package org.fdse.registerservice.service.serviceApi;

import org.fdse.commonservice.utils.Response;

public interface RegisterServiceApi {
    Response userRegister(String userName, String userPassword);
}
