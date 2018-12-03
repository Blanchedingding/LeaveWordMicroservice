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

    @Override
    public Response userLogin(String userName, String userPassword) {
        if(CommonTools.isEmpty(userName))
            return new Response("-1","用户名不能为空");
        if(CommonTools.isEmpty(userPassword))
            return new Response("-1","用户密码不能为空");
        try {
            Response response = restTemplate.getForEntity("http://user-service:8081/user", Response.class, userName).getBody();
            if(!response.getStatus().equals("0")){
                return new Response("-1","获取用户异常");
            }
            JSONObject user = JSONObject.parseObject((String) response.getContent());
            if(user != null){
                if(user.getString("userPassword").equals(userPassword)) {
                    user.put("userPassword","");
                    return new Response("0", JSON.toJSONString(user));
                }
                else
                    return new Response("-1","密码错误");
            }
            else
                return new Response("-1","用户不存在");
        } catch (Exception e){
            e.printStackTrace();
            return new Response("-1","网络调用错误");
        }
    }

}
