package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.ClientSdi;

/**
 * @File: ClientService.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:29 AM
 * @Update: 21/6/2023
 */
public interface ClientService {

    /**
     * Creates a client based on the provided ClientSdi object.
     *
     * @param sdi The ClientSdi object containing the client information.
     * @return {@code true} if the client is successfully created, {@code false} otherwise.
     */
    Boolean create(ClientSdi sdi);
}
