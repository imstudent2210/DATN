package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StoreDTO;
import com.graduate.touslestemp.domain.entity.Store;
import com.graduate.touslestemp.domain.mapper.StoreMapper;
import com.graduate.touslestemp.domain.repository.StoreRepository;
import com.graduate.touslestemp.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreService storeService;
    @Autowired
    private StoreMapper storeMapper;

    @GetMapping("/get")
    public ResponseEntity<List<StoreDTO>> getAllStoreDTO() {
        return new ResponseEntity<>(storeMapper.toStoreDTOs(storeRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StoreDTO> getStoreDTO(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(storeService.find(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<StoreDTO> createStoreDTO(@RequestBody @Valid Store store) throws Exception {
        return new ResponseEntity<>(storeService.create(store), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StoreDTO> updateStoreDTO(@RequestBody @Valid StoreDTO storeDto, @PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(storeService.update(storeDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStoreDTO(@PathVariable("id") Long id) {
        this.storeService.delete(id);
    }

    @GetMapping("/search/{store}")
    List<StoreDTO> searchStoreDTO(@PathVariable("store") String store) {
        return this.storeService.search(store);
    }

    @GetMapping("/filter/{addressId}")
    List<StoreDTO> filterStoreDTO(@PathVariable("addressId") Long addressId) {
        return this.storeService.filter(addressId);
    }

    @GetMapping("/paging")
    public PageResponseDTO<?> pagingStoreDTO(Pageable request) {
        return storeService.getAllStore(request);
    }

    @DeleteMapping("/delete-storeandproduct/{id}")
    public void deleteStoreAndProduct(@PathVariable("id") Long id) {
        this.storeService.deleteStoreAndProduct(id);
    }

}
