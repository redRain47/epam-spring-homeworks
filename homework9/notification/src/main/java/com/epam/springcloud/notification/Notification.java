package com.epam.springcloud.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Notification {
    private String user;
    private Notifier notifyBy = Notifier.EMAIL;

    public Notification(String user) {
        this.user = user;
    }
}
