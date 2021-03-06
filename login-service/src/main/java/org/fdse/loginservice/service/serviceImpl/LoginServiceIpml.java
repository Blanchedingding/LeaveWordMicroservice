package org.fdse.loginservice.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.fdse.commonservice.utils.CommonTools;
import org.fdse.commonservice.utils.Response;
import org.fdse.loginservice.service.serviceApi.LoginServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceIpml implements LoginServiceApi {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Response userLogin(String userName, String userPassword) {
        if(CommonTools.isEmpty(userName))
            return new Response("-1","用户名不能为空");
        if(CommonTools.isEmpty(userPassword))
            return new Response("-1","用户密码不能为空");
        try {
            Map<String,String> requestEntity = new HashMap<>();
            requestEntity.put("userName", userName);
            Response response = restTemplate.getForEntity("http://user-service:8081/user/filter?userName={userName}", Response.class, requestEntity).getBody();
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
