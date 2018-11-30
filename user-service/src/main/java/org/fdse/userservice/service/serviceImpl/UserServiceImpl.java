package org.fdse.userservice.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import org.fdse.commonservice.utils.CommonTools;
import org.fdse.commonservice.utils.Response;
import org.fdse.userservice.domain.User;
import org.fdse.userservice.repository.UserRepository;
import org.fdse.userservice.service.serviceApi.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceApi {

    @Autowired
    UserRepository userRepository;

    @Override
    public Response getUser(Integer userId) {
        User user;
        if((user = userRepository.findUserByUserId(userId))!=null)
            return new Response("0", JSON.toJSONString(user));
        else
            return new Response("-1","用户不存在");
    }

    @Override
    public Response getUserByName(String userName) {
        User user;
        if((user = userRepository.findUserByUserName(userName))!=null)
            return new Response("0", JSON.toJSONString(user));
        else
            return new Response("-1","用户不存在");
    }

    @Override
    public Response addUser(String userName, String userPassword) {
        if(CommonTools.isEmpty(userName))
            return new Response("-1","用户名不能为空");
        if(CommonTools.isEmpty(userPassword))
            return new Response("-1","用户密码不能为空");
        try {
            if(userRepository.findUserByUserName(userName)!=null)
                return new Response("-1","此用户名已经存在");
            User user = new User();
            user.setUserName(userName);
            user.setUserPassword(userPassword);
            user.setRegisterTime(CommonTools.getCurrentTime());
            user = userRepository.save(user);
            user.setUserPassword("");
            return new Response("0", JSON.toJSONString(user));
        }catch (Exception e){
            return new Response("-1", "插入用户异常");
        }
    }
}
