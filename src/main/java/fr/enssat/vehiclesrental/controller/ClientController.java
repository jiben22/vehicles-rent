package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.constants.ControllerConstants.ClientController.*;
import fr.enssat.vehiclesrental.model.Client;
import fr.enssat.vehiclesrental.service.ClientService;
import fr.enssat.vehiclesrental.service.exception.notfound.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

import static fr.enssat.vehiclesrental.constants.ControllerConstants.ClientController.*;
import static fr.enssat.vehiclesrental.constants.ControllerConstants.Controller.*;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping(BASE_URL)
public class ClientController {

    private final ClientService clientService;

    /**
     * Afficher la liste des clients
     * @param springModel Modèle
     * @param lastname Nom
     * @param firstname Prénom
     * @param email Email
     * @param zipcode Code postal
     * @return la liste des clients correspondant aux paramètres de requête
     */
    private String showClients(Model springModel,
                              @RequestParam(defaultValue = "") String lastname,
                              @RequestParam(defaultValue = "") String firstname,
                              @RequestParam(defaultValue = "") String email,
                              @RequestParam(defaultValue = "") String zipcode) {
        log.info(String.format("GET %s", BASE_URL));
        springModel.addAttribute(TITLE, GetClients.TITLE);

        List<Client> clients = clientService.searchClients(firstname, lastname, email, zipcode);
        springModel.addAttribute(CLIENTS, clients);

        return GetClients.VIEW;
    }

