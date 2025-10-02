package com.sanchit.springboot.SpringBootProject.services;

import com.sanchit.springboot.SpringBootProject.dto.DepartmentDTO;
import com.sanchit.springboot.SpringBootProject.entities.DepartmentEntity;
import com.sanchit.springboot.SpringBootProject.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }


    public List<DepartmentDTO> getAllDepartments(){
        List<DepartmentEntity> allDepartments= departmentRepository.findAll();
        return allDepartments
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity , DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(Long deptId){
        DepartmentEntity departmentEntity = departmentRepository.findById(deptId).orElse(null);
        return modelMapper.map(departmentEntity, DepartmentDTO.class);
    }


    public DepartmentDTO createNewDepartment(DepartmentDTO departmentDTO){
        DepartmentEntity toCreate = modelMapper.map(departmentDTO, DepartmentEntity.class);
        DepartmentEntity departmentEntity = departmentRepository.save(toCreate);
        return  modelMapper.map(departmentEntity , DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO , Long deptId){
        DepartmentEntity toUpdate = modelMapper.map(departmentDTO, DepartmentEntity.class);

        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO , DepartmentEntity.class);
        departmentEntity.setId(deptId);

        DepartmentEntity updatedDepartmentEntity = departmentRepository.save(departmentEntity);

        return modelMapper.map(departmentEntity, DepartmentDTO.class);
    }

    public Boolean deleteDepartment(Long deptId){
        if(!isExist(deptId)) return false;
        departmentRepository.deleteById(deptId);
        return true;
    }


    public Boolean isExist(Long deptId){
        return departmentRepository.existsById(deptId);
    }
}
