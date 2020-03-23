package com.epam.springcloud.notification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping
public class NotificationController {
    private final Set<Notification> notifications = new HashSet<>();

    @PostMapping("{username}")
    public ResponseEntity<Void> notify(@PathVariable String username) {
        notifications.add(new Notification(username));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<Notification>> getNotifications() {
        return (notifications.size() > 0)
                ? ResponseEntity.ok(notifications)
                : ResponseEntity.notFound().build();
    }
}
