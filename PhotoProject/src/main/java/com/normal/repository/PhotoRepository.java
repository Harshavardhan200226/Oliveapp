package com.normal.repository;

import java.util.List;

import com.normal.entity.Photo;
import com.normal.entity.Photo.Status;

public interface PhotoRepository {

	List<Photo> findByEventId(Long eventId);

	List<Photo> findByEventIdAndStatus(Long eventId, Status published);

	Object findById(Long photoId);

	void save(Photo photo);

}
