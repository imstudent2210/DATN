package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StoreDTO;
import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @File: StoreService.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:34 AM
 * @Update: 21/6/2023
 */
public interface StoreService {
    /**
     * Retrieves all the stores.
     *
     * @return A list of Store objects representing all the stores.
     */
    List<Store> findAll();

    /**
     * Retrieves all the stores using pagination.
     *
     * @param request Pageable object specifying the page and size for pagination.
     * @return A PageResponseDTO<?> object containing the paginated results.
     */
    PageResponseDTO<?> getAllStore(Pageable request);

    /**
     * Retrieves a specific store by its ID.
     *
     * @param id Long value representing the ID of the store.
     * @return A StoreDTO object containing the store details.
     */
    StoreDTO find(Long id);

    /**
     * Creates a new store.
     *
     * @param store Store object representing the store to be created.
     * @return A StoreDTO object containing the created store details.
     * @throws Exception if an error occurs during the creation process.
     */
    StoreDTO create(Store store) throws Exception;

    /**
     * Deletes a store by its ID.
     *
     * @param id Long value representing the ID of the store to be deleted.
     */
    void delete(Long id);

    /**
     * Updates an existing store.
     *
     * @param storeDto StoreDTO object representing the updated store details.
     * @param id       Long value representing the ID of the store to be updated.
     * @return A StoreDTO object containing the updated store details.
     * @throws Exception if an error occurs during the update process.
     */
    StoreDTO update(StoreDTO storeDto, Long id) throws Exception;

    /**
     * Searches for stores based on the provided keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of StoreDTO objects representing the matched stores.
     */
    List<StoreDTO> search(String keyword);

    /**
     * Filters stores based on the provided ID.
     *
     * @param id Long value representing the ID for filtering stores.
     * @return A list of StoreDTO objects representing the filtered stores.
     */
    List<StoreDTO> filter(Long id);

    /**
     * Deletes a store and its associated products by its ID.
     *
     * @param id Long value representing the ID of the store to be deleted.
     */
    void deleteStoreAndProduct(Long id);

}
