package com.leaveword.service.serviceApi;


import org.fdse.commonservice.utils.Response;

public interface WordService {
    Response getWords(Integer userId);
    Response leaveWord(Integer userId,String title,String content);
}
