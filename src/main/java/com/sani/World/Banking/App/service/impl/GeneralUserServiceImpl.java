package com.sani.World.Banking.App.service.impl;

import com.sani.World.Banking.App.domain.entity.UserEntity;
import com.sani.World.Banking.App.infrastructure.config.JwtAuthenticationFilter;
import com.sani.World.Banking.App.infrastructure.config.JwtTokenProvider;
import com.sani.World.Banking.App.payload.response.BankResponse;
import com.sani.World.Banking.App.repository.UserRepository;
import com.sani.World.Banking.App.service.GeneralUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneralUserServiceImpl implements GeneralUserService {

    private final FileUploadServiceImpl fileUploadService;

    private final UserRepository userRepository;

    private final JwtAuthenticationFilter authenticationFilter;

    private final HttpServletRequest request;

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public ResponseEntity<BankResponse<String>> uploadProfilePics(MultipartFile profilePics) {

        String token = authenticationFilter.getTokenFromRequest(request);
        String email = jwtTokenProvider.getUserName(token);

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        String fileUrl = null;

        try{
            if(userEntityOptional.isPresent()){
                fileUrl = fileUploadService.uploadFile(profilePics);

                UserEntity userEntity = userEntityOptional.get();
                userEntity.setProfilePicture(fileUrl);

                userRepository.save(userEntity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(
                new BankResponse<>(
                        "Uploaded Successfully",
                        fileUrl
                )
        );
    }
}
