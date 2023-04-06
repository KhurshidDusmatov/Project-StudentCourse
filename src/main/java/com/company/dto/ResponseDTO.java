package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class ResponseDTO {
    private Integer scmId;
    private Integer sId;
    private String sName;
    private String sSurname;
    private Integer cId;
    private String cName;
    private Integer mark;
    private LocalDate createdDate;

}
