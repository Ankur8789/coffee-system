package com.lld.models;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bill {
    private String id;
    private Coffee coffee;
    private LocalDateTime localDateTime;
    private Status status;

}
