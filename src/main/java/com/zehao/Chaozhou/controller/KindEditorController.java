package com.zehao.Chaozhou.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import com.zehao.Chaozhou.common.GsonSingleton;
import com.zehao.Chaozhou.common.JsonResult;
import com.zehao.Chaozhou.common.config.COSConfig;
import com.zehao.Chaozhou.utils.COSUtil;
import com.zehao.Chaozhou.utils.DateConvert;
import com.zehao.Chaozhou.utils.Logger;
import com.zehao.Chaozhou.utils.Picture;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class KindEditorController {

    private static Gson gson = GsonSingleton.getInstance();

    /**
     * 副文本编辑框的内容获取
     * 获取表单的信息
     * @param request
     * @return
     */
    @RequestMapping(value="/upload",produces="application/json;charset=utf-8")
    @ResponseBody
    public String upload(HttpServletRequest request){
        String context = request.getParameter("uploadImage");
        System.out.println("打印的内容为："+context);
        return "1111";
    }

    /**
     * 副文本编辑框中的图片上传功能
     * @param file
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping(value="/kindEditorUpload")//与 副文本编辑框中的 uploadJson 参数对应
    @ResponseBody
    public String uploadSingleImage(MultipartFile file) throws IllegalStateException, IOException {
            String filename = "";
            String upload = COSUtil.uploadWithWait(file, Picture.EDITOR);
            JsonElement je = new JsonParser().parse(upload);
            JsonElement imgstatus = je.getAsJsonObject().get("status");
            if(imgstatus != null && imgstatus.getAsLong() == 0) {
                JsonElement data = je.getAsJsonObject().get("data");
                if(data != null && data.getAsString() != null) {
                    filename = data.getAsString();
                }
            }
            Map<Object,Object> info = new HashMap<>();
            info.put("error", 0);
            info.put("url", "https://chaozhou-1257279578.cos.ap-chengdu.myqcloud.com/" + filename);
            return gson.toJson(info);
    }
}
