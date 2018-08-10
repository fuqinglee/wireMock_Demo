package com.qiyi.HTTP;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetNeedHeader {
    /**
     * 发送HttpGet请求
     * @param url
     * @param header
     * @return
     */
    public static String sendGet(String url, Map <String, String> header) {
        //1.获得一个httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //2.生成一个get请求
        HttpGet httpget = new HttpGet(url);

        if(header.size()!=0){
            //map遍历
            for(Object a: header.keySet() ){
                httpget.setHeader((String) a,header.get(a));
            }
        }
        
        CloseableHttpResponse response = null;
        try {
            //3.执行get请求并返回结果
            response = httpclient.execute(httpget);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            //4.处理结果，这里将结果返回为字符串
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch ( IOException e) {
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
