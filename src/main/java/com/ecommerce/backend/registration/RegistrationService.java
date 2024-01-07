package com.ecommerce.backend.registration;

import com.ecommerce.backend.appuser.AppUser;
import com.ecommerce.backend.appuser.AppUserRole;
import com.ecommerce.backend.appuser.AppUserService;
import com.ecommerce.backend.registration.token.ConfirmationToken;
import com.ecommerce.backend.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.email());

        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid: %s".formatted(request.email()));
        }

        return appUserService.signUpUser(new AppUser(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.password(),
                AppUserRole.USER
        ));
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());

        return "confirmed";
    }
}