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

/**
 * @File: StoreController.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:12 AM
 * @Update: 21/6/2023
 */
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

    /**
     * Retrieves all store entities as a list of StoreDTO objects.
     *
     * @return ResponseEntity containing the list of StoreDTO objects and HTTP status OK
     */
    @GetMapping("/get")
    public ResponseEntity<List<StoreDTO>> getAllStoreDTO() {
        return new ResponseEntity<>(storeMapper.toStoreDTOs(storeRepository.findAll()), HttpStatus.OK);
    }

    /**
     * Retrieves a specific store entity by its ID.
     *
     * @param id The ID of the store to retrieve
     * @return ResponseEntity containing the StoreDTO object and HTTP status OK
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<StoreDTO> getStoreDTO(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(storeService.find(id), HttpStatus.OK);
    }

    /**
     * Creates a new store entity.
     *
     * @param store The Store object representing the new store entity
     * @return ResponseEntity containing the created StoreDTO object and HTTP status OK
     * @throws Exception if an error occurs during creation
     */
    @PostMapping("/create")
    public ResponseEntity<StoreDTO> createStoreDTO(@RequestBody @Valid Store store) throws Exception {
        return new ResponseEntity<>(storeService.create(store), HttpStatus.OK);
    }

    /**
     * Updates an existing store entity with the given ID.
     *
     * @param storeDto The updated StoreDTO object
     * @param id       The ID of the store entity to update
     * @return ResponseEntity containing the updated StoreDTO object and HTTP status OK
     * @throws Exception if an error occurs during update
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<StoreDTO> updateStoreDTO(@RequestBody @Valid StoreDTO storeDto, @PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(storeService.update(storeDto, id), HttpStatus.OK);
    }

    /**
     * Deletes a store entity with the given ID.
     *
     * @param id The ID of the store entity to delete
     */
    @DeleteMapping("/delete/{id}")
    public void deleteStoreDTO(@PathVariable("id") Long id) {
        this.storeService.delete(id);
    }

    /**
     * Searches for store entities based on the store name.
     *
     * @param store The store name to search for
     * @return List of StoreDTO objects matching the search criteria
     */
    @GetMapping("/search/{store}")
    List<StoreDTO> searchStoreDTO(@PathVariable("store") String store) {
        return this.storeService.search(store);
    }

    /**
     * Filters store entities based on the address ID.
     *
     * @param addressId The ID of the address to filter by
     * @return List of StoreDTO objects filtered by the address ID
     */
    @GetMapping("/filter/{addressId}")
    List<StoreDTO> filterStoreDTO(@PathVariable("addressId") Long addressId) {
        return this.storeService.filter(addressId);
    }

    /**
     * Retrieves paginated store entities.
     *
     * @param request The Pageable object representing the page request
     * @return PageResponseDTO containing the paginated results
     */
    @GetMapping("/paging")
    public PageResponseDTO<?> pagingStoreDTO(Pageable request) {
        return storeService.getAllStore(request);
    }

    /**
     * Deletes a store entity and its associated products with the given ID.
     *
     * @param id The ID of the store entity to delete
     */
    @DeleteMapping("/delete-storeandproduct/{id}")
    public void deleteStoreAndProduct(@PathVariable("id") Long id) {
        this.storeService.deleteStoreAndProduct(id);
    }

}
