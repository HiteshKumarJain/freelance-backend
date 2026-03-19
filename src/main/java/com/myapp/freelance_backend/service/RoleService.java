package com.myapp.freelance_backend.service;

import com.myapp.freelance_backend.dto.request.RoleRequestDTO;
import com.myapp.freelance_backend.dto.response.RoleResponseDTO;
import com.myapp.freelance_backend.entity.Role;
import com.myapp.freelance_backend.entity.RoleName;
import com.myapp.freelance_backend.exception.ResourceAlreadyExistsException;
import com.myapp.freelance_backend.exception.ResourceNotFoundException;
import com.myapp.freelance_backend.repository.RoleRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public RoleResponseDTO addRole( RoleRequestDTO requestDTO) {
        String roleName = requestDTO.getRoleName();
        if(roleRepository.existsByRoleName(RoleName.valueOf(roleName))) throw new ResourceAlreadyExistsException("Role name already exists");
        Role entityRole = new Role();
        entityRole.setRoleName(RoleName.valueOf(roleName));
        Role savedRole = roleRepository.save(entityRole);
        return convertToDTO(savedRole);
    }

    public List<RoleResponseDTO> fetchAllRoles(){
        List<Role> allRoles = roleRepository.findAll();
        return convertToDTOList(allRoles);
    }

    public RoleResponseDTO fetchRoleById(Long roleId){
        Role role = roleRepository.findById(roleId).orElseThrow(()-> new ResourceNotFoundException("Role not found"));
        return convertToDTO(role);
    }

    private  RoleResponseDTO convertToDTO(Role role){
        return new RoleResponseDTO(role);
    }
    private  List<RoleResponseDTO> convertToDTOList(List<Role> roleList) {
        List<RoleResponseDTO> dtoList = new ArrayList<>();
        for(Role role : roleList) {
            dtoList.add(new RoleResponseDTO(role));
        }
        return dtoList;
    }
}
