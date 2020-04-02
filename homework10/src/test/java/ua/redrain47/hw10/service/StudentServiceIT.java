package ua.redrain47.hw10.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.redrain47.hw10.config.HibernateConfig;
import ua.redrain47.hw10.dto.StudentDto;
import ua.redrain47.hw10.util.DtoCreator;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class StudentServiceIT {

    @Autowired
    private Serviceable<StudentDto, UUID> studentService;

    @Test
    @Transactional
    public void shouldCreate() {
        StudentDto studentDto = DtoCreator.createStudentDto(null, true);
        studentService.create(studentDto);
        StudentDto createdStudent = studentService.getById(studentDto.getId());

        assertEquals(studentDto, createdStudent);
    }

    @Test
    public void shouldGetById() {
        UUID studentDtoId = UUID.fromString("84c57494-7415-11ea-bc55-0242ac130003");
        StudentDto studentDto = DtoCreator.createStudentDto(studentDtoId, true);

        assertEquals(studentDto, studentService.getById(studentDtoId));
    }

    @Test
    public void shouldGetAll() {
        List<StudentDto> studentDtoList = DtoCreator.createStudentDtoList();

        assertEquals(studentDtoList, studentService.getAll());
    }

    @Test
    @Transactional
    public void shouldUpdate() {
        UUID studentDtoId = UUID.fromString("84c57494-7415-11ea-bc55-0242ac130003");
        StudentDto studentDto = DtoCreator.createStudentDto(studentDtoId, true);

        studentDto.setFirstName("John");
        studentDto.setLastName("Cena");
        studentService.update(studentDto);

        assertEquals(studentDto, studentService.getById(studentDtoId));
    }

    @Test
    @Transactional
    public void shouldDelete() {
        UUID deletedId = UUID.fromString("84c57494-7415-11ea-bc55-0242ac130003");

        studentService.deleteById(deletedId);
        assertNull(studentService.getById(deletedId));
    }
}