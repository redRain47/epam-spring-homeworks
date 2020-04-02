package ua.redrain47.hw10.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.redrain47.hw10.config.HibernateConfig;
import ua.redrain47.hw10.dto.AddressDto;
import ua.redrain47.hw10.util.DtoCreator;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class AddressServiceIT {

    @Autowired
    private Serviceable<AddressDto, UUID> addressService;

    @Test
    @Transactional
    public void shouldCreate() {
        AddressDto addressDto = DtoCreator.createAddressDto(null);
        addressService.create(addressDto);
        AddressDto createdAddress = addressService.getById(addressDto.getId());

        assertEquals(addressDto, createdAddress);
    }

    @Test
    public void shouldGetById() {
        UUID addressDtoId = UUID.fromString("84c56ea4-7415-11ea-bc55-0242ac130003");
        AddressDto addressDto = DtoCreator.createAddressDto(addressDtoId);

        assertEquals(addressDto, addressService.getById(addressDtoId));
    }

    @Test
    public void shouldGetAll() {
        List<AddressDto> addressDtoList = DtoCreator.createAddressDtoList();

        assertEquals(addressDtoList, addressService.getAll());
    }

    @Test
    @Transactional
    public void shouldUpdate() {
        UUID addressDtoId = UUID.fromString("84c56ea4-7415-11ea-bc55-0242ac130003");
        AddressDto addressDto = DtoCreator.createAddressDto(addressDtoId);

        addressDto.setCity("Updated city");
        addressService.update(addressDto);

        assertEquals(addressDto, addressService.getById(addressDtoId));
    }

    @Test
    @Transactional
    public void shouldDelete() {
        UUID deletedId = UUID.fromString("84c56ea4-7415-11ea-bc55-0242ac130003");

        addressService.deleteById(deletedId);
        assertNull(addressService.getById(deletedId));
    }
}
