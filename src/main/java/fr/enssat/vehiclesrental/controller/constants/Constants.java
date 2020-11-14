package fr.enssat.vehiclesrental.controller.constants;

public class Constants {

    public static final class Controller {
        private Controller() {}

        public static final String TITLE = "title";
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

        public static final String VEHICLES = "vehicles";

        public static final class GetVehicles {
            private GetVehicles() {}

            public static final String URL = "/vehicules";
            public static final String TITLE = "Liste des véhicules";
            public static final String VIEW = "vehicles";
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
