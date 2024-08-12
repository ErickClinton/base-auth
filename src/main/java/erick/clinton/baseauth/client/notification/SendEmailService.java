package erick.clinton.baseauth.client.notification;

import erick.clinton.baseauth.client.notification.entity.EmailEntity;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    private final SendEmailClient sendEmailClient;

    public  SendEmailService(SendEmailClient sendEmailClient){
        this.sendEmailClient = sendEmailClient;
    }

    public void sendEmail(EmailEntity email){
        var response = sendEmailClient.sendEmail(email);

        if(response.getStatusCode().isError()){
            System.out.println("Error send email");
        }
    }
}
