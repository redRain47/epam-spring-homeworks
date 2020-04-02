package ua.redrain47.hw10.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.redrain47.hw10.config.HibernateConfig;
import ua.redrain47.hw10.dto.UniversityDto;
import ua.redrain47.hw10.util.DtoCreator;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class UniversityServiceIT {
    @Autowired
    private Serviceable<UniversityDto, UUID> universityService;

    @Test
    @Transactional
    public void shouldCreate() {
        UniversityDto universityDto = DtoCreator.createUniversityDto(null);
        universityService.create(universityDto);
        UniversityDto createdUniversity = universityService.getById(universityDto.getId());

        assertEquals(universityDto, createdUniversity);
    }

    @Test
    public void shouldGetById() {
        UUID universityDtoId = UUID.fromString("84c571f6-7415-11ea-bc55-0242ac130003");
        UniversityDto universityDto = DtoCreator.createUniversityDto(universityDtoId);

        assertEquals(universityDto, universityService.getById(universityDtoId));
    }

    @Test
    public void shouldGetAll() {
        List<UniversityDto> universityDtoList = DtoCreator.createUniversityDtoList();

        assertEquals(universityDtoList, universityService.getAll());
    }

    @Test
    @Transactional
    public void shouldUpdate() {
        UUID universityDtoId = UUID.fromString("84c571f6-7415-11ea-bc55-0242ac130003");
        UniversityDto universityDto = DtoCreator.createUniversityDto(universityDtoId);

        universityDto.setName("Updated university");
        universityService.update(universityDto);

        assertEquals(universityDto, universityService.getById(universityDtoId));
    }

    @Test
    @Transactional
    public void shouldDelete() {
        UUID deletedId = UUID.fromString("84c571f6-7415-11ea-bc55-0242ac130003");

        universityService.deleteById(deletedId);
        assertNull(universityService.getById(deletedId));
    }
}