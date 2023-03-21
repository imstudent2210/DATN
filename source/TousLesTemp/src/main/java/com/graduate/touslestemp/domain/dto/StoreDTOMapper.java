package com.graduate.touslestemp.domain.dto;

import com.graduate.touslestemp.domain.dto.StoreDTO;
import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StoreDTOMapper implements Function<Store, StoreDTO> {
    @Override
    public StoreDTO apply(Store store) {
        return new StoreDTO(
                store.getName(),
                store.getEmail(),
                store.getPhone(),
                store.getAddress()
        );
    }
}
