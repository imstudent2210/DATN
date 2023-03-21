package com.graduate.touslestemp.controller;

//import com.graduate.touslestemp.domain.dto.StoreDTO;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.entity.Store;
import com.graduate.touslestemp.domain.mapper.StoreDto;
import com.graduate.touslestemp.domain.mapper.StoreMapper;
import com.graduate.touslestemp.domain.repository.ProductRepository;
import com.graduate.touslestemp.domain.repository.StoreRepository;
import com.graduate.touslestemp.service.AddressService;
import com.graduate.touslestemp.service.StoreService;
import jakarta.transaction.Transactional;
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
    private AddressService addressService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoreMapper storeMapper;

    /*===========================DTO v2=========================*/

    @GetMapping("/get/dto1")
    public ResponseEntity<List<StoreDto>> allStoreDTO() {
        return new ResponseEntity<>(storeMapper.toStoreDTOs(storeRepository.findAll()), HttpStatus.OK);
    }

    /*==========================end DTO==============================*/
    @GetMapping("/get")
    List<Store> allStore() {
        return this.storeRepository.findAll();
    }
    @Transactional
    @PostMapping("/create")
    public Store createStore(@RequestBody @Valid Store store) throws Exception {
       return this.storeService.save(store);
    }

    @GetMapping("/get/{store}")
    Store getStoreByName(@PathVariable("store") String store) throws Exception {
        return this.storeService.findStore(store);
    }

    @GetMapping("/get-page")
    public PageResponseDTO<?> getAllCourse(Pageable request){    return storeService.getAllStore(request);}
    @PatchMapping("/update/{storename}")
    public Store updateStore(@RequestBody @Valid Store store, @PathVariable("storename") String storename) throws Exception {

        return this.storeService.update(store, storename);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteStore(@PathVariable("id") Long id) throws Exception{
        this.storeService.deleteStore(id);
    }

    //
    @GetMapping("/product")
    List<Product> allProduct() {
        return this.productRepository.findAll();
    }


}
