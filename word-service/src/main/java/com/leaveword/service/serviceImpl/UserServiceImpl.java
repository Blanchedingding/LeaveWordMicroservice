package com.leaveword.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leaveword.service.serviceApi.UserService;
import org.fdse.commonservice.utils.CommonTools;
import org.fdse.commonservice.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Response userRegister(String userName, String userPassword) {
        MultiValueMap<String,String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("userName", userName);
        requestEntity.add("userPassword", userPassword);
        ResponseEntity<Response> responseEntity = restTemplate.postForEntity("http://user-service:8081/user", requestEntity ,Response.class);
//        ResponseEntity<Response> responseEntity = restTemplate.getForEntity("http://user-service:8081/user/"+ userName + "/" + userPassword, Response.class);
        return responseEntity.getBody();

    }

}
