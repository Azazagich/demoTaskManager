package org.example.demotaskmanager.service.mapper;

import java.util.List;

public interface MapperEntity <Entity, DTO> {
    Entity toEntity(DTO dto);

    List<Entity> toEntities(List<DTO> dtos);

    DTO toDTO(Entity entity);

    List<DTO> toDTOS(List<Entity> entities);
}
