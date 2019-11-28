package com.jxc.readapis.dto;

import lombok.Value;

@Value
public class Count {
    private final int count;

    public Count(int count) {
        this.count = count;
    }
}
