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
        public static final String BASE_URL = "/";

        public static final String ICON = "dashboard";

        public static final class GetDashboard {
            private GetDashboard() {}

            public static final String URL = BASE_URL;
            public static final String REDIRECT_URL = "redirect:/";
            public static final String TITLE = "Vue d'ensemble";
            public static final String VIEW = "dashboard";
        }
    }

    public static final class ClientController {
        private ClientController() {}
        public static final String BASE_URL = "/clients";
        private static final String BASE_VIEW = "vehicle/";

        public static final String CLIENT = "client";
        public static final String CLIENT_LABEL = CLIENT;
        public static final String CLIENTS = "clients";
        public static final String CLIENTS_LABEL = CLIENTS;

        public static final String ICON = "people";

        public static final class GetClients {
            private GetClients() {}
            public static final String URL = BASE_URL;
            public static final String TITLE = "Liste des clients";
            public static final String VIEW = "clients";
        }
    }

    public static final class VehicleController {

        private VehicleController() {}

        public static final String BASE_URL = "/vehicules";
        public static final String REDIRECT_VEHICLES = "redirect:/vehicules";
        public static final String REDIRECT_VEHICLE_BY_REGISTRATION = "redirect:/vehicules/%s";
        public static final String PATTERN_REGISTRATION = "{registration}";
        private static final String BASE_VIEW = "vehicle/";

        public static final String VEHICLE = "vehicle";
        public static final String VEHICLE_LABEL = "véhicule";
        public static final String VEHICLES = "vehicles";
        public static final String VEHICLES_LABEL = "véhicules";

        public static final String CARS = "voitures";
        public static final String MOTORBIKES = "motos";
        public static final String PLANES = "avions";

        public static final String ICON = "directions_car";
        public static final String ICON_CAR = "drive_eta";
        public static final String ICON_MOTORBIKE = "two_wheeler";
        public static final String ICON_PLANE = "flight";

        public static final class GetVehicles {
            private GetVehicles() {}

            public static final String TITLE = "Liste des véhicules";
            public static final String VIEW = BASE_VIEW + VEHICLES;
        }

        public static final class GetVehicleByRegistration {
            private GetVehicleByRegistration() {}

            public static final String URL = "/{registration}";
            public static final String TITLE = "Fiche du véhicule";
            public static final String VIEW = BASE_VIEW + VEHICLE;
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
        public static final String BOOKINGS_LABEL = "réservations";
        public static final String BOOKING = "booking";
        public static final String BOOKING_LABEL = "réservation";

        public static final class GetBookings {
            private GetBookings() {}

            public static final String URL = BASE_URL;
            public static final String TITLE = "Liste des réservations";
            public static final String VIEW = BASE_VIEW+BOOKINGS;
        }

        public static final class GetBookingById {
            private GetBookingById() {}

            public static final String URL = BASE_URL + "/{id}";
            public static final String TITLE = "Fiche d'une réservation";
            public static final String VIEW = BASE_VIEW + "booking";
        }
    }

    public static final class EmployeeController {
        private EmployeeController() {}
        public static final String BASE_URL = "/collaborateurs";
        public static final String REDIRECT_EMPLOYEES = "redirect:/collaborateurs";
        public static final String REDIRECT_EMPLOYEE_BY_ID = "redirect:/collaborateurs/%s";
        private static final String BASE_VIEW = "employee/";
        public static final String EMPLOYEE = "employee";
        public static final String EMPLOYEE_LABEL = "collaborateur";
        public static final String EMPLOYEES = "employees";
        public static final String EMPLOYEES_LABEL = "collaborateurs";
        public static final String ICON = "work";

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
        public static final String BASE_URL = "/statistiques";

        public static final String CHARTS = "charts";
        public static final String CHARTS_LABEL = "statistiques";

        public static final String ICON = "poll";

        public static final class GetCharts {
            private GetCharts() {}


            public static final String URL = BASE_URL + "/{type}/{interval}";
            public static final String TITLE = "Statistiques";
            public static final String VIEW = "statistics";
            public static final String TOP10 = "top10";
            public static final String INTERVAL = "interval";
            public static final String TOP10_NAME = "TOP 10 : %s";
            public static final String INTERVAL_NAME = "Période : %s";
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
