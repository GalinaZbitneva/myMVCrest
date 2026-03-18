package me.mvcRest.api.v1.mapper;

import me.mvcRest.api.v1.model.VendorDTO;
import me.mvcRest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

   VendorDTO vendorToVendorDTO (Vendor vendor);

   Vendor vendorDTOtoVendor (VendorDTO vendorDTO);
}
