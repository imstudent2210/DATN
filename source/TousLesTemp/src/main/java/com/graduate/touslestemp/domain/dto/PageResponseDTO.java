package com.graduate.touslestemp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @File: PageResponseDTO.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:13 AM
 * @Update: 21/6/2023
 */
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
