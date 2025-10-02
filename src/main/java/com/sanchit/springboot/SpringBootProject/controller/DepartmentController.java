package com.sanchit.springboot.SpringBootProject.controller;

import com.sanchit.springboot.SpringBootProject.dto.DepartmentDTO;
import com.sanchit.springboot.SpringBootProject.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{deptId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long deptId){
        return ResponseEntity.ok(departmentService.getDepartmentById(deptId));
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        return ResponseEntity.ok(departmentService.createNewDepartment(departmentDTO));
    }

    @PutMapping
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody @Valid DepartmentDTO departmentDTO , Long deptId){
        return ResponseEntity.ok(departmentService.updateDepartment(departmentDTO , deptId));
    }

    @DeleteMapping("/{deptId}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable Long deptId){
        return ResponseEntity.ok(departmentService.deleteDepartment(deptId));
    }
}
