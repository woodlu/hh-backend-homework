package ru.hh.school.dto;

import lombok.Data;

@Data
public class SalaryDto {
    private long to;
    private long from;
    private String currency;
    private boolean gross;
}
