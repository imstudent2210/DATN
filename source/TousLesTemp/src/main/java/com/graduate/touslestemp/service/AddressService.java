package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.entity.Category;

import java.util.List;

/**
 * @File: AddressService.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:29 AM
 * @Update: 21/6/2023
 */
public interface AddressService {
    /**
     * Retrieves all addresses.
     *
     * @return A list of all addresses.
     */
    List<Address> findAll();

    /**
     * Saves an address.
     *
     * @param address The address to be saved.
     * @return The saved address.
     * @throws Exception If an error occurs during the save process.
     */
    Address save(Address address) throws Exception;

    /**
     * Finds an address by its name.
     *
     * @param name The name of the address to find.
     * @return The address matching the provided name.
     */
    Address findAddress(String name);

    /**
     * Updates an address identified by its name.
     *
     * @param address The updated address.
     * @param name    The name of the address to update.
     * @return The updated address.
     * @throws Exception If an error occurs during the update process.
     */
    Address update(Address address, String name) throws Exception;

    /**
     * Deletes an address by its ID.
     *
     * @param id The ID of the address to delete.
     */
    void deleteAddress(Long id);

    /**
     * Updates an address identified by its ID.
     *
     * @param address The updated address.
     * @param id      The ID of the address to update.
     * @return The updated address.
     * @throws Exception If an error occurs during the update process.
     */
    Address update(Address address, Long id) throws Exception;
}
