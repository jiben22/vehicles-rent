package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.password.Mail;

public interface IEmailService {
    void sendEmail(Mail mail);

}

