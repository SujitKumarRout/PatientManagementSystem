package com.sujit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("footer")
public class FooterController
{

    @GetMapping("/api")
    public String getAPI()
    {
        return "API/api";
    }
}