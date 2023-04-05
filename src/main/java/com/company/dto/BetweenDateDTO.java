package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BetweenDateDTO {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
