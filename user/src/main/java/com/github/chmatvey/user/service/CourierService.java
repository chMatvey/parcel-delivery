package com.github.chmatvey.user.service;

import com.github.chmatvey.user.dto.CourierResponse;
import com.github.chmatvey.user.dto.CourierStatusSetRequest;
import com.github.chmatvey.user.dto.UserCreateRequest;
import com.github.chmatvey.user.entity.Courier;
import com.github.chmatvey.user.entity.User;
import com.github.chmatvey.user.repository.CourierRepository;
import com.github.chmatvey.user.repository.UserRepository;
import com.github.chmatvey.user.service.error.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final UserRepository userRepository;
    private final CourierRepository courierRepository;

    @Transactional
    public void createCourierAccount(UserCreateRequest request) {
        User user = userRepository.save(User.builder()
                .login(request.login())
                .password(request.password())
                .role(request.role())
                .build());

        courierRepository.save(Courier.builder()
                .userId(user.getId())
                .build());
    }

    public List<CourierResponse> getAllCouriers() {
        return courierRepository.findAll().stream()
                .map(CourierResponse::create)
                .toList();
    }

    public void setStatus(CourierStatusSetRequest request, long courierId) {
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(UserNotFoundException::new);

        courier.setStatus(request.status());
        courierRepository.save(courier);
    }
}
