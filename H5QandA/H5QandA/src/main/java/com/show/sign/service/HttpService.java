package com.show.sign.service;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class HttpService{

    public  String client(String url, HttpMethod method, MultiValuedMap<String,String> params){
        RestTemplate template=new RestTemplate();
        ResponseEntity<String> response1=template.getForEntity(url,String.class);
        return response1.getBody();
    }
        public String postMap(String url, SortedMap<String,Object> headerMap, SortedMap<String,Object> content) {
            String result = null;
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);

            CloseableHttpResponse response = null;
            try {
                if(headerMap!=null) {
                    Iterator headerIterator = headerMap.entrySet().iterator();          //循环增加header
                    while (headerIterator.hasNext()) {
                        Map.Entry<String, Object> elem = (Map.Entry<String, Object>) headerIterator.next();
                        post.addHeader(elem.getKey(), elem.getValue() + "");
                    }
                }
                if(content!=null){

                    List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

                    for (Iterator iter = content.keySet().iterator(); iter.hasNext(); ) {
                        String name = (String)iter.next();
                        String value = String.valueOf(content.get(name));
                        params.add(new BasicNameValuePair(name,value));
                    }
                    UrlEncodedFormEntity uefEntity;
                    uefEntity = new UrlEncodedFormEntity(params,"UTF-8");
                    post.setEntity(uefEntity);
                }
                response = httpClient.execute(post);            //发送请求并接收返回数据
                if(response != null && response.getStatusLine().getStatusCode() == 200)
                {
                    HttpEntity entity = response.getEntity();       //获取response的body部分
                    result = EntityUtils.toString(entity);          //读取reponse的body部分并转化成字符串
                }
                return result;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    httpClient.close();
                    if(response != null)
                    {
                        response.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }



}
