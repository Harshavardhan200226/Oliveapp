package com.normal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.normal.entity.Photo;
import com.normal.entity.Photo.Status;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

	List<Photo> findByEventIdAndStatus(Long eventId, Status published);

	List<Photo> findByEventId(Long eventId);

}
