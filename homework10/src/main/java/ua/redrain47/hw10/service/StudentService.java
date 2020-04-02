package ua.redrain47.hw10.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.redrain47.hw10.dto.StudentDto;
import ua.redrain47.hw10.entity.Student;
import ua.redrain47.hw10.repository.StudentRepository;
import ua.redrain47.hw10.util.PersistentEntityUpdater;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService implements Serviceable<StudentDto, UUID> {
    private final ModelMapper modelMapper;

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(ModelMapper modelMapper, StudentRepository studentRepository) {
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDto getById(UUID id) {
        Student student = studentRepository.getById(id);

        return (student != null)
                ? modelMapper.map(student, StudentDto.class)
                : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDto> getAll() {
        List<Student> studentList = studentRepository.getAll();

        return (studentList != null && studentList.size() > 0)
                ? studentList.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList())
                : null;
    }

    @Override
    @Transactional
    public void create(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        UUID studentDtoId = studentDto.getId();

        if (Objects.nonNull(studentDtoId)) {
            student.setId(studentDtoId);
        }

        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void update(StudentDto studentDto) {
        Student persistentStudent = studentRepository.getById(studentDto.getId());
        Student updatedStudent = modelMapper.map(studentDto, Student.class);

        PersistentEntityUpdater.updateStudent(persistentStudent, updatedStudent, true);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        studentRepository.deleteById(id);
    }
}
