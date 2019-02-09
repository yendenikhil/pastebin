package dev.ny.pastebin.pastebincore.controller;

import dev.ny.pastebin.pastebincore.bean.BinEntity;
import dev.ny.pastebin.pastebincore.repo.BinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Controller
public class HomeController {
    private static final String BIN = "bin";
    private static final String SAVED_BIN = "savedBin";
    @Autowired
    private BinRepository repo;

    @GetMapping({"/", "home"})
    public String homepage(ModelMap modelMap) {
        BinEntity binEntity = (BinEntity) modelMap.get(SAVED_BIN);
        if (binEntity == null) {
            binEntity = new BinEntity();
        }
        modelMap.addAttribute(BIN, binEntity);
        return "home";
    }

    @PostMapping({"/", "home"})
    public String makeBin(@ModelAttribute BinEntity bin, RedirectAttributesModelMap modelMap) {
        BinEntity savedBin = repo.save(bin);
        modelMap.addFlashAttribute(SAVED_BIN, savedBin);
        return "redirect:/" + savedBin.getId();
    }

    @GetMapping("/{id}")
    public String showSavedBin(@PathVariable("id") Long id, ModelMap modelMap) {
        BinEntity bin = (BinEntity) modelMap.get(SAVED_BIN);
        if (bin == null) {
            bin = repo.findById(id).orElse(new BinEntity());
        }
        modelMap.addAttribute(BIN, bin);
        return "home";
    }
}
