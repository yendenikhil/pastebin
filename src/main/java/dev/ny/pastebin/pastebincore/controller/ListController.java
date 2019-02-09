package dev.ny.pastebin.pastebincore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListController {
    @GetMapping("/list")
    public String showPage(ModelMap modelMap) {
        return "list";
    }


}
