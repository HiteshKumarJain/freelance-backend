package com.myapp.freelance_backend;

import com.myapp.freelance_backend.dto.request.RoleRequestDTO;
import com.myapp.freelance_backend.dto.response.RoleResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {
    private final RoleRepository roleRepository;


    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping("/roles")
    public ResponseEntity<ApiResponse> addRole(@Valid @RequestBody RoleRequestDTO requestDTO) {
        String roleName = requestDTO.getRoleName();
        if(roleRepository.existsByRoleName(RoleName.valueOf(roleName))){
            return ResponseUtil.error(409,"Role name already exists");
        }
        Role entityRole = new Role();
        entityRole.setRoleName(RoleName.valueOf(roleName));
        Role savedRole = roleRepository.save(entityRole);
        RoleResponseDTO responseDTO = convertToDTO(savedRole);
        return ResponseUtil.success(201,"Role created successfully",responseDTO);
    }

    @GetMapping("/roles")
    public ResponseEntity<ApiResponse> fetchAllRoles(){
        List<Role> allRoles = roleRepository.findAll();
        List<RoleResponseDTO> dtoList = convertToDTOList(allRoles);
        return ResponseUtil.success(200,"All roles",dtoList);

    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<ApiResponse> fetchRoleById(@PathVariable Long roleId){
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if(roleOptional.isEmpty()) return ResponseUtil.error(404,"Role not found");
        Role role = roleOptional.get();
        RoleResponseDTO responseDTO = convertToDTO(role);
        return ResponseUtil.success(200,"Role found",responseDTO);
    }

    public static RoleResponseDTO convertToDTO(Role role){
        return new RoleResponseDTO(role);
    }
    public static List<RoleResponseDTO> convertToDTOList(List<Role> roleList) {
        List<RoleResponseDTO> dtoList = new ArrayList<>();
        for(Role role : roleList) {
            dtoList.add(new RoleResponseDTO(role));
        }
        return dtoList;
    }
}
