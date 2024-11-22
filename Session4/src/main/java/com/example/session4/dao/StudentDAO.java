package com.example.session4.dao;

import com.example.session4.bean.Student;
import com.example.session4.bean.StudentMap;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.session4.rest.Rest;

public class StudentDAO {

    ObjectMapper objectMapper = new ObjectMapper();

    public StudentMap findAll() throws Exception {
        JsonNode resp = Rest.get("/students");
        return objectMapper.convertValue(resp, StudentMap.class);
    }

    public Student findByKey(String key) throws Exception {
        JsonNode resp = Rest.get("/students/" + key);
        return objectMapper.convertValue(resp, Student.class);
    }

    public String create(Student data) throws Exception {
        JsonNode resp = Rest.post("/students", data);
        return resp.get("name").asText();
    }

    public Student upate(String key, Student data) throws Exception {
        JsonNode resp = Rest.put("/students/" + key, data);
        return objectMapper.convertValue(resp, Student.class);
    }

    public void delete(String key) throws Exception {
        Rest.delete("/students/" + key); // Đảm bảo đường dẫn chính xác
    }

}
