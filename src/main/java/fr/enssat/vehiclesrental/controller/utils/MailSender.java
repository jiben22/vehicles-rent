package fr.enssat.vehiclesrental.controller.utils;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Email;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

@Slf4j
public class MailSender {

    public static boolean sendMail(Map<String, String> mailContent) {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;

        //TODO: set in configuration yaml
        client = new MailjetClient("80048a6d41106822546ce8ffe0cdc98e", "585f54e6122779fa0e14c39cec8b611b");

        request = new MailjetRequest(Email.resource)
                .property(Email.FROMEMAIL, "blegallo@enssat.fr")
                .property(Email.FROMNAME, "Vehi'Rental - Location de véhicules")
                .property(Email.SUBJECT, mailContent.get("subject"))
                .property(Email.RECIPIENTS, new JSONArray()
                        .put(new JSONObject()
                                .put("Email", mailContent.get("recipient"))))
                .property(Email.MJTEMPLATEID, Integer.valueOf(mailContent.get("templateId")))
                .property(Email.MJTEMPLATELANGUAGE, true)
                .property(Email.VARS, new JSONObject()
                        .put("firstname", mailContent.get("firstname"))
                        .put("resetUrl", mailContent.get("resetUrl"))
                        .put("subject", mailContent.get("subject")));

        try {
            response = client.post(request);
            return response.getStatus() == 200;
        } catch(Exception e) {
            log.error(e.toString());
        }

        return false;

    }

}