    /**
     * Afficher les informations d'un client
     * @param springModel Modèle
     * @param id ID du client
     * @return la fiche du client
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_CLIENT.label)")
    @GetMapping(GetClientById.URL)
    public String showClientById(Model springModel, @PathVariable String id) {
        log.info(String.format("GET %s", StringUtils.replace(GetClientById.URL, PATTERN_ID, id)));
        springModel.addAttribute(TITLE, GetClientById.TITLE);

        //TODO: return 404 if client not found
        Client client = clientService.getClient(Long.parseLong(id));
        springModel.addAttribute(CLIENT, client);

        return GetClientById.VIEW;
    }

    /**
     * Afficher le formulaire d'ajout d'un client
     * @param springModel Modèle
     * @return le formulaire d'ajout
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_CLIENT.label)")
    @GetMapping(AddClient.URL)
    public String showAddClient(Model springModel) {
        log.info(String.format("GET %s", AddClient.URL));
        springModel.addAttribute(TITLE, AddClient.TITLE);

        Client client = new Client();
        springModel.addAttribute(CLIENT, client);

        return AddClient.VIEW;
    }

    /**
     * Ajouter un client
     * @param client Client
     * @param result Binding result
     * @param springModel Modèle
     * @param redirectAttributes Redirect attributes
     * @return la fiche du client ou le formulaire d'ajout avec les erreurs
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_CLIENT.label)")
    @PostMapping(AddClient.URL)
    public String addClient(@Valid @ModelAttribute(CLIENT) Client client,
                            BindingResult result,
                            Model springModel,
                            RedirectAttributes redirectAttributes) {
        log.info(String.format("POST %s", AddClient.URL));
        springModel.addAttribute(TITLE, AddClient.TITLE);

        // Check if form has errors
        if (result.hasErrors()) {
            log.info(result.toString());

            // Return form with errors
            return AddClient.VIEW;
        }

        try {
            Client existedClient = clientService.getClientByEmail(client.getEmail());
            if (existedClient != null) {
                result.rejectValue("email", "client.email",
                        "L'email est déjà attribué pour un autre client");

                // Return form with errors
                return AddClient.VIEW;
            }
        } catch (ClientNotFoundException clientNotFoundException) {
            try {
                // Save client
                client = clientService.addClient(client);
            } catch (Exception exception) {
                log.error(exception.getMessage() + exception.getCause());
                redirectAttributes.addFlashAttribute(MESSAGE, AddClient.ERROR_MESSAGE);

                return REDIRECT_CLIENTS;
            }
        }

        return String.format(REDIRECT_CLIENT_BY_ID, client.getId());
    }

    /**
     * Afficher le formulaire de modification d'un client
     * @param springModel Modèle
     * @param id ID du client
     * @return le formulaire de modification d'un client
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_CLIENT.label)")
    @GetMapping(UpdateClient.URL)
    public String showUpdateClient(Model springModel, @PathVariable String id) {
        log.info(String.format("GET %s", StringUtils.replace(UpdateClient.URL, PATTERN_ID, id)));
        springModel.addAttribute(TITLE, UpdateClient.TITLE);

        Client client = clientService.getClient(Long.parseLong(id));
        springModel.addAttribute(CLIENT, client);

        return UpdateClient.VIEW;
    }

    /**
     * Met à jour un client
     * @param client Client
     * @param id ID du client
     * @param result Binding result
     * @param springModel Modèle
     * @param redirectAttributes Redirect attributes
     * @return la fiche du client mise à jour ou le formulaire de modification avec les erreurs
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_CLIENT.label)")
    @PostMapping(UpdateClient.URL)
    public String updateClient(@Valid @ModelAttribute(CLIENT) Client client,
                                @PathVariable String id,
                                BindingResult result,
                                Model springModel,
                                RedirectAttributes redirectAttributes) {
        log.info(String.format("POST %s", StringUtils.replace(UpdateClient.URL, PATTERN_ID, id)));
        springModel.addAttribute(TITLE, UpdateClient.TITLE);

        if (result.hasErrors()) {
            log.info(result.toString());

            // Return form with errors
            return UpdateClient.VIEW;
        }

        try {
            // Update client
            if (clientService.exists(client.getId())) {
                client = clientService.editClient(client);
            } else {
                throw new Exception("Le client n'existe pas " + client.getId());
            }
        } catch (Exception exception) {
            log.error(exception.getMessage() + exception.getCause());
            redirectAttributes.addFlashAttribute(MESSAGE, UpdateClient.ERROR_MESSAGE);

            return REDIRECT_CLIENTS;
        }

        return String.format(REDIRECT_CLIENT_BY_ID, client.getId());
    }

    /**
     * Archiver un client
     * @param id ID du client
     * @param redirectAttributes Redirect attributes
     * @return la liste des clients ou un message d'erreur si l'archivage échoue
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_CLIENT.label)")
    @GetMapping(ArchiveClient.URL)
    public String archiveClient(@PathVariable String id,
                              RedirectAttributes redirectAttributes) {
        log.info(String.format("GET %s", StringUtils.replace(ArchiveClient.URL, PATTERN_ID, id)));

        try {
            // Archive client
            clientService.archiveClient(Long.parseLong(id));
        } catch (Exception exception) {
            log.error(exception.getMessage() + exception.getCause());
            redirectAttributes.addFlashAttribute(MESSAGE, ArchiveClient.ERROR_MESSAGE);
        }

        return REDIRECT_CLIENTS;
    }

    /**
     * Afficher le formulaire de recherche d'un client
     * @param springModel Modèle
     * @return le formulaire de modification d'un client
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_CLIENT.label)")
    @GetMapping(SearchClient.URL)
    public String showSearchClients(Model springModel) {
        log.info(String.format("GET %s", SearchClient.URL));
        springModel.addAttribute(TITLE, SearchClient.TITLE);

        Client client = new Client();
        springModel.addAttribute(CLIENT, client);

        return SearchClient.VIEW;
    }

    /**
     * Afficher la liste des clients
     * @param client Client
     * @param springModel Modèle
     * @return la liste des clients correspondant aux paramètres de requête
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_CLIENT.label)")
    @PostMapping(SearchClient.URL)
    public String searchClient(@ModelAttribute(CLIENT) Client client,
                               Model springModel) {
        log.info(String.format("POST %s", SearchClient.URL));

        return showClients(springModel, client.getLastname(), client.getFirstname(), client.getEmail(), client.getZipcode());
    }
}
