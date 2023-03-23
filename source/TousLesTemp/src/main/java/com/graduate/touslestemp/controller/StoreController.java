package com.graduate.touslestemp.controller;
import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StoreDto;
import com.graduate.touslestemp.domain.entity.Store;
import com.graduate.touslestemp.domain.mapper.StoreMapper;
import com.graduate.touslestemp.domain.repository.ProductRepository;
import com.graduate.touslestemp.domain.repository.StoreRepository;
import com.graduate.touslestemp.service.StoreService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreService storeService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoreMapper storeMapper;

    /*===========================DTO v2=========================*/

    @GetMapping("/get-all")
    public ResponseEntity<List<StoreDto>> getAllStoreDTO() {
        return new ResponseEntity<>(storeMapper.toStoreDTOs(storeRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StoreDto> getStoreDTO(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(storeService.find(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<StoreDto> createStoreDTO(@RequestBody @Valid Store store) throws Exception {
        return new ResponseEntity<>(storeService.create(store), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StoreDto> updateStoreDTO(@RequestBody @Valid StoreDto storeDto, @PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(storeService.update(storeDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStoreDTO(@PathVariable("id") Long id) throws Exception {
        this.storeService.delete(id);
    }

    @GetMapping("/search/{store}")
    List<StoreDto> searchStoreDTO(@PathVariable("store") String store) throws Exception {
        return this.storeService.search(store);
    }
    @GetMapping("/filter/{addressId}")
    List<StoreDto> filterStoreDTO(@PathVariable("addressId") Long addressId) throws Exception {
        return this.storeService.filter(addressId);
    }

    @GetMapping("/paging")
    public PageResponseDTO<?> pagingStoreDTO(Pageable request) {
        return storeService.getAllStore(request);
    }
    /*==========================end DTO==============================*/
    @GetMapping("/get1")
    List<Store> allStore() {
        return this.storeRepository.findAll();
    }






}
