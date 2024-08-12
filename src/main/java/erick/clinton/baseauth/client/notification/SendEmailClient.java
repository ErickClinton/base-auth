package erick.clinton.baseauth.client.notification;

import erick.clinton.baseauth.client.notification.entity.EmailEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name="notification",
        url = "${client.notification-service.url}"
)
public interface SendEmailClient {
    @PostMapping
    ResponseEntity<Void> sendEmail(EmailEntity email);
}
