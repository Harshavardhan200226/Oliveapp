package com.trizen.photoshare.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.normal.entity.Photo;
import com.normal.repository.EventRepository;
import com.normal.repository.PhotoRepository;
import com.normal.service.CloudStorageService;

import io.micrometer.observation.Observation.Event;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired private EventRepository eventRepository;
    @Autowired private PhotoRepository photoRepository;
    @Autowired private CloudStorageService cloudStorageService;

    // Admin Route: Create Event
    @PostMapping("/admin/events")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        Event saved = eventRepository.save(event);
        return ResponseEntity.ok(saved);
    }

    // Team Member Route: Multi-part Photo Upload to Cloud
    @PostMapping("/team/events/{eventId}/upload")
    public ResponseEntity<?> uploadPhotos(
            @PathVariable Long eventId,
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("uploadedBy") String uploadedBy) {

        Event event = eventRepository.findById(eventId).orElseThrow();

        for (MultipartFile file : files) {
            try {
                Map uploadResult = cloudStorageService.uploadFile(file);
                String cloudUrl = uploadResult.get("secure_url").toString();

                Photo photo = new Photo();
                photo.setFileName(file.getOriginalFilename());
                photo.setStorageLocation(cloudUrl);
                photo.setFileSize(file.getSize());
                photo.setUploadedBy(uploadedBy);
                photo.setEvent(event);
                photo.setStatus(Photo.Status.PENDING);

                photoRepository.save(photo);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().body("Cloud upload failed");
            }
        }
        return ResponseEntity.ok(Map.of("message", "Files uploaded successfully!"));
    }

    // Admin Route: Fetch pending photos
    @GetMapping("/admin/events/{eventId}/photos")
    public List<Photo> getEventPhotos(@PathVariable Long eventId) {
        return photoRepository.findByEventId(eventId);
    }

    // Admin Route: Moderate Photo Status
    @PostMapping("/admin/photos/{photoId}/status")
    public ResponseEntity<?> updatePhotoStatus(
            @PathVariable Long photoId,
            @RequestParam Photo.Status status) {

        Photo photo = photoRepository.findById(photoId).orElseThrow();
        photo.setStatus(status);
        photoRepository.save(photo);
        return ResponseEntity.ok(Map.of("message", "Photo status updated to " + status));
    }

    // Admin Route: Publish Gallery
    @PostMapping("/admin/events/{eventId}/publish")
    public ResponseEntity<?> publishGallery(@PathVariable Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        event.setGalleryPublished(true);
        eventRepository.save(event);
        return ResponseEntity.ok(Map.of("message", "Gallery published!"));
    }

    // Customer Route: Protected Access by PIN
    @PostMapping("/public/events/{eventId}/gallery")
    public ResponseEntity<?> getCustomerGallery(
            @PathVariable Long eventId,
            @RequestBody Map<String, String> body) {

        String pin = body.get("pin");
        Event event = eventRepository.findById(eventId).orElseThrow();

        if (!event.isGalleryPublished()) {
            return ResponseEntity.status(403).body(Map.of("message", "Gallery not published yet!"));
        }

        if (!event.getPinCode().equals(pin)) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid PIN Code!"));
        }

        List<Photo> publishedPhotos = photoRepository.findByEventIdAndStatus(eventId, Photo.Status.PUBLISHED);
        return ResponseEntity.ok(publishedPhotos);
    }
}
