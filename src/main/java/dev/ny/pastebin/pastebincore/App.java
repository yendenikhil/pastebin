package dev.ny.pastebin.pastebincore;

import dev.ny.pastebin.pastebincore.bean.BinEntity;
import dev.ny.pastebin.pastebincore.repo.BinRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

