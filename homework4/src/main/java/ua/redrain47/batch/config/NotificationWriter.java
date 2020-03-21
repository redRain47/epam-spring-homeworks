package ua.redrain47.batch.config;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import ua.redrain47.batch.model.User;

import java.util.List;

public class NotificationWriter implements ItemWriter<User> {
    private final Logger LOG = Logger.getLogger(NotificationWriter.class);
    private JavaMailSender javaMailSender;

    public NotificationWriter(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void write(List<? extends User> list) throws Exception {
        list.forEach(user -> {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setSubject("Low balance!");
            simpleMailMessage.setText("You has received this message because your balance is low. Do something with that :)");

            javaMailSender.send(simpleMailMessage);

            LOG.debug("Full name: " + user.getFullName() + ", E-mail: " + user.getEmail());
        });
    }
}
