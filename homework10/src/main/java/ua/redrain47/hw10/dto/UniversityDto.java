package ua.redrain47.hw10.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(exclude = {"studentsDto"})
public class UniversityDto implements Serializable {
    private UUID id;

    private String name;

    private Set<StudentDto> studentsDto;
}
