package fr.enssat.vehiclesrental.constants;

public class MailConstants {

    private MailConstants() {}

    public static final String RECIPIENT = "recipient";
    public static final String FIRSTNAME = "firstname";
    public static final String SUBJECT = "subject";
    public static final String RESET_URL = "resetUrl";
    public static final String TEMPLATE_ID = "templateId";

    public static final class CreatePassword {
        private CreatePassword() {}

        public static final String SUBJECT = "Bienvenue dans l'entreprise %s";
        public static final String URL = "%s/resetPassword/new/?token=%s";
        public static final String TEMPLATE_ID = "1958686";
    }

    public static final class ResetPassword {
        private ResetPassword() {}

        public static final String SUBJECT = "Vehi'Rental - Réinitialisation du mot de passe";
        public static final String URL = "%s/resetPassword/pwd?token=%s";
        public static final String TEMPLATE_ID = "1894529";
    }
}
