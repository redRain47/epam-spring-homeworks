package ua.redrain47.hw10.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

@Data
@EqualsAndHashCode(exclude = {"universityDto"})
public class StudentDto implements Serializable {
    private UUID id;

    private String firstName;

    private String lastName;

    private AddressDto addressDto;

    private UniversityDto universityDto;
}
