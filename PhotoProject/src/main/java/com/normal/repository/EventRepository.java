package com.normal.repository;

import com.normal.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Basic CRUD methods (findById, save, deleteById) are automatically inherited
}
