package dku.globalsw.nomorerounding.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String name, String email, String password) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(email);

        simpleMailMessage.setSubject("임시 비밀번호를 보내드립니다.");

        simpleMailMessage.setText(name + "님의 임시 비밀번호는 " + password + " 입니다");

        javaMailSender.send(simpleMailMessage);
    }
}
