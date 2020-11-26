package fr.enssat.vehiclesrental.constants;

public class ControllerConstants {

    private ControllerConstants() {}

    public static final class Controller {
        private Controller() {}

        public static final String TITLE = "title";
        public static final String MESSAGE = "message";
        public static final String PATTERN_ID = "{id}";
    }

    public static final class DashboardController {
        private DashboardController() {}

        public static final class GetDashboard {
            private GetDashboard() {}

            public static final String URL = "/";
            public static final String REDIRECT_URL = "redirect:/";
            public static final String TITLE = "Vue d'ensemble";
            public static final String VIEW = "dashboard";
        }
    }

    public static final class ClientController {
        private ClientController() {}

        public static final String BASE_URL = "/clients";
        public static final String REDIRECT_CLIENTS = "redirect:/clients";
        public static final String REDIRECT_CLIENT_BY_ID = "redirect:/clients/%s";
        private static final String BASE_VIEW = "client/";
        public static final String CLIENTS = "clients";
        public static final String CLIENT = "client";

        public static final class GetClients {
            private GetClients() {}

            public static final String TITLE = "Liste des clients";
            public static final String VIEW = BASE_VIEW + "clients";
        }

        public static final class GetClientById {
            private GetClientById() {}

            public static final String URL = "/{id}";
            public static final String TITLE = "Fiche du client";
            public static final String VIEW = BASE_VIEW + "client";
        }

        public static final class AddClient {
            private AddClient() {}

            public static final String URL = "/ajouter";
            public static final String TITLE = "Ajouter un client";
            public static final String VIEW = BASE_VIEW + "addClient";
            public static final String ERROR_MESSAGE = "Impossible d'enregister le client";
        }

        public static final class UpdateClient {
            private UpdateClient() {}

            public static final String URL = "/modifier/{id}";
            public static final String TITLE = "Modifier le client";
            public static final String VIEW = BASE_VIEW + "editClient";
            public static final String ERROR_MESSAGE = "Impossible de modifier le client";
        }

        public static final class ArchiveClient {
            private ArchiveClient() {}

            public static final String URL = "/supprimer/{id}";
            public static final String TITLE = "Archiver le client";
            public static final String VIEW = BASE_VIEW + "archiveClient";
            public static final String ERROR_MESSAGE = "Impossible d'archiver le client";
        }

        public static final class SearchClient {
            private SearchClient() {}

            public static final String URL = "/rechercher";
            public static final String TITLE = "Rechercher un client";
            public static final String VIEW = BASE_VIEW + "searchClients";
            public static final String ERROR_MESSAGE = "Impossible de rechercher le client";
        }
    }

    public static final class VehicleController {

        private VehicleController() {}

        public static final String BASE_URL = "/vehicules";
        public static final String REDIRECT_VEHICLES = "redirect:/vehicules";
        public static final String REDIRECT_VEHICLE_BY_REGISTRATION = "redirect:/vehicules/%s";
        public static final String PATTERN_REGISTRATION = "{registration}";
        private static final String BASE_VIEW = "vehicle/";
        public static final String VEHICLES = "vehicles";
        public static final String VEHICLE = "vehicle";

        public static final class GetVehicles {
            private GetVehicles() {}

            public static final String TITLE = "Liste des véhicules";
            public static final String VIEW = BASE_VIEW + "vehicles";
        }

        public static final class GetVehicleByRegistration {
            private GetVehicleByRegistration() {}

            public static final String URL = "/{registration}";
            public static final String TITLE = "Fiche du véhicule";
            public static final String VIEW = BASE_VIEW + "vehicle";
        }

        public static final class AddVehicle {
            private AddVehicle() {}

            public static final String URL = "/ajouter";
            public static final String URL_CAR = "/Car";
            public static final String URL_MOTORBIKE = "/Motorbike";
            public static final String URL_PLANE = "/Plane";
            public static final String TITLE = "Ajouter un véhicule";
            public static final String VIEW = BASE_VIEW + "addVehicle";
            public static final String ERROR_MESSAGE = "Impossible d'enregister le véhicule";
        }

        public static final class UpdateVehicle {
            private UpdateVehicle() {}

            public static final String URL = "/modifier/{registration}";
            public static final String URL_CAR = "/Car/{registration}";
            public static final String URL_MOTORBIKE = "/Motorbike/{registration}";
            public static final String URL_PLANE = "/Plane/{registration}";
            public static final String TITLE = "Modifier le véhicule";
            public static final String VIEW = BASE_VIEW + "editVehicle";
            public static final String ERROR_MESSAGE = "Impossible de modifier le véhicule";
        }

        public static final class ArchiveVehicle {
            private ArchiveVehicle() {}

