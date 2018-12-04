package org.fdse.registerservice.service.serviceImpl;

import org.fdse.commonservice.utils.Response;
import org.fdse.registerservice.service.serviceApi.RegisterServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RegisterServiceImpl implements RegisterServiceApi {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Response userRegister(String userName, String userPassword) {
        MultiValueMap<String,String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("userName", userName);
        requestEntity.add("userPassword", userPassword);
        ResponseEntity<Response> responseEntity = restTemplate.postForEntity("http://user-service:8081/user", requestEntity ,Response.class);
        return responseEntity.getBody();
    }
}
