package com.graduate.touslestemp.config;

import com.graduate.touslestemp.domain.dto.SocialProvider;
import com.graduate.touslestemp.domain.entity.Role;
import com.graduate.touslestemp.domain.entity.User;
import com.graduate.touslestemp.domain.repository.RoleRepository;
import com.graduate.touslestemp.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * @File: SetupDataLoader.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:10 AM
 * @Update: 21/6/2023
 */
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Overrides the onApplicationEvent method to perform additional setup logic when the application context is refreshed.
     * This method is responsible for creating initial roles, creating a user if not found, and setting up the application's initial state.
     *
     * @param event The ContextRefreshedEvent representing the application context refresh event.
     */
    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        // Create initial roles
        Role userRole = createRoleIfNotFound(Role.ROLE_STOREMANAGER);
        Role adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);
        createUserIfNotFound("admin@gmail.com", Set.of(userRole, adminRole));
        alreadySetup = true;
    }

    /**
     * Creates a new user if the user with the specified email is not found in the user repository.
     * If the user is created, it is assigned the provided roles.
     *
     * @param email The email of the user.
     * @param roles The set of roles to assign to the user.
     * @return The created user if it was not found in the repository, otherwise returns the existing user.
     */

    @Transactional
    User createUserIfNotFound(final String email, Set<Role> roles) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setDisplayName("AdminTouslesTemp");
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("@GetMapping6"));
            user.setRoles(roles);
            user.setProvider(SocialProvider.LOCAL.getProviderType());
            user.setEnabled(true);
            Date now = Calendar.getInstance().getTime();
            user.setCreatedDate(now);
            user.setModifiedDate(now);
            user = userRepository.save(user);
        }
        return user;
    }

    /**
     * Creates a new role if the role with the specified name is not found in the role repository.
     *
     * @param name The name of the role.
     * @return The created role if it was not found in the repository, otherwise returns the existing role.
     */
    @Transactional
    Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = roleRepository.save(new Role(name));
        }
        return role;
    }

  /*  @Transactional
    Salary createSalaryIfNotFound(final String name) throws Exception {
      Salary salary = salaryRepository.findSalaryByName(name);
        return salaryService.save(salary);
    }*/
}