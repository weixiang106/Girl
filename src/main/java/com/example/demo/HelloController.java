package com.example.demo;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "设置")
@RequestMapping(value = "/v1")
public class HelloController {

    @RequestMapping(value = "/hello")
    public String goList(){
        return "fhhfhhhh";
    }
    @RequestMapping(value = "students")
    public String getStudent(){
        Student student = new Student();
        student.setId(1);
        student.setAge(18);
        student.setName("张三疯");
        student.setUserName("oceans");
        return  student.toString();

    }
}
