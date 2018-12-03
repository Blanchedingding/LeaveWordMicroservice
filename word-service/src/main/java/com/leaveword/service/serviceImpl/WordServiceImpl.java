package com.leaveword.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.leaveword.domain.Word;
import com.leaveword.repository.WordRepository;
import com.leaveword.service.serviceApi.WordService;
import org.fdse.commonservice.utils.CommonTools;
import org.fdse.commonservice.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WordRepository wordRepository;

    @Override
    public Response getWords(Integer userId) {
        List<Word> words = wordRepository.findAll();
        if(words.size()>0)
            return new Response("0", JSONArray.toJSONString(words));
        else
            return new Response("0", "没有留言");
    }

    @Override
    public Response leaveWord(Integer userId, String title, String content) {
        try {
            Response response = restTemplate.getForEntity("http://user-service:8081/user", Response.class, userId).getBody();
            if (!response.getStatus().equals("0")) {
                return new Response("-1", "用户不存在");
            }
        } catch (Exception e){
            e.printStackTrace();
            return new Response("-1","服务调用异常");
        }
        if(CommonTools.isEmpty(title))
            return new Response("-1","标题不能为空");
        if(CommonTools.isEmpty(content))
            return new Response("-1","内容不能为空");
        Word word = new Word();
        word.setUserId(userId);
        word.setTitle(title);
        word.setContent(content);
        word.setLeaveTime(CommonTools.getCurrentTime());
        wordRepository.save(word);
        return new Response("0", JSON.toJSONString(word));
    }
}
