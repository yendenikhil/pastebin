package dev.ny.pastebin.pastebincore.bean;

import lombok.Value;

import java.util.Date;

@Value
public class BinLiteEntity {
    String title;
    Long id;
    Date createdAt;

}
