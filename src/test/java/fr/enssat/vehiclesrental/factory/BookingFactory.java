package fr.enssat.vehiclesrental.factory;

import fr.enssat.vehiclesrental.model.Booking;
import fr.enssat.vehiclesrental.model.enums.Status;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

/**
 * Créer des réservations qui sont utilisé uniquement pour les tests.
 */
public class BookingFactory {
    /**
     * Créer une réservation à l'état disponible
     *
     * @return Une réservation qui commence dans 1 jour et se termine dans 1 semaine
     */
    public static Booking getBookingAvailable(){
        // Nous crééons un date de début de réservation qui commence demain
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, 1);
        startDate.set(Calendar.HOUR_OF_DAY,0);
        startDate.set(Calendar.MINUTE,0);
        startDate.set(Calendar.SECOND,0);

        LocalDate startLocal = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault());

        // Nous crééons un date de fin de réservation qui termin dans une semaine
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, 7);
        endDate.set(Calendar.HOUR_OF_DAY,0);
        endDate.set(Calendar.MINUTE,0);
        endDate.set(Calendar.SECOND,0);

        LocalDate endLocal = LocalDate.ofInstant(endDate.toInstant(), ZoneId.systemDefault());

        /**
         * On convertit les dates en timestamp pour pouvoir
         * les utiliser dans le modèle de Booking.
         */
        Timestamp startTime = new Timestamp(startDate.getTimeInMillis());
        startTime.setNanos(0);
        Timestamp endTime = new Timestamp(endDate.getTimeInMillis());
        endTime.setNanos(0);

        return Booking.builder()
        .id(148632579L)
        .startDate(startLocal)
        .endDate(endLocal)
        .expectedNumberKm(2500)
        .expectedPrice(2000.5f)
        .isDiscount(true)
        .discount(200.5f)
        .status(Status.AVAILABLE)
        .client(ClientFactory.getClient())
        .vehicle(VehicleFactory.getMotorbike())
        .build();
    }

    /**
     * Créer une réservation à l'état archivé
     *
     * @return Une réservation qui est archivée
     */
    public static Booking getBookingArchived(){
        // Nous crééons un date de début de réservation qui commence demain
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, -7);
        startDate.set(Calendar.HOUR_OF_DAY,0);
        startDate.set(Calendar.MINUTE,0);
        startDate.set(Calendar.SECOND,0);

        LocalDate startLocal = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault());

        // Nous crééons un date de fin de réservation qui termin dans une semaine
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, -1);
        endDate.set(Calendar.HOUR_OF_DAY,0);
        endDate.set(Calendar.MINUTE,0);
        endDate.set(Calendar.SECOND,0);

        LocalDate endLocal = LocalDate.ofInstant(endDate.toInstant(), ZoneId.systemDefault());

        /**
         * On convertit les dates en timestamp pour pouvoir
         * les utiliser dans le modèle de Booking.
         */
        Timestamp startTime = new Timestamp(startDate.getTimeInMillis());
        startTime.setNanos(0);
        Timestamp endTime = new Timestamp(endDate.getTimeInMillis());
        endTime.setNanos(0);

        return Booking.builder()
                .id(148632579L)
                .startDate(startLocal)
                .endDate(endLocal)
                .expectedNumberKm(2500)
                .expectedPrice(2000.5f)
                .isDiscount(true)
                .discount(200.5f)
                .status(Status.ARCHIVED)
                .client(ClientFactory.getClient())
                .vehicle(VehicleFactory.getMotorbike())
                .build();
    }

    /**
     * Créer une réservation à l'état disponible qui commence dans 1 mois et se termine dans 2 mois
     *
     * @return Une réservation qui commence dans 1 mois et se termine dans 2 mois
     */
    public static Booking getBookingAvailableStart1MonthEnd2Month(){
        // Nous crééons un date de début de réservation qui commence demain
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH,1);
        startDate.set(Calendar.HOUR_OF_DAY,0);
        startDate.set(Calendar.MINUTE,0);
        startDate.set(Calendar.SECOND,0);

        LocalDate startLocal = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault());

        // Nous crééons un date de fin de réservation qui termin dans une semaine
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 2);
        endDate.set(Calendar.HOUR_OF_DAY,0);
        endDate.set(Calendar.MINUTE,0);
        endDate.set(Calendar.SECOND,0);

        LocalDate endLocal = LocalDate.ofInstant(endDate.toInstant(), ZoneId.systemDefault());

        /**
         * On convertit les dates en timestamp pour pouvoir
         * les utiliser dans le modèle de Booking.
         */
        Timestamp startTime = new Timestamp(startDate.getTimeInMillis());
        startTime.setNanos(0);
        Timestamp endTime = new Timestamp(endDate.getTimeInMillis());
        endTime.setNanos(0);

        return Booking.builder()
                .id(148632579L)
                .startDate(startLocal)
                .endDate(endLocal)
                .expectedNumberKm(2500)
                .expectedPrice(2000.5f)
                .isDiscount(true)
                .discount(200.5f)
                .status(Status.AVAILABLE)
                .client(ClientFactory.getClient())
                .vehicle(VehicleFactory.getMotorbike())
                .build();
    }

    /**
     * Créer une réservation à l'état loué qui commence dans aujourd'hui et se termine dans 1 semaine
     *
     * @return Une réservation qui commence aujourd'hui et se termine dans 1 semaine
     */
    public static Booking getBookingRent(){
        // Nous crééons un date de début de réservation qui commence demain
        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.HOUR_OF_DAY,0);
        startDate.set(Calendar.MINUTE,0);
        startDate.set(Calendar.SECOND,0);

        // Nous crééons un date de fin de réservation qui termin dans une semaine
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, 7);
        endDate.set(Calendar.HOUR_OF_DAY,0);
        endDate.set(Calendar.MINUTE,0);
        endDate.set(Calendar.SECOND,0);

        LocalDate startLocal = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDate endLocal = LocalDate.ofInstant(endDate.toInstant(), ZoneId.systemDefault());

        /**
         * On convertit les dates en timestamp pour pouvoir
         * les utiliser dans le modèle de Booking.
         */
        Timestamp startTime = new Timestamp(startDate.getTimeInMillis());
        startTime.setNanos(0);
        Timestamp endTime = new Timestamp(endDate.getTimeInMillis());
        endTime.setNanos(0);

        return Booking.builder()
                .id(148632581L)
                .startDate(startLocal)
                .endDate(endLocal)
                .expectedNumberKm(250)
                .expectedPrice(1000.0f)
                .isDiscount(true)
                .discount(200.5f)
                .status(Status.RENTED)
                .client(ClientFactory.getClient())
                .vehicle(VehicleFactory.getPlane())
                .build();
    }
    /**
     * Créer une réservation à l'état disponible
     *
     * @return Une réservation qui commence le 12 octobre et se termine le 15 octobre
     */
    public static Booking getBookingAvailableInOctober(){
        // Nous crééons un date de début de réservation qui commence demain
        Calendar startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR),10,12,0,0,0);

        // Nous crééons un date de fin de réservation qui termin dans une semaine
        Calendar endDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR),10,15,0,0,0);

        LocalDate startLocal = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDate endLocal = LocalDate.ofInstant(endDate.toInstant(), ZoneId.systemDefault());

        /**
         * On convertit les dates en timestamp pour pouvoir
         * les utiliser dans le modèle de Booking.
         */
        Timestamp startTime = new Timestamp(startDate.getTimeInMillis());
        startTime.setNanos(0);
        Timestamp endTime = new Timestamp(endDate.getTimeInMillis());
        endTime.setNanos(0);

        return Booking.builder()
                .id(148632580L)
                .startDate(startLocal)
                .endDate(endLocal)
                .expectedNumberKm(500)
                .expectedPrice(300f)
                .isDiscount(false)
                .discount(0f)
                .status(Status.AVAILABLE)
                .client(ClientFactory.getClient())
                .vehicle(VehicleFactory.getMotorbike())
                .build();
    }

    /**
     * Créer une réservation qui commence hier
     *
     * @return Une réservation qui commence hier
     */
    public static Booking getBookingBeforeToday(){
        // Nous crééons un date de début de réservation qui commence demain
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, -1);
        startDate.set(Calendar.HOUR_OF_DAY,0);
        startDate.set(Calendar.MINUTE,0);
        startDate.set(Calendar.SECOND,0);

        // Nous crééons un date de fin de réservation qui termin dans une semaine
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, 7);
        endDate.set(Calendar.HOUR_OF_DAY,0);
        endDate.set(Calendar.MINUTE,0);
        endDate.set(Calendar.SECOND,0);

        LocalDate startLocal = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDate endLocal = LocalDate.ofInstant(endDate.toInstant(), ZoneId.systemDefault());

        /**
         * On convertit les dates en timestamp pour pouvoir
         * les utiliser dans le modèle de Booking.
         */
        Timestamp startTime = new Timestamp(startDate.getTimeInMillis());
        startTime.setNanos(0);
        Timestamp endTime = new Timestamp(endDate.getTimeInMillis());
        endTime.setNanos(0);

        return Booking.builder()
                .id(148632579L)
                .startDate(startLocal)
                .endDate(endLocal)
                .expectedNumberKm(2500)
                .expectedPrice(2000.5f)
                .isDiscount(true)
                .discount(200.5f)
                .status(Status.AVAILABLE)
                .client(ClientFactory.getClient())
                .vehicle(VehicleFactory.getMotorbike())
                .build();
    }

    public static Booking getBookingStartAfterEnd(){
        // Nous crééons un date de début de réservation qui commence demain
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, 7);
        startDate.set(Calendar.HOUR_OF_DAY,0);
        startDate.set(Calendar.MINUTE,0);
        startDate.set(Calendar.SECOND,0);

        // Nous crééons un date de fin de réservation qui termin dans une semaine
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, 0);
        endDate.set(Calendar.HOUR_OF_DAY,0);
        endDate.set(Calendar.MINUTE,0);
        endDate.set(Calendar.SECOND,0);

        LocalDate startLocal = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDate endLocal = LocalDate.ofInstant(endDate.toInstant(), ZoneId.systemDefault());

        /**
         * On convertit les dates en timestamp pour pouvoir
         * les utiliser dans le modèle de Booking.
         */
        Timestamp startTime = new Timestamp(startDate.getTimeInMillis());
        startTime.setNanos(0);
        Timestamp endTime = new Timestamp(endDate.getTimeInMillis());
        endTime.setNanos(0);

        return Booking.builder()
                .id(148632579L)
                .startDate(startLocal)
                .endDate(endLocal)
                .expectedNumberKm(2500)
                .expectedPrice(2000.5f)
                .isDiscount(true)
                .discount(200.5f)
                .status(Status.AVAILABLE)
                .client(ClientFactory.getClient())
                .vehicle(VehicleFactory.getMotorbike())
                .build();
    }
}
