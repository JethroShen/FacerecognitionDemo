package com.jethro.utils;

import com.baidu.aip.face.AipFace;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @Author: 沈佳俊
 * @CreateTime: 2019-07-25
 * @Description: 百度人脸识别工具类
 */
@Component
public class FaceRecognitionUtil {

    @Value("${ai.appId}")
    private String APP_ID;

    @Value("${ai.apiKey}")
    private String API_KEY;

    @Value("${ai.secretKey}")
    private String SECRET_KEY;

    @Value("${ai.imageType}")
    private String IMAGE_TYPE;

    @Value("${ai.groupId}")
    private String groupId;

    private AipFace client;

    private HashMap<String, String> options = new HashMap<String, String>();

    public FaceRecognitionUtil() {
        /*
        图片质量控制
        NONE: 不进行控制           LOW:较低的质量要求
        NORMAL: 一般的质量要求      HIGH: 较高的质量要求   默认 NONE
        */
        options.put("quality_control", "NORMAL");

        /*
        活体检测控制
        NONE: 不进行控制         LOW:较低的活体要求(高通过率 低攻击拒绝率)
        NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率)     HIGH: 较高的活体要求(高攻击拒绝率 低通过率)  默认NONE
         */
        options.put("liveness_control", "LOW");
    }

    @PostConstruct
    public void init() {
        client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
    }

    //人脸注册 ：将用户照片存入人脸库中
    public Boolean faceRegister(String userId, String image) {
        JSONObject res = client.addUser(image, IMAGE_TYPE, groupId, userId, options);
        Integer errorCode = res.getInt("error_code");
        return errorCode == 0 ? true : false;
    }

    //人脸更新 ：更新人脸库中的用户照片
    public Boolean faceUpdate(String userId, String image) {
        JSONObject res = client.updateUser(image, IMAGE_TYPE, groupId, userId, options);
        Integer errorCode = res.getInt("error_code");
        return errorCode == 0 ? true : false;
    }


    //人脸检测：判断上传图片中是否具有面部头像
    public Boolean faceCheck(String image) {
        JSONObject res = client.detect(image, IMAGE_TYPE, options);
        if (res.has("error_code") && res.getInt("error_code") == 0) {
            JSONObject resultObject = res.getJSONObject("result");
            Integer faceNum = resultObject.getInt("face_num");
            return faceNum == 1 ? true : false;
        } else {
            return false;
        }
    }


    /**
     *   * 人脸查找：查找人脸库中最相似的人脸并返回数据
     *   * 处理：用户的匹配得分（score）大于80分，即可认为是同一个用户
     */
    public String faceSearch(String image) {
        JSONObject res = client.search(image, IMAGE_TYPE, groupId, options);
        if (res.has("error_code") && res.getInt("error_code") == 0) {
            JSONObject result = res.getJSONObject("result");
            JSONArray userList = result.getJSONArray("user_list");
            if (userList.length() > 0) {
                JSONObject user = userList.getJSONObject(0);
                double score = user.getDouble("score");
                if (score > 80) {
                    return user.getString("user_id");
                }
            }
        }
        return null;
    }


}
