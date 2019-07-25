package com.jethro.service.imlp;

import com.jethro.service.FaceRecognitionService;
import com.jethro.utils.FaceRecognitionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 沈佳俊
 * @CreateTime: 2019-07-25
 * @Description: ${Description}
 */
@Service
public class FaceRecognitionServiceImpl implements FaceRecognitionService{

    @Autowired
    private FaceRecognitionUtil faceRecognitionUtil;


    @Override
    public Boolean register(String image, String userId) {
        return faceRecognitionUtil.faceRegister(userId,image);
    }

    @Override
    public Boolean check(String image) {
        return faceRecognitionUtil.faceCheck(image);
    }

    @Override
    public Boolean update(String image, String userId) {
        return faceRecognitionUtil.faceUpdate(userId,image);
    }

    @Override
    public String login(String image) {
        return faceRecognitionUtil.faceSearch(image);
    }
}
