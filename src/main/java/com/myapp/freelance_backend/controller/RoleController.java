package com.myapp.freelance_backend.controller;

import com.myapp.freelance_backend.service.RoleService;
import com.myapp.freelance_backend.util.ApiResponse;
import com.myapp.freelance_backend.util.ResponseUtil;
import com.myapp.freelance_backend.dto.request.RoleRequestDTO;
import com.myapp.freelance_backend.dto.response.RoleResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping("/roles")
    public ResponseEntity<ApiResponse> addRole(@Valid @RequestBody RoleRequestDTO requestDTO) {
        RoleResponseDTO responseDTO = roleService.addRole(requestDTO);
        return ResponseUtil.success(201,"Role created successfully",responseDTO);
    }

    @GetMapping("/roles")
    public ResponseEntity<ApiResponse> fetchAllRoles(){
        List<RoleResponseDTO> dtoList = roleService.fetchAllRoles();
        return ResponseUtil.success(200,"All roles",dtoList);

    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<ApiResponse> fetchRoleById(@PathVariable Long roleId){
        RoleResponseDTO responseDTO = roleService.fetchRoleById(roleId);
        return ResponseUtil.success(200,"Role found",responseDTO);
    }


}
