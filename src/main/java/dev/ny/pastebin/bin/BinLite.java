package dev.ny.pastebin.bin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BinLite {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
}