            public static final String URL = "/supprimer/{id}";
            public static final String TITLE = "Archiver le véhicule";
            public static final String VIEW = BASE_VIEW + "archiveVehicle";
            public static final String ERROR_MESSAGE = "Impossible d'archiver le véhicule";
        }
    }

    public static final class BookingController {
        private BookingController() {}

        public static final String BASE_URL = "/reservations";
        private static final String BASE_VIEW = "booking/";
        public static final String BOOKINGS = "bookings";
        public static final String BOOKING = "booking";
        public static final String REDIRECT_BOOKINGS = "redirect:/reservations";
        public static final String REDIRECT_BOOKING_BY_ID = "redirect:/reservations/%s";

        public static final class GetBookings {
            private GetBookings() {}

            public static final String URL = BASE_URL;
            public static final String TITLE = "Liste des réservations";
            public static final String VIEW = BASE_VIEW+"bookings";
        }

        public static final class GetBookingById {
            private GetBookingById() {}

            public static final String URL = BASE_URL + "/{id}";
            public static final String TITLE = "Fiche d'une réservation";
            public static final String VIEW = BASE_VIEW + "booking";
        }

        public static final class CreateBooking {
            private CreateBooking() {}

            public static final String URL = BASE_URL+"/creer";
            public static final String TITLE = "Créer une réservation";
            public static final String VIEW = BASE_VIEW + "createBooking";
            public static final String ERROR_MESSAGE = "Impossible de créer la réservation";
        }

        public static final class CancelBooking {
            private CancelBooking() {}

            public static final String URL = BASE_URL+"/annuler/{id}";
            public static final String TITLE = "Annuler une réservation";
            public static final String VIEW = BASE_VIEW + "cancelVehicle";
            public static final String ERROR_MESSAGE = "Impossible d'annuler la réservation";
        }
    }

    public static final class EmployeeController {
        private EmployeeController() {}

        public static final String BASE_URL = "/collaborateurs";
        public static final String REDIRECT_EMPLOYEES = "redirect:/collaborateurs";
        public static final String REDIRECT_EMPLOYEE_BY_ID = "redirect:/collaborateurs/%s";
        private static final String BASE_VIEW = "employee/";
        public static final String EMPLOYEES = "employees";
        public static final String EMPLOYEE = "employee";

        public static final class GetEmployees {
            private GetEmployees() {}

            public static final String TITLE = "Liste des collaborateurs";
            public static final String VIEW = BASE_VIEW + "employees";
        }

        public static final class GetEmployeeById {
            private GetEmployeeById() {}

            public static final String URL = "/{id}";
            public static final String TITLE = "Fiche du collaborateur";
            public static final String VIEW = BASE_VIEW + "employee";
        }

        public static final class AddEmployee {
            private AddEmployee() {}

            public static final String URL = "/ajouter";
            public static final String TITLE = "Ajouter un collaborateur";
            public static final String VIEW = BASE_VIEW + "addEmployee";
            public static final String ERROR_MESSAGE = "Impossible d'enregister le collaborateur";
        }

        public static final class UpdateEmployee {
            private UpdateEmployee() {}

            public static final String URL = "/modifier/{id}";
            public static final String TITLE = "Modifier le collaborateur";
            public static final String VIEW = BASE_VIEW + "editEmployee";
            public static final String ERROR_MESSAGE = "Impossible de modifier le collaborateur";
        }

        public static final class DeleteEmployee {
            private DeleteEmployee() {}

            public static final String URL = "/supprimer/{id}";
            public static final String TITLE = "Supprimer le collaborateur";
            public static final String VIEW = BASE_VIEW + "deleteVehicle";
            public static final String ERROR_MESSAGE = "Impossible de supprimer le collaborateur";
        }
    }

    public static final class ChartController {
        private ChartController() {}

        public static final String CHARTS = "charts";

        public static final class GetCharts {
            private GetCharts() {}

            public static final String URL = "/statistiques";
            public static final String TITLE = "Statistiques";
            public static final String VIEW = "charts";
        }
    }

    public static final class PasswordForgotController {

        private PasswordForgotController() {}

        public static final String PASSWORD_FORGOT = "forgotPasswordForm";
        public static final String BASE_URL = "/reinitialisation-mot-de-passe";
        public static final String TITLE = "Demande du mot de passe";
        public static final String VIEW = "forgotPassword";
    }

    public static final class PasswordResetController {

        private PasswordResetController() {}

        public static final String PASSWORD_RESET = "passwordResetForm";
        public static final String BASE_URL = "/resetPassword/{option}";
        public static final String TITLE = "Réinitialisation du mot de passe";
        public static final String REDIRECT_URL_FAIL = "redirect:/resetPassword/%s?token=%s";
        public static final String REDIRECT_URL_SUCCESS = "redirect:/connexion?resetSuccess";
        public static final String VIEW = "resetPassword";
    }
}
