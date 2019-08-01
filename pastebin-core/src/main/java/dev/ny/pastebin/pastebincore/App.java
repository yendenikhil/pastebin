package dev.ny.pastebin.pastebincore;

import dev.ny.pastebin.pastebincore.bin.Bin;
import dev.ny.pastebin.pastebincore.bin.BinRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner testData(final BinRepository repo) {
        return (args) -> {
            IntStream.range(0, 15)
                    .forEach(i -> repo.save(Bin.builder()
                            .title("test title " + i)
                            .content("test content " + i)
                            .build()));
        };
    }
}

