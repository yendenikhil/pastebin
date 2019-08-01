package dev.ny.pastebin.pastebincore.bin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BinLite {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
}
