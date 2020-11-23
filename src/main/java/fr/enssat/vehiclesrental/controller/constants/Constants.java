package fr.enssat.vehiclesrental.controller.constants;

public class Constants {

    public static final class Controller {
        private Controller() {}

        public static final String TITLE = "title";
        public static final String MESSAGE = "message";
    }

    public static final class DashboardController {
        private DashboardController() {}

        public static final class GetDashboard {
            private GetDashboard() {}

            public static final String URL = "/";
            public static final String TITLE = "Vue d'ensemble";
            public static final String VIEW = "dashboard";
        }
    }

    public static final class ClientController {
        private ClientController() {}

        public static final String CLIENTS = "clients";

        public static final class GetClients {
            private GetClients() {}

            public static final String URL = "/clients";
            public static final String TITLE = "Liste des clients";
            public static final String VIEW = "clients";
        }
    }

    public static final class VehicleController {

        private VehicleController() {}

        public static final String BASE_URL = "/vehicules";
        private static final String BASE_VIEW = "vehicle/";
        public static final String VEHICLES = "vehicles";
        public static final String VEHICLE = "vehicle";

        public static final class GetVehicles {
            private GetVehicles() {}

            public static final String URL = BASE_URL;
            public static final String TITLE = "Liste des véhicules";
            public static final String VIEW = BASE_VIEW + "vehicles";
        }

        public static final class GetVehicleById {
            private GetVehicleById() {}

            public static final String URL = BASE_URL + "/{registration}";
            public static final String TITLE = "Fiche du véhicule";
            public static final String VIEW = BASE_VIEW + "vehicle";
        }

        public static final class AddVehicle {
            private AddVehicle() {}

            public static final String URL = BASE_URL + "/ajouter";
            public static final String URL_CAR = BASE_URL + "/fr.enssat.vehiclesrental.model.Car";
            public static final String URL_MOTORBIKE = BASE_URL + "/fr.enssat.vehiclesrental.model.Motorbike";
            public static final String URL_PLANE = BASE_URL + "/fr.enssat.vehiclesrental.model.Plane";
            public static final String TITLE = "Ajouter un véhicule";
            public static final String VIEW = BASE_VIEW + "addVehicle";
            public static final String ERROR_MESSAGE = "Impossible d'enregister le véhicule";
        }

        public static final class UpdateVehicle {
            private UpdateVehicle() {}

            public static final String URL = BASE_URL + "/{id}";
            public static final String TITLE = "Modifier le véhicule";
            public static final String VIEW = BASE_VIEW + "editVehicle";
            public static final String ERROR_MESSAGE = "Impossible de modifier le véhicule";
        }

        public static final class DeleteVehicle {
            private DeleteVehicle() {}

            public static final String URL = BASE_URL + "/{id}";
            public static final String TITLE = "Supprimer le véhicule";
            public static final String VIEW = BASE_VIEW + "archiveVehicle";
            public static final String ERROR_MESSAGE = "Impossible de supprimer le véhicule";
        }
    }

    public static final class BookingController {
        private BookingController() {}

        public static final String BOOKINGS = "bookings";

        public static final class GetBookings {
            private GetBookings() {}

            public static final String URL = "/bookings";
            public static final String TITLE = "Liste des réservations";
            public static final String VIEW = "bookings";
        }
    }

    public static final class EmployeeController {
        private EmployeeController() {}

        public static final String EMPLOYEES = "employees";

        public static final class GetEmployees {
            private GetEmployees() {}

            public static final String URL = "/employes";
            public static final String TITLE = "Liste des employés";
            public static final String VIEW = "employees";
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
}
