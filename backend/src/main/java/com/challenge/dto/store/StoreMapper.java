package com.challenge.dto.store;

import com.challenge.domain.model.Store;
import org.modelmapper.ModelMapper;

public class StoreMapper {
    public static StoreResponseDto toDto(Store store){
        return new ModelMapper().map(store, StoreResponseDto.class);
    }
}
