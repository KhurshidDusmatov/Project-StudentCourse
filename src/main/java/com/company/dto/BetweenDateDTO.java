package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BetweenDateDTO {
    private LocalDate fromDate;
    private LocalDate toDate;
}
