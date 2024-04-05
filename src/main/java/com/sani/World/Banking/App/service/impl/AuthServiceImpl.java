package com.sani.World.Banking.App.service.impl;

import com.sani.World.Banking.App.domain.entity.UserEntity;
import com.sani.World.Banking.App.domain.enums.Roles;
import com.sani.World.Banking.App.infrastructure.config.JwtTokenProvider;
import com.sani.World.Banking.App.payload.request.EmailDetails;
import com.sani.World.Banking.App.payload.request.LoginRequest;
import com.sani.World.Banking.App.payload.request.UserRequest;
import com.sani.World.Banking.App.payload.response.APIResponse;
import com.sani.World.Banking.App.payload.response.AccountInfo;
import com.sani.World.Banking.App.payload.response.BankResponse;
import com.sani.World.Banking.App.payload.response.JwtAuthResponse;
import com.sani.World.Banking.App.repository.UserRepository;
import com.sani.World.Banking.App.service.AuthService;
import com.sani.World.Banking.App.service.EmailService;
import com.sani.World.Banking.App.utils.AccountUtils;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder encoder;

    @Override
    public BankResponse registerUser(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXIXTS_MESSAGE)
                    .accountInfo(null)
                    .build();



           }

        UserEntity newUser =  UserEntity.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .middleName(userRequest.getMiddleName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .password(encoder.encode(userRequest.getPassword()))
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status("ACTIVE")
                .roles(Roles.USER)
                .profilePicture("https://res.cloudinary.com/dpfqbb9pl/image/upload/v1701260428/maleprofile_ffeep9.png")
                .build();

        UserEntity saveUser =  userRepository.save(newUser);

        //send email alert
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(saveUser.getEmail())
                .subject("ACCOUNT CREATION")
                .messageBody("CONGRATULATIONS!!! Your account has been successfully created. \n Your Account details: \n"+
                        "Account Name: "+ saveUser.getFirstName() + " "+saveUser.getLastName()+" "+saveUser.getMiddleName()+
                        "\n Account  Number: " + saveUser.getAccountNumber())
                .build();

        emailService.sendEmailAlert(emailDetails);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(saveUser.getAccountBalance())
                        .accountNumber(saveUser.getAccountNumber())
                        .accountName(saveUser.getFirstName() +" "+
                                saveUser.getLastName() + " "+
                                saveUser.getMiddleName())
                        .build())
                .build();
    }

    @Override
    public ResponseEntity<APIResponse<JwtAuthResponse>> login(LoginRequest loginRequest) {

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(loginRequest.getEmail());

        Authentication authentication = null;

        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );


        EmailDetails loginAlert = EmailDetails.builder()
                .subject("You're logged in")
                .recipient(loginRequest.getEmail())
                .messageBody("You logged into your account. If you  did not initiate this request, contact support desk")
                .build();

        emailService.sendEmailAlert(loginAlert);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        UserEntity  userEntity = userEntityOptional.get();


        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new APIResponse<>(
                                "login successful",
                                JwtAuthResponse.builder()
                                        .accessToken(token)
                                        .id(userEntity.getId())
                                        .email(userEntity.getEmail())
                                        .gender(userEntity.getGender())
                                        .firstName(userEntity.getFirstName())
                                        .lastName(userEntity.getLastName())
                                        .profilePicture(userEntity.getProfilePicture())
                                        .roles(userEntity.getRoles())
                                        .build()

                        )
                );
    }


}