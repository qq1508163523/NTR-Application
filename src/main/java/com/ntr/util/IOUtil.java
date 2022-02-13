package com.ntr.util;

import com.ntr.api.CommonResult;
import com.ntr.exception.CommonException;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class IOUtil {
    public static <T> void sendResponse(ServletResponse response, CommonResult<T> result) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(ObjectUtil.objectToJsonString(result));
        printWriter.flush();
        printWriter.close();
    }



    public static void createImageFromBase64(String imageData,String path){
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            byte[] bytes = decoder.decodeBuffer(imageData);
            for(int i = 0;i < bytes.length;i++){
                if(bytes[i] < 0){
                    bytes[i] += 256;
                }
            }
            OutputStream outputStream = new FileOutputStream(path);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        }catch (IOException e){
            throw new CommonException("SERVER ERROR");
        }
    }
}
