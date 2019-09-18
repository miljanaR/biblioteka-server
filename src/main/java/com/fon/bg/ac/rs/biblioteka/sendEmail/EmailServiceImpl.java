package com.fon.bg.ac.rs.biblioteka.sendEmail;

import com.fon.bg.ac.rs.biblioteka.entity.Notification;
import com.fon.bg.ac.rs.biblioteka.entity.Reservation;
import com.fon.bg.ac.rs.biblioteka.entity.Issue;
import com.fon.bg.ac.rs.biblioteka.service.NotificationService;
import com.fon.bg.ac.rs.biblioteka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EmailServiceImpl {

  @Autowired
  private JavaMailSender javaMailSender;

  @Autowired
  private NotificationService notificationService;


  public void sendEmail(Reservation reservation) {
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo(reservation.getUser().getUsername());
    msg.setSubject("Obaveštenje  o skorasnjem isteku roka rezervacije\n");
    msg.setText("Za clana sa brojem clanske karte: " + reservation.getUser().getIdKorisnika() +
        "\n E-obaveštavanje: Obaveštenje o skorašnjem isteku roka rezervacije" +
        "\n\nPoštovani,\nObaveštavamo Vas da ste rezervisali sledeću građu kojoj će narednog dana isteći rok za preuzimanje: \n" +
        "\n naziv publikacije: " +
        reservation.getPrimerak().getPublikacija().getNazivPublikacije() +
        "\n isbn: " + reservation.getPrimerak().getPublikacija().getIsbn() +
        "\n proj primerka: " + reservation.getPrimerak().getId() +
        "\n autor: " + reservation.getPrimerak().getPublikacija().getAutor() +
        "\n\nMolimo Vas da građu što pre preuzmete, u suprotnom ce Vasa reservation biti ponistena.\n\n" +
        "Srdačan pozdrav, " +
        "\n\nBiblioteka silab" +
        "\nBulevar kralja Aleksandra 71, 11120 Beograd 35 - Palilula" +
        "\ntel.: +381 11 337 05 09,\nfaks: +381 11 337 03 54" +
        "\n\nOvo je sistemska poruka koju ste primili kao naručilac e-obaveštenja, stoga ne odgovarajte na nju.");

    javaMailSender.send(msg);
    Notification notification = new Notification();
    notification.setTip("Obaveštenje  o skorasnjem isteku roka rezervacije");
    notification.setTekst("Rrezervacija [ broj: "+ reservation.getIdRezervacija() +" ] , clan [ ime i prezime: " +  reservation.getUser().getImePrezime() +
        ", clanski broj: " + reservation.getUser().getIdKorisnika() +
         " ] istice uskoro." );
    notification.setUser(reservation.getUser());
    notification.setDatum(new Date());
    notificationService.save(notification);


  }

  public void sendEmailOpomena(Issue issue) {

    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo(issue.getRezervacija().getUser().getUsername());
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    msg.setSubject("Obaveštenje  o skorasnjem isteku roka pozajmice\n");
    msg.setText("Za clana sa brojem clanske karte: " + issue.getRezervacija().getUser().getIdKorisnika() +
        "\n E-obaveštavanje: Obaveštenje o skorašnjem isteku roka pozajmice" +
        "\n\nPoštovani,\nObaveštavamo Vas da ste pozajmili sledeću građu kojoj će narednih dana isteći rok pozajmice: \n" +
        "\n rok za povratak: " + sdf.format(issue.getRokZaPovratak()) +
        "\n naziv publikacije: " + issue.getRezervacija().getPrimerak().getPublikacija().getNazivPublikacije() +
        "\n isbn: " + issue.getRezervacija().getPrimerak().getPublikacija().getIsbn() +
        "\n proj primerka: " + issue.getRezervacija().getPrimerak().getId() +
        "\n autor: " + issue.getRezervacija().getPrimerak().getPublikacija().getAutor() +
        "\n\nMolimo Vas da građu što pre vratite.\n\n" +
        "Srdačan pozdrav, " +
        "\n\nBiblioteka silab" +
        "\nBulevar kralja Aleksandra 71, 11120 Beograd 35 - Palilula" +
        "\ntel.: +381 11 337 05 09,\nfaks: +381 11 337 03 54" +
        "\n\nOvo je sistemska poruka koju ste primili kao naručilac e-obaveštenja, stoga ne odgovarajte na nju.");

    javaMailSender.send(msg);
    Notification notification = new Notification();
    notification.setTip("Obaveštenje  o skorasnjem isteku roka pozajmice");
    notification.setTekst("Issue [ broj: "+ + issue.getIdZaduzenje() +" ] , clan [ ime i prezime: "+  issue.getRezervacija().getUser().getImePrezime() +
        ", clanski broj: " + issue.getRezervacija().getUser().getIdKorisnika() + " ] istice uskoro." );
    notification.setUser(issue.getRezervacija().getUser());
    notification.setDatum(new Date());
    notificationService.save(notification);

  }


}

