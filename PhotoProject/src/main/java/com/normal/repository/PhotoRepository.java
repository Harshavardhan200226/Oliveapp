package com.normal.repository;

import com.normal.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByEventId(Long eventId);
    List<Photo> findByEventIdAndStatus(Long eventId, Photo.PhotoStatus status);
}
