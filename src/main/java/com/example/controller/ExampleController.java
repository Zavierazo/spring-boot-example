package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.ExampleService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class ExampleController {
    @Autowired
    private ExampleService exampleService;

    @RequestMapping({
            "", "index", "home"
    })
    public String clearAll(Model model) throws Exception {
        model.addAttribute("title", "Home");
        model.addAttribute("imgPath", "image.jpg");
        return "index";// Return index html with title Home
    }

    @RequestMapping(method = RequestMethod.GET, value = "/testMethodCache", produces = {
            MediaType.TEXT_PLAIN_VALUE
    })
    @ResponseBody
    public String clearAll(@RequestParam String key1, @RequestParam(required = false) String key2) throws Exception {
        return exampleService.randomStringWithCache(key1, key2);
    }

}
