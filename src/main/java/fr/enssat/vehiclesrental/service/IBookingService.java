package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Booking;
import fr.enssat.vehiclesrental.model.Client;
import fr.enssat.vehiclesrental.model.enums.Status;

import java.util.Date;
import java.util.List;

public interface IBookingService {
    /**
     * Détermine si une réservation existe dans la base de données
     *
     * @param id Id d'un réservation
     * @return Vrai si la réservation existe faux sinon
     */
    boolean exists(long id);

    /**
     * Récupère une réservation dans la base de données à partir de son id
     *
     * @param id Id d'une réservation
     * @return La réservation qui a été trouvée
     */
    Booking getBooking(long id);

    /**
     * Ajouter une réservation dans la base de donnée
     *
     * @param booking Réservation a ajouter dans la base de données
     * @return La réservation ajoutée dans la base de données
     */
    Booking addBooking(Booking booking);

    /**
     * Update an existing booking in the database
     *
     * @param booking Booking to edit
     * @return The update booking
     */
    Booking editBooking(Booking booking);

    /**
     * Récupère toutes les réservations contenus dans la base de données
     *
     * @return Toutes les réservations de la base de données
     */
    List<Booking> getBookings();

    /**
     * Chercher des réservations selon des filtes
     *
     * @param startDate Date du début d'une réservation
     * @param endDate Date de la fin d'une réservation
     * @param status Etat d'une réservation
     * @see Status
     * @return Toutes les réservations correspondants à la recherche
     */
    List<Booking> searchBookings(Date startDate, Date endDate, Status status);

    /**
     * Annuler une réservation qui n'a pas commencé
     *
     * @param id Id de la réservation que l'on veut annulée
     */
    void cancelBooking(long id);

    /**
     * Passe une réservation en location
     *
     * @param id Id de la réservation qui passe en état location
     * @return Réservation avec l'état loué
     */
    Booking rentBooking(long id);

    /**
     * Archive une réservation
     *
     * @param id Id d'une réservation que l'on souhaite archivée
     * @return Réservation que l'on a archivée
     */
    Booking archiveBooking(long id);
}
