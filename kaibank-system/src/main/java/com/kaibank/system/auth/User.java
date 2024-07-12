package com.kaibank.system.auth;

import com.kaibank.system.entity.Role;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * The User class is used to define user details for authentication.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Component
@Data
public class User {

  private String name;

  private String username;

  private String password;

  private boolean isFirstLogin;

  Set<Role> roles;
}
