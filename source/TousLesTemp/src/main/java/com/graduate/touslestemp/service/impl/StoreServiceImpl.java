package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StoreDto;
import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.entity.Store;
import com.graduate.touslestemp.domain.mapper.StoreMapper;
import com.graduate.touslestemp.domain.repository.AddressRepository;
import com.graduate.touslestemp.domain.repository.StoreRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    /*================================ DTO v2==========================*/
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreMapper storeMapper;
    final ModelMapper modelMapper = new ModelMapper();

    @Override
    public StoreDto create(Store store) throws Exception {
        if (isExisStore(store.getName())) {
            System.out.println("This store has already");
            throw new RequestException("This store has already!");
        } else {
            return (storeMapper.toStoreDTO(storeRepository.save(store)));
        }
    }
    @Override
    public StoreDto find(Long id) {
        Optional<Store> store = storeRepository.findById(id);
        if (store.isEmpty()) {
            throw new RequestException("Not found store, id: " + id);
        }
        return (storeMapper.toStoreDTO(storeRepository.findById(id).get()));
    }

    @Override
    public StoreDto update(StoreDto storeDto, Long id) throws Exception {
        Store local = this.storeRepository.findById(id)
                .orElseThrow(() -> new RequestException("Can't found this store id: " + id));
        storeMapper.updateEntity(storeDto, local);
        return (storeMapper.toStoreDTO(storeRepository.save(local)));
    }

    @Override
    public void delete(Long id) {
        storeRepository.delete(storeRepository.findById(id)
                .orElseThrow(() -> new RequestException("Can't found this store id: " + id)));
    }

    @Override
    public List<StoreDto> search(String name) {
        List<Store> stores = this.storeRepository.searchStoreByName("%" + name + "%");
        if (stores.isEmpty()) {
            throw new RequestException("No data!");
        } else {
            List<StoreDto> storeDtos = this.storeMapper.toStoreDTOs(stores);
            return storeDtos;
        }
    }

    @Override
    public List<StoreDto> filter(Long id) {
        List<Store> stores = this.storeRepository.filterStoreByAddressId(id);
        if (stores.isEmpty()) {
            throw new RequestException("No data!");
        } else {
            List<StoreDto> storeDtos = this.storeMapper.toStoreDTOs(stores);
            return storeDtos;
        }
    }

    @Override
    public List<Store> findAll() {
        return this.storeRepository.findAll();
    }

    @Override
    public PageResponseDTO<?> getAllStore(Pageable pageable) {
        return modelMapper.map(storeRepository.findAll(pageable), PageResponseDTO.class);
    }


    public boolean isExisStore(String name) {
        Store checkStore = storeRepository.findStoreByName(name);
        if (checkStore != null) {
            return true;
        }
        return false;
    }
}
