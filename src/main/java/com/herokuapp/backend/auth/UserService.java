package com.herokuapp.backend.auth;

import com.herokuapp.backend.client.ClientServiceFacade;
import com.herokuapp.backend.corporation.CorporationServiceFacade;
import com.herokuapp.backend.driver.DriverServiceFacade;
import com.herokuapp.backend.email.EmailService;
import javassist.NotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private final ClientServiceFacade clientService;
    private final CorporationServiceFacade corporationService;
    private final DriverServiceFacade driverService;
    private final EmailService emailService;
    private final FirebaseRegistrationService firebaseRegistrationService;

    public UserService(ClientServiceFacade clientService, CorporationServiceFacade corporationService, DriverServiceFacade driverService, EmailService emailService, FirebaseRegistrationService firebaseRegistrationService) {
        this.clientService = clientService;
        this.corporationService = corporationService;
        this.driverService = driverService;
        this.emailService = emailService;
        this.firebaseRegistrationService = firebaseRegistrationService;
    }

    void sendPasswordResetToken(String email) {
        if (clientService.existsByEmail(email)) {
            String token = RandomStringUtils.randomAlphabetic(20);
            clientService.setPasswordResetToken(email, token);
            emailService.sendPasswordResetEmail(email, token);
        } else if (corporationService.existsByEmail(email)) {
            String token = RandomStringUtils.randomAlphabetic(20);
            corporationService.setPasswordResetToken(email, token);
            emailService.sendPasswordResetEmail(email, token);
        } else if (driverService.existByEmail(email)) {
            String token = RandomStringUtils.randomAlphabetic(20);
            driverService.setPasswordResetToken(email, token);
            emailService.sendPasswordResetEmail(email, token);
        } else {
            throw new IllegalArgumentException("There is no user connected with this email.");
        }
    }

    void resetPassword(String token, String newPassword, String newPasswordConfirm) throws ExecutionException, InterruptedException {
        String email = getEmailByToken(token);
        if (!newPassword.equals(newPasswordConfirm))
            throw new IllegalArgumentException("Provided passwords do not match.");
        else if (email != null && passwordResetTokenActive(token)) {
            firebaseRegistrationService.resetPassword(email, newPassword);
            deactivatePasswordResetToken(token);
            emailService.sendPasswordResetConfirmationEmail(email);
        } else throw new IllegalArgumentException("The token is not valid or no longer active.");
    }

    private String getEmailByToken(String token) {
        if (driverService.getEmailByPasswordResetToken(token) != null)
            return driverService.getEmailByPasswordResetToken(token);
        else if (corporationService.getEmailByPasswordResetToken(token) != null)
            return corporationService.getEmailByPasswordResetToken(token);
        else if (clientService.getEmailByPasswordResetToken(token) != null)
            return clientService.getEmailByPasswordResetToken(token);
        else {
            throw new IllegalArgumentException("The provided reset token is not valid.");
        }
    }

    private void deactivatePasswordResetToken(String token) {
        clientService.deactivatePasswordResetToken(token);
        corporationService.deactivatePasswordResetToken(token);
        driverService.deactivatePasswordResetToken(token);
    }

    private Boolean passwordResetTokenActive(String token) {
        if (clientService.passwordResetTokenActive(token)) return true;
        else if (corporationService.passwordResetTokenActive(token)) return true;
        else return driverService.passwordResetTokenActive(token);
    }
}