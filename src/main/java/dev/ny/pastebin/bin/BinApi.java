package dev.ny.pastebin.bin;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bins")
@Slf4j
public class BinApi {
    private static final String ZERO = "0";
    private static final String TEN = "10";
    private static final String ID = "id";
    @Autowired
    private BinRepository repo;


    @GetMapping
    public List<Bin> getSearchResults(@RequestParam(value = "q", defaultValue = "") final String title,
                                          @RequestParam(value = "pageNum", defaultValue = ZERO) final int pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = TEN) final int pageSize) {
        log.debug("GET: /: query={}, pageNum:{}, pageSize:{}", title, pageNum, pageSize);
        return repo.findByTitleIgnoreCaseContaining(title,
                PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, ID)));
    }

    @PostMapping
    public Bin saveBin(@RequestBody final Bin bin) {
        log.debug("POST: /: Saving: {}", bin.toString());
        return repo.save(bin);
    }

    @GetMapping("/{id}")
    public Bin getBinWithId(@PathVariable("id") final Long id) {
        log.debug("GET: /{}: fetching bin", id);
        return repo.findById(id).orElseGet(() -> {
            log.debug("bin with id: {} not found", id);
            return new Bin();
        });
    }

    @DeleteMapping("{id}")
    public void deleteBinWithId(@PathVariable("id") final Long id) {
        log.debug("DELETE: /{}: deleting bin if exists", id);
        repo.deleteById(id);
    }
}
