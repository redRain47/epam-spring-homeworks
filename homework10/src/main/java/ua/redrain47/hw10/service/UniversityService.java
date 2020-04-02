package ua.redrain47.hw10.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.redrain47.hw10.dto.UniversityDto;
import ua.redrain47.hw10.entity.University;
import ua.redrain47.hw10.repository.UniversityRepository;
import ua.redrain47.hw10.util.PersistentEntityUpdater;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UniversityService implements Serviceable<UniversityDto, UUID> {
    private final ModelMapper modelMapper;

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityService(ModelMapper modelMapper, UniversityRepository universityRepository) {
        this.modelMapper = modelMapper;
        this.universityRepository = universityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UniversityDto getById(UUID id) {
        University university = universityRepository.getById(id);

        return (university != null)
                ? modelMapper.map(university, UniversityDto.class)
                : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UniversityDto> getAll() {
        List<University> universityList = universityRepository.getAll();

        return (universityList != null && universityList.size() > 0)
                ? universityList.stream()
                .map(university -> modelMapper.map(university, UniversityDto.class))
                .collect(Collectors.toList())
                : null;
    }

    @Override
    @Transactional
    public void create(UniversityDto universityDto) {
        University university = modelMapper.map(universityDto, University.class);
        UUID universityDtoId = universityDto.getId();

        if (Objects.nonNull(universityDtoId)) {
            university.setId(universityDtoId);
        }

        universityRepository.save(university);
    }

    @Override
    @Transactional
    public void update(UniversityDto universityDto) {
        University persistentUniversity = universityRepository.getById(universityDto.getId());
        University updatedUniversity = modelMapper.map(universityDto, University.class);

        PersistentEntityUpdater.updateUniversity(persistentUniversity, updatedUniversity);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        universityRepository.deleteById(id);
    }
}
