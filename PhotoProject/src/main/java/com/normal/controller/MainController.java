package com.normal.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.normal.entity.Event;
import com.normal.entity.Photo;
import com.normal.repository.EventRepository;
import com.normal.repository.PhotoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") 
public class MainController {

    @Autowired private EventRepository eventRepository;
    @Autowired private PhotoRepository photoRepository;

    private final String UPLOAD_DIR = "uploads/";

    @PostMapping("/admin/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventRepository.save(event));
    }

    @PostMapping("/team/events/{eventId}/upload")
    public ResponseEntity<String> uploadPhotos(
            @PathVariable Long eventId,
            @RequestParam(value="uploadedBy", required=false,defaultValue="Anonymous") String uploadedBy,
            @RequestParam("files") MultipartFile[] files) throws IOException {

        Optional<Event> eventOpt = eventRepository.findById(eventId);
        if (eventOpt.isEmpty()) return ResponseEntity.badRequest().body("Event not found");

        Files.createDirectories(Paths.get(UPLOAD_DIR));

        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.write(path, file.getBytes());

            Photo photo = new Photo();
            photo.setEvent(eventOpt.get());
            photo.setFileName(file.getOriginalFilename());
            photo.setStorageUrl("/uploads/" + fileName);
            photo.setUploadedBy(uploadedBy);
            photo.setStatus(Photo.PhotoStatus.PENDING);

            photoRepository.save(photo);
        }

        return ResponseEntity.ok("Files uploaded successfully for approval.");
    }

    @GetMapping("/admin/events/{eventId}/photos")
    public ResponseEntity<List<Photo>> getAdminPhotos(@PathVariable Long eventId) {
        return ResponseEntity.ok(photoRepository.findByEventId(eventId));
    }

    @PatchMapping("/admin/photos/{photoId}/status")
    public ResponseEntity<Photo> updatePhotoStatus(
            @PathVariable Long photoId,
            @RequestParam Photo.PhotoStatus status) {

        Optional<Photo> photoOpt = photoRepository.findById(photoId);
        if (photoOpt.isEmpty()) return ResponseEntity.notFound().build();

        Photo photo = photoOpt.get();
        photo.setStatus(status);
        return ResponseEntity.ok(photoRepository.save(photo));
    }

    // Customer: View Gallery with PIN Validation
    @PostMapping("/public/events/{eventId}/gallery")
    public ResponseEntity<?> getCustomerGallery(
            @PathVariable Long eventId,
            @RequestBody Map<String, String> payload) {

        Optional<Event> eventOpt = eventRepository.findById(eventId);
        if (eventOpt.isEmpty()) return ResponseEntity.badRequest().body("Invalid Event");

        String pin = payload.get("pin");
        if (!eventOpt.get().getPinCode().equals(pin)) {
            return ResponseEntity.status(401).body("Invalid PIN code");
        }

        List<Photo> publishedPhotos = photoRepository.findByEventIdAndStatus(eventId, Photo.PhotoStatus.PUBLISHED);
        return ResponseEntity.ok(publishedPhotos);
    }
}
