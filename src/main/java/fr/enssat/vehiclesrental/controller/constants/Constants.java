package fr.enssat.vehiclesrental.controller.constants;

public class Constants {

    public static final class Controller {
        private Controller() {}

        public static final String TITLE = "title";
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
}
