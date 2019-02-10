package dev.ny.pastebin.pastebincore.controller;


import dev.ny.pastebin.pastebincore.bean.BinLiteEntity;
import dev.ny.pastebin.pastebincore.repo.BinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final String ZERO = "0";
    private static final String TEN = "10";
    private static final String ID = "id";
    @Autowired
    private BinRepository repo;


    @GetMapping("/list")
    public List<BinLiteEntity> getSearchResults(@RequestParam("q") String title,
                                                @RequestParam(value = "pageNum", defaultValue = ZERO) int pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = TEN) int pageSize) {
        return repo.findByTitleIgnoreCaseContaining(title,
                PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, ID)));
    }
}
