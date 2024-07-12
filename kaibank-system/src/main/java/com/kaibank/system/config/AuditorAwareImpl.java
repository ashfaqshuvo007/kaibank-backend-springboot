package com.kaibank.system.config;

import com.kaibank.system.auth.KaiBankUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The AuditorAwareImpl class implements AuditorAware interface to provide currently logged in user
 * for auditing
 *
 * @author Pabasara
 * @version 1.0
 */
@Component
@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        KaiBankUserDetails kaiBankUserDetails = (KaiBankUserDetails) loggedInUser.getPrincipal();
        return Optional.of(kaiBankUserDetails.getName());
    }
}
