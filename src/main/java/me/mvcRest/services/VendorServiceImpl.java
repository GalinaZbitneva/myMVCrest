package me.mvcRest.services;

import me.mvcRest.api.v1.mapper.VendorMapper;
import me.mvcRest.api.v1.model.VendorDTO;
import me.mvcRest.api.v1.model.VendorListDTO;
import me.mvcRest.domain.Vendor;
import me.mvcRest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    private  final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorDTO getVendorById(Long id) {
       VendorDTO vendorDTO = vendorRepository.findById(id).map(vendorMapper::vendorToVendorDTO)
               .orElseThrow(ResourceNotFoundException::new);
       vendorDTO.setVendorURL(getVendorURL(id));
       return vendorDTO;
    }

    @Override
    public VendorListDTO getAllVendors() {
       List<VendorDTO> listVendors = vendorRepository.findAll().stream()
               .map(vendor ->{VendorDTO dtoVendor = vendorMapper.vendorToVendorDTO(vendor);
               dtoVendor.setVendorURL(getVendorURL(vendor.getId()));
               return dtoVendor;
               })
               .collect(Collectors.toList());
       VendorListDTO vendors = new VendorListDTO();
       vendors.setVendors(listVendors);
       return vendors;
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {

        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);

        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
       Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
       vendor.setId(id);

       return  saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if(vendorDTO.getName() != null){
                vendor.setName(vendorDTO.getName());
            }
            return saveAndReturnDTO(vendor);
        }).orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private String getVendorURL(Long id){
        return "/api/v1/vendor/"+id;
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor){
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(savedVendor);

        returnDTO.setVendorURL(getVendorURL(savedVendor.getId()));

        return returnDTO;
    }


}
