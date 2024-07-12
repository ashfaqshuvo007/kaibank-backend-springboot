package com.kaibank.system.service;

import com.kaibank.system.auth.KaiBankUserDetails;
import com.kaibank.system.auth.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionCompleterService {
    private static final long TASK_RUNNER_DELAY_IN_MILLIS = 3 * 60_000; // 5 minute

    @Autowired
    private TransactionService transactionService;

    @Scheduled(fixedDelay = TASK_RUNNER_DELAY_IN_MILLIS)
    public void completeTransactions() {
        User user = new User();
        user.setName("SYSTEM_SCHEDULER");
        user.setPassword("root");
        KaiBankUserDetails kaiBankUserDetails = new KaiBankUserDetails(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(kaiBankUserDetails, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Started");
        transactionService.completeTransfers();
    }


}
