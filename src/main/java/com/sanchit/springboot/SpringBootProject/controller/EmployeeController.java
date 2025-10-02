package com.sanchit.springboot.SpringBootProject.controller;

import com.sanchit.springboot.SpringBootProject.dto.EmployeeDTO;
import com.sanchit.springboot.SpringBootProject.exceptions.ResourceNotFoundException;
import com.sanchit.springboot.SpringBootProject.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// (controller | response body)
@RestController  // its used here because it has the request response body in it
@RequestMapping(path = "/employees") // parent routing from here
public class EmployeeController {
//    @GetMapping(path = "/getMsg")
//    public String getMySecretMsg(){
//        return ("This is Secret MSG : SA Won WTC");
//    }

    private final EmployeeService employeeService;

    //constructor method to invoke this
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}") // provide the path to get the path variable
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){ // it will come from the Path here
        // you can give the name which will ge used as different name from here.
        // return new EmployeeDTO(id , "san@email" , "san" , 23 , LocalDate.of(2025 , 06 , 15) , true);
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with id : " + id));
//                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/employees")
    public String getEmployees(@RequestParam(required = false) Integer age ,
                               @RequestParam(required = true) String sortBy){
        return "hi age" + age + " " + sortBy;
    }
//    @PostMapping
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmp){ // setting the input emp
//        inputEmp.setId(100L);
//        return inputEmp;
//    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployeeNew(@RequestBody @Valid EmployeeDTO inputEmp){ // setting the input emp
        EmployeeDTO savedEmployee = employeeService.saveNewEmployee(inputEmp);
        return new ResponseEntity<>(savedEmployee , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
//        return employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    /**
     * Update the Employee ID for the Given Employee.
     * */

//    public boolean EmployeeNotFound(Long employeeId){
//        boolean isExists = employeeService.isEmployeeExistById(employeeId);
//        if(!isExists) return new ResourceNotFoundException("Employee not found with id : " + employeeId);
//        return isExists;
//    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeDetail(@RequestBody @Valid EmployeeDTO employeeDTO , @PathVariable Long employeeId){

        return ResponseEntity.ok(employeeService.updateEmployeeDetail(employeeDTO , employeeId));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long employeeId){
        boolean gotDeleted = employeeService.deleteEmployee(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployee(@RequestBody Map<String , Object> toUpdate , @PathVariable Long employeeId){
        EmployeeDTO employeeUpdated = employeeService.updatePartialEmployee(employeeId , toUpdate);
        if(employeeUpdated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeUpdated);
    }
}
