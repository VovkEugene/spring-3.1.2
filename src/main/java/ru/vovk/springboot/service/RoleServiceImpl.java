package ru.vovk.springboot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vovk.springboot.model.Role;
import ru.vovk.springboot.repository.RoleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository repository;

    @Override
    public List<Role> getAllRoles() {
        return repository.findAll();
    }
}
