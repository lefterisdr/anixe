package io.anixe.assignment.repository;

import io.anixe.assignment.model.dao.BookingDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BookingRepo extends JpaRepository<BookingDao, Integer> {
    Set<BookingDao> findByCustomerSurname(String customerSurname);
}
