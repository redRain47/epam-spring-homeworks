package ua.redrain47.hw10.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.redrain47.hw10.dto.AddressDto;
import ua.redrain47.hw10.entity.Address;
import ua.redrain47.hw10.repository.AddressRepository;
import ua.redrain47.hw10.util.PersistentEntityUpdater;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressService implements Serviceable<AddressDto, UUID> {

    private final ModelMapper modelMapper;

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(ModelMapper modelMapper, AddressRepository addressRepository) {
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public AddressDto getById(UUID id) {
        Address address = addressRepository.getById(id);

        return (address != null)
                ? modelMapper.map(address, AddressDto.class)
                : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressDto> getAll() {
        List<Address> addressList = addressRepository.getAll();

        return (addressList != null && addressList.size() > 0)
                ? addressList.stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .collect(Collectors.toList())
                : null;
    }

    @Override
    @Transactional
    public void create(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        UUID addressDtoId = addressDto.getId();

        if (Objects.nonNull(addressDtoId)) {
            address.setId(addressDtoId);
        }

        addressRepository.save(address);
    }

    @Override
    @Transactional
    public void update(AddressDto addressDto) {
        Address persistentAddress = addressRepository.getById(addressDto.getId());
        Address updatedAddress = modelMapper.map(addressDto, Address.class);

        PersistentEntityUpdater.updateAddress(persistentAddress, updatedAddress);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        addressRepository.deleteById(id);
    }
}
