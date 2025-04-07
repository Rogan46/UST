package com.example.demo.controller;

import com.example.demo.Entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class controller {
    @GetMapping("/")
    public String Home(HttpServletRequest request ){
        return "welcome to home"+" "+ request.getSession().getId();
    }
    private List<Student> students=new ArrayList<>(List.of(
            new Student(1,"rogan",80),
            new Student(2,"udhay",80)


            ));
    @GetMapping("/students")
    public List<Student> getstudents(){
return students;
    }
    @GetMapping("/csrf")
    public CsrfToken getcsrftoken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");

    }
    @PostMapping("/students")
    public Student add(@RequestBody Student student){
        students.add(student);
        return student;
    }

}
