package org.example.demotaskmanager.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.demotaskmanager.domain.Role;
import org.example.demotaskmanager.domain.User;
import org.example.demotaskmanager.repository.RoleRepository;
import org.example.demotaskmanager.service.dto.RoleDTO;
import org.example.demotaskmanager.service.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper){
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }


    public RoleDTO getById(Long id) {
        return roleMapper.toDTO(roleRepository.findById(id).orElseThrow());
    }

    public List<RoleDTO> getAll() {
        return roleMapper.toDTOS(roleRepository.findAll());
    }

    public RoleDTO save(RoleDTO roleDTO) {
        return roleMapper.toDTO(roleRepository.save(roleMapper.toEntity(roleDTO)));
    }

    //full update це без перевірки на null просто сетається Full Update (Повне оновлення)
    //Повне оновлення означає, що всі поля DTO повинні перезаписати відповідні поля сутності, навіть якщо вони мають значення null.
    public boolean updateAll(Long id, RoleDTO roleDTO) {
        Role role = roleRepository.findById(id).orElseThrow();
        roleMapper.fullUpdate(roleDTO, role);
        roleRepository.save(role);
        return true;
    }

    //partial update це перевірка на null(Partial Update (Часткове оновлення)
    //Часткове оновлення означає, що оновлюються лише ті поля, які явно задані у DTO. Поля, що мають значення null, не повинні перезаписувати існуючі дані у базі.)
    public boolean update(Long id, RoleDTO roleDTO) {
        Role role = roleRepository.findById(id).orElseThrow();
        roleMapper.partialUpdate(roleDTO, role);
        roleRepository.save(role);
        return true;
    }

    public void deleteById(Long id) {
        //
//        Role role = roleRepository.findById(id).orElseThrow();
//        Set<User> users =  role.getUsers();
//        users.forEach(user -> user.setRole(null));
//        users.forEach(role::removeUser);
        //
        try {
            roleRepository.updateToNullUsersByRoleId(id);
            roleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Role with id " + id + " not found");
        }
    }
}
