package com.kaibank.system.service;

import com.kaibank.system.dto.converter.EmployeeConverter;
import com.kaibank.system.dto.converter.RoleConverter;
import com.kaibank.system.dto.model.EmployeeDTO;
import com.kaibank.system.dto.model.RoleDTO;
import com.kaibank.system.entity.Employee;
import com.kaibank.system.entity.Role;
import com.kaibank.system.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * This EmployeeService class is used to implement logics related to employee.
 *
 * @author Boomika
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = false)
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws Exception {

        Employee emp = employeeRepository.getEmployeeByUsername(employeeDTO.getUsername());
        if (emp != null) {
            throw new DuplicateKeyException("This username is taken");
        }

        Employee employee = employeeConverter.employeeDtoToEmployee(employeeDTO);
        int password = generateRandomPassword();
        employee.setPassword(passwordEncoder.encode(Integer.toString(password)));
        employee.setFirstLogin(true);
        employee.setAttempts(0);

        Set<Role> roles = convertRoleDtoToEntities(employeeDTO.getRoleDTOSet());
        employee.setRoles(roles);

        employee = employeeRepository.save(employee);
        EmployeeDTO empDTO = employeeConverter.employeeToEmployeeDto(employee);
        empDTO.setPassword(Integer.toString(password));
        empDTO.setRoleDTOSet(employeeDTO.getRoleDTOSet());
        empDTO.setBranchDTO(employeeDTO.getBranchDTO());
        return empDTO;
    }

    public Set<Role> convertRoleDtoToEntities(Set<RoleDTO> roleDTOS) {
        Set<Role> roles = new HashSet<>();
        roleDTOS.forEach(r -> roles.add(roleConverter.roleDtoToRole(r)));
        return roles;
    }

    public int generateRandomPassword() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}
