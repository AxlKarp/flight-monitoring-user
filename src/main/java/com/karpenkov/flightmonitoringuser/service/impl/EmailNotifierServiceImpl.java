package com.karpenkov.flightmonitoringuser.service.impl;

import com.karpenkov.flightmonitoringuser.repository.Subscription;
import com.karpenkov.flightmonitoringuser.service.EmailNotifierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailNotifierServiceImpl implements EmailNotifierService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void notifySubscriber(Subscription subscription, Integer oldMinPrice, Integer newMinPrice) {
    log.debug("method notifySubscriber started");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(subscription.getEmail());
        msg.setSubject("Flights Monitoring Service");
        msg.setText(String.format("Hello, dear! \n"
        + "price for your flight has decreased \n"
        + "Old min price = %s, \n new min price = %s, \n Subscription details = %s",
                oldMinPrice,newMinPrice,subscription.toString()));
        javaMailSender.send(msg);
        log.debug("method notifySubscriber finished");
    }

    @Override
    public void notifyAddingSubscription(Subscription subscription) {
    log.debug("method notifyAddingSubscription started");
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo(subscription.getEmail());
    msg.setSubject("Flights Monitoring Service");
    msg.setText(String.format("Hello, dear! \n "
            +"Subscription has been succsesfully addded \n"
            +"Subscription details = %s", subscription.toString()));
    javaMailSender.send(msg);
    log.debug("method notifyAddingSubscription finished");
    }
}
