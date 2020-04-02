package ua.redrain47.hw10.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class AddressDto implements Serializable {
    private UUID id;

    private String house;

    private String street;

    private String city;

    private String region;

    private String zipcode;
}
