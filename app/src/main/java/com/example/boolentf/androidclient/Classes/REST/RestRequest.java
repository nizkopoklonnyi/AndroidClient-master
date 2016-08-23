package com.example.boolentf.androidclient.Classes.REST;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
/**
 * Created by BoolenTF on 14.08.2016.
 */
public class RestRequest {
        final static String serverUrl = "http://sn-munchkin-server.cfapps.io/mobile";
        String prefix;
    // statusCode - поле предназначеное для получения статуса запроса.
        String statusCode;
        RestTemplate restTemplate;

        public RestRequest() throws Exception{
            restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        }
        public String getStatus(){
            return statusCode;
        }
//В квчестве запроса отправляем объект, получает ответ- объект. Возвращает объект.
        public <T> T PostObjGetObj(Object request, Class<T> responseType) throws Exception{
            String url = serverUrl + "/" + prefix;
        return restTemplate.postForObject(url,request,responseType);
        }
//В квчестве запроса отправляет объект, получаем ответ- объект. Изменяет поле(statusCode). Возврашает объект.
        public <T> T PostObjGetObjAndStatus(Object request, Class<T> responseType) throws Exception{
            String url = serverUrl + "/" + prefix;
            ResponseEntity <T> rEntity= restTemplate.postForEntity(url,request,responseType);
            statusCode=rEntity.getStatusCode().toString();
            return rEntity.getBody();
        }

    //В квчестве запроса отправляет объект и заголовок, получаем ответ- объект. Изменяет поле(statusCode). Возврашает объект.
    public <T> T PostObjGetObjAndStatus(Object request, Class<T> responseType, String token) throws Exception{
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Authorization",token);
        //MultiValueMap<Object,HttpHeaders> EntityAndHeader= new LinkedMultiValueMap<Object,HttpHeaders>();
        //EntityAndHeader.set(request,requestHeaders);
        HttpEntity requestEntity = new HttpEntity<Object>(request,requestHeaders);
        String url = serverUrl + "/" + prefix;
        ResponseEntity <T> rEntity=restTemplate.exchange(url, HttpMethod.POST,requestEntity,responseType);
        statusCode=rEntity.getStatusCode().toString();
        return rEntity.getBody();
    }

    //В квчестве запроса отправляет объект и заголовок, получаем ответ- объект. Изменяет поле(statusCode). Возврашает объект.
    public <T> T GetObjAndStatus(Class<T> responseType, String token) throws Exception{
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Authorization",token);
        //MultiValueMap<Object,HttpHeaders> EntityAndHeader= new LinkedMultiValueMap<Object,HttpHeaders>();
        //EntityAndHeader.set(request,requestHeaders);
        HttpEntity requestEntity = new HttpEntity(requestHeaders);
        String url = serverUrl + "/" + prefix;
        ResponseEntity <T> rEntity=restTemplate.exchange(url, HttpMethod.GET,requestEntity,responseType);
        statusCode=rEntity.getStatusCode().toString();
        return rEntity.getBody();
    }


    //Запрашивает некоторый объект. Изменяет статус код. Возвращает объект.
        public <T> T GetObjAndStatus(Class<T> responseType) throws Exception{
            String url = serverUrl + "/" + prefix;
            ResponseEntity<T> rEntity= restTemplate.getForEntity(url,responseType);
            statusCode=rEntity.getStatusCode().toString();
            return rEntity.getBody();
        }
// Определяет каталог.
        public RestRequest in(String prefix){
            this.prefix = prefix;
            return this;
        }

    }