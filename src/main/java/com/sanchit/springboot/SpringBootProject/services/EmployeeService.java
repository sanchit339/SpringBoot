package com.sanchit.springboot.SpringBootProject.services;

import com.sanchit.springboot.SpringBootProject.dto.EmployeeDTO;
import com.sanchit.springboot.SpringBootProject.entities.EmployeeEntity;
import com.sanchit.springboot.SpringBootProject.exceptions.ResourceNotFoundException;
import com.sanchit.springboot.SpringBootProject.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * @Concepts Used here
 *  - Streams - to work on the object in a stream.
 *  - ModelMapper - To Map bind the Entity to the DTO.
 *  -
 * */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO saveNewEmployee(EmployeeDTO inputEmp) {
        EmployeeEntity tempEmpEnt = modelMapper.map(inputEmp , EmployeeEntity.class); // reverse mapping
        EmployeeEntity employeeEntity = employeeRepository.save(tempEmpEnt); // it can only save the Entity
        return modelMapper.map(employeeEntity , EmployeeDTO.class);
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id){
//        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
//        return modelMapper.map(employeeEntity, EmployeeDTO.class);
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity , EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> allEmployees = employeeRepository.findAll();
        return allEmployees
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity , EmployeeDTO.class))
                .collect(Collectors.toList());
    }
    /**
     * This can be handled in multiple ways
     *  - Create new emp if that emp not present
     *  - Not present return Null
     *  - Just Update and return true | DTO
     * */
    public EmployeeDTO updateEmployeeDetail(EmployeeDTO employeeDTO , Long empId){
        isEmployeeExistById(empId);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO , EmployeeEntity.class);
        employeeEntity.setId(empId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity , EmployeeDTO.class);
    }

    public void isEmployeeExistById(Long empId){
        boolean isExist = employeeRepository.existsById(empId);
        if(!isExist) throw new ResourceNotFoundException("Employee Not found with id : " + empId);
    }

    public boolean deleteEmployee(Long empId){
        isEmployeeExistById(empId);
        employeeRepository.deleteById(empId);
        return true;
    }

    public EmployeeDTO updatePartialEmployee(Long empId, Map<String , Object> updates){
        isEmployeeExistById(empId);
        // get the Employee object
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).orElse(null);
        updates.forEach((field , value) -> {
            Field fieldToUpdate = ReflectionUtils.findRequiredField(EmployeeEntity.class , field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, employeeEntity , value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity) , EmployeeDTO.class);
    }
}
