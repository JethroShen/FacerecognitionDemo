package com.jethro.service;

/**
 * @Author: 沈佳俊
 * @CreateTime: 2019-07-25
 * @Description: ${Description}
 */
public interface FaceRecognitionService {
    Boolean register(String image, String userId);

    Boolean check(String image);

    Boolean update(String image, String userId);

    String login(String image);
}
