package com.example.session4.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.HttpURLConnection;
import java.net.URL;

public class Rest {
    
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    public static JsonNode get(String path) throws Exception{
        return request("GET", path, null);
    }
    
    public static JsonNode post(String path, Object data) throws Exception{
        return request("POST", path, data);
    }
    
    public static JsonNode put(String path, Object data) throws Exception{
        return request("PUT", path, data);
    }
    
    public static void delete(String path) throws Exception{
        request("DELETE", path, null);
    }
    
    
    
    private static JsonNode request(String method, String path, Object data) throws Exception{
        try {
            
            // 1 REQUEST
            String uri = "https://sof306-java6-default-rtdb.firebaseio.com" + path + ".json";
            URL url = new URL(uri);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Accept", "application.json");
            httpURLConnection.setRequestMethod(method);
            
            // DATA(POST and PUT only)
            if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
                httpURLConnection.setDoOutput(true);
                objectMapper.writeValue(httpURLConnection.getOutputStream(), data);
            }
            
            // 2 RESPONSE
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                return objectMapper.readTree(httpURLConnection.getInputStream());
            }
            
            httpURLConnection.disconnect();
            return null;
            
        } catch (Exception e) {
            throw new Exception();
        }
    }
    
}
