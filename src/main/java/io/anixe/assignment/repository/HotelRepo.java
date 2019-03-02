package io.anixe.assignment.repository;

import io.anixe.assignment.model.dao.HotelDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepo extends JpaRepository<HotelDao, Integer> {
    Optional<HotelDao> findByName(String name);
}
