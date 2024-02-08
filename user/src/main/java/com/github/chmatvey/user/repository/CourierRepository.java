package com.github.chmatvey.user.repository;

import com.github.chmatvey.user.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, Long> {
}
