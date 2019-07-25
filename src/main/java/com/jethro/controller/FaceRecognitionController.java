package com.jethro.controller;

import com.baidu.aip.util.Base64Util;
import com.jethro.service.FaceRecognitionService;
import com.jethro.utils.response.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: 沈佳俊
 * @CreateTime: 2019-07-25
 * @Description: 百度人脸识别
 */

@RestController
@RequestMapping("/api/face")
@Api(value = "FaceRecognitionController", description = "人脸识别接口")
public class FaceRecognitionController {

    @Autowired
    private FaceRecognitionService faceRecognitionService;

    private static final String userId = "1";


    @ApiOperation(value = "上传人脸照片进行注册")
    @PostMapping("/registerPic")
    public ResponseVO registerPic(@RequestParam("file") MultipartFile file) throws IOException {


        //构造base64图片字符串
        byte[] bytes = file.getBytes();
        String image = Base64Util.encode(bytes);

        //判断图片中是否包含人脸
        Boolean isFace = faceRecognitionService.check(image);
        if (!isFace) {
            return ResponseVO.build().setCode(500).setMessage("人脸识别失败!");
        }


        // 人脸注册
        Boolean flag = faceRecognitionService.register(image, userId);
        if (flag) {
            return ResponseVO.build().setCode(200).setMessage("注册成功!");
        } else {
            return ResponseVO.build().setCode(500).setMessage("注册失败!");
        }

    }


    @ApiOperation(value = "上传人脸照片进行更新!")
    @PostMapping("/updatePic")
    @CrossOrigin
    public ResponseVO updatePic(@RequestParam("file") MultipartFile file) throws IOException {

        byte[] bytes = file.getBytes();
        String image = Base64Util.encode(bytes);
        //判断图片中是否包含人脸
        Boolean isFace = faceRecognitionService.check(image);
        if (!isFace) {
            return ResponseVO.build().setCode(500).setMessage("人脸识别失败!");
        }

        // 人脸更新
        Boolean flag = faceRecognitionService.update(image, userId);
        if (flag) {
            return ResponseVO.build().setCode(200).setMessage("更新成功!");
        } else {
            return ResponseVO.build().setCode(500).setMessage("更新失败!");
        }

    }


    @ApiOperation(value = "上传人脸照片进行登录")
    @PostMapping("/login")
    public ResponseVO checkPic(@RequestParam("file") MultipartFile file) throws IOException {

        byte[] bytes = file.getBytes();
        String image = Base64Util.encode(bytes);
        //判断图片中是否包含人脸
        Boolean isFace = faceRecognitionService.check(image);
        if (!isFace) {
            return ResponseVO.build().setCode(500).setMessage("人脸识别失败");
        }

        // 人脸注册
        String flag = faceRecognitionService.login(image);
        if (flag == null) {
            return ResponseVO.build().setCode(500).setMessage("登陆失败");
        } else {
            return ResponseVO.build().setCode(200).setMessage("登录成功,用户id为:" + flag);
        }
    }


}
