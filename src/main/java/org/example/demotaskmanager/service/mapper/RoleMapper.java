package org.example.demotaskmanager.service.mapper;

import org.example.demotaskmanager.domain.Role;
import org.example.demotaskmanager.service.dto.RoleDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RoleMapper extends MapperEntity<Role, RoleDTO> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(RoleDTO dto, @MappingTarget Role entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void fullUpdate(RoleDTO dto, @MappingTarget Role entity);
}
