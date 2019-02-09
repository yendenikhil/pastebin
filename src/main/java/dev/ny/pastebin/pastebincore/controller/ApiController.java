package dev.ny.pastebin.pastebincore.controller;


import dev.ny.pastebin.pastebincore.bean.BinLiteEntity;
import dev.ny.pastebin.pastebincore.repo.BinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    BinRepository repo;


    @GetMapping("/list")
    public List<BinLiteEntity> getSearchResults(@RequestParam("q") String title) {
        System.out.println("inside getSearchResults");
        System.out.println(title);
        return repo.findByTitleIgnoreCaseContaining(title);
    }
}
