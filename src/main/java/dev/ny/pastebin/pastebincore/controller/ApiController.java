package dev.ny.pastebin.pastebincore.controller;


import dev.ny.pastebin.pastebincore.bean.BinEntity;
import dev.ny.pastebin.pastebincore.bean.BinLiteEntity;
import dev.ny.pastebin.pastebincore.repo.BinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {
    private static final String ZERO = "0";
    private static final String TEN = "10";
    private static final String ID = "id";
    @Autowired
    private BinRepository repo;


    @GetMapping("/list")
    public List<BinLiteEntity> getSearchResults(@RequestParam(value = "q", defaultValue = "") String title,
                                                @RequestParam(value = "pageNum", defaultValue = ZERO) int pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = TEN) int pageSize) {
        log.debug("GET: /list: query={}, pageNum:{}, pageSize:{}", title, pageNum, pageSize);
        return repo.findByTitleIgnoreCaseContaining(title,
                PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, ID)));
    }

    @PostMapping("/list")
    public BinEntity saveBin(@RequestBody BinEntity bin) {
        log.debug("POST: /list: Saving: {}", bin.toString());
        return repo.save(bin);
    }

    @GetMapping("/list/{id}")
    public BinEntity getBinWithId(@PathVariable("id") Long id) {
        log.debug("GET: /list/{}: fetching bin", id);
        return repo.findById(id).orElseGet(() -> {
            BinEntity bin = new BinEntity();
            bin.setTitle("Error");
            String errorDesc = String.format("Bin with id %s not found.", id);
            log.debug(errorDesc);
            bin.setContent(errorDesc);
            return bin;
        });
    }

    @DeleteMapping("/list/{id}")
    public BinLiteEntity deleteBinWithId(@PathVariable("id") Long id, ModelMap map) {
        Optional<BinEntity> b = repo.findById(id);
        b.ifPresent(repo::delete);
        return b.isPresent() ?
                new BinLiteEntity("Deleted.", id, new Date()) :
                new BinLiteEntity("ID not found", id, null);
    }
}
