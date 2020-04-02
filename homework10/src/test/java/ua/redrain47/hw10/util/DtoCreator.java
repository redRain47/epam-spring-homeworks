package ua.redrain47.hw10.util;

import ua.redrain47.hw10.dto.AddressDto;
import ua.redrain47.hw10.dto.StudentDto;
import ua.redrain47.hw10.dto.UniversityDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DtoCreator {
    public static AddressDto createAddressDto(UUID id) {
        AddressDto addressDto = new AddressDto();

        addressDto.setId(id != null ? id : UUID.randomUUID());
        addressDto.setHouse("38a");
        addressDto.setStreet("Some st.");
        addressDto.setCity("Some city");
        addressDto.setRegion("Some region");
        addressDto.setZipcode("123455");

        return addressDto;
    }

    public static List<AddressDto> createAddressDtoList() {
        List<AddressDto> addressDtoList = new ArrayList<>();
        AddressDto addressDto = new AddressDto();

        addressDto.setId(UUID.fromString("84c570f2-7415-11ea-bc55-0242ac130003"));
        addressDto.setHouse("38a");
        addressDto.setStreet("Another st.");
        addressDto.setCity("Another city");
        addressDto.setRegion("Another region");
        addressDto.setZipcode("983155");

        addressDtoList.add(createAddressDto(
                UUID.fromString("84c56ea4-7415-11ea-bc55-0242ac130003")
        ));
        addressDtoList.add(addressDto);

        return addressDtoList;
    }

    public static UniversityDto createUniversityDto(UUID id) {
        UniversityDto universityDto = new UniversityDto();

        universityDto.setId(id != null ? id : UUID.randomUUID());
        universityDto.setName("Oles Honchar Dnipro National University");

        return universityDto;
    }

    public static List<UniversityDto> createUniversityDtoList() {
        List<UniversityDto> universityDtoList = new ArrayList<>();
        UniversityDto universityDto = new UniversityDto();

        universityDto.setId(UUID.fromString("84c572c8-7415-11ea-bc55-0242ac130003"));
        universityDto.setName("Taras Shevchenko Kyiv National University");

        universityDtoList.add(createUniversityDto(
                UUID.fromString("84c571f6-7415-11ea-bc55-0242ac130003")
        ));
        universityDtoList.add(universityDto);

        return universityDtoList;
    }

    public static StudentDto createStudentDto(UUID id, boolean hasUniversity) {
        StudentDto studentDto = new StudentDto();

        studentDto.setId(id != null ? id : UUID.randomUUID());
        studentDto.setFirstName("Ivan");
        studentDto.setLastName("Ivanov");
        studentDto.setAddressDto(createAddressDto(
                UUID.fromString("84c56ea4-7415-11ea-bc55-0242ac130003")
        ));

        if (hasUniversity) {
            UniversityDto universityDto = createUniversityDto(
                    UUID.fromString("84c571f6-7415-11ea-bc55-0242ac130003")
            );
            studentDto.setUniversityDto(universityDto);
            universityDto.setStudentsDto(Collections.singleton(studentDto));
        }

        return studentDto;
    }

    public static List<StudentDto> createStudentDtoList() {
        List<StudentDto> studentDtoList = new ArrayList<>();
        List<UniversityDto> universityDtoList = createUniversityDtoList();
        List<AddressDto> addressDtoList = createAddressDtoList();
        UniversityDto dnu = universityDtoList.get(0);
        UniversityDto knu = universityDtoList.get(1);


        StudentDto firstStudentDto = createStudentDto(
                UUID.fromString("84c57494-7415-11ea-bc55-0242ac130003"),
                false
        );
        StudentDto secondStudentDto = new StudentDto();

        firstStudentDto.setUniversityDto(dnu);
        dnu.setStudentsDto(Collections.singleton(firstStudentDto));
        firstStudentDto.setAddressDto(addressDtoList.get(0));

        secondStudentDto.setId(UUID.fromString("84c57566-7415-11ea-bc55-0242ac130003"));
        secondStudentDto.setFirstName("Vasya");
        secondStudentDto.setLastName("Pupkin");
        secondStudentDto.setUniversityDto(universityDtoList.get(1));
        knu.setStudentsDto(Collections.singleton(secondStudentDto));
        secondStudentDto.setAddressDto(addressDtoList.get(1));

        studentDtoList.add(firstStudentDto);
        studentDtoList.add(secondStudentDto);

        return studentDtoList;
    }
}
