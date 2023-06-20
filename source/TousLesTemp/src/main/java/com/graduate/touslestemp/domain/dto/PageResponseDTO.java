package com.graduate.touslestemp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
/*
* @File:  PageResponseDTO.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:17 PM
* @Last update: 20/6/2023
*
* */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PageResponseDTO<T> {
    int page;
    int size;
    String sort;
    Long totalElements;
    List<T> content;
}
