package ee.srini.webapp.controller;

import ee.srini.webapp.model.Client;
import ee.srini.webapp.repository.CountryRepository;
import ee.srini.webapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final CountryRepository countryRepository;

    @GetMapping
    public String clients(Model model, HttpSession session) {
        Long appUserId = getLoggedInUserId(session);
        if (appUserId == null) {
            log.info("User session expired");

            return "redirect:/login";
        }

        List<Client> clients = clientService.getAllClientsByAppUserId(appUserId);
        model.addAttribute("clients", clients);

        return "clients";
    }

    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable("id") Long id, Model model, HttpSession session) {
        Long appUserId = getLoggedInUserId(session);
        if (appUserId == null) {
            log.info("User session expired");

            return "redirect:/login";
        }

        Client client = clientService.getClientByIdAndAppUserId(id, appUserId);
        if (client == null) {
            log.error("Currently logged in user has no rights to view/edit client id: {}", id);

            return "redirect:/clients";
        }

        log.info("Editing client id: {}", id);
        model.addAttribute("client", client);
        model.addAttribute("countries", countryRepository.findAll());

        return "client-edit";
    }

    @PostMapping("/edit/{id}")
    public String editClientHandle(@PathVariable("id") Long id, @Valid Client client, BindingResult result, Model model, HttpSession session) {
        Long appUserId = getLoggedInUserId(session);
        if (appUserId == null) {
            log.info("User session expired");

            return "redirect:/login";
        }
        if (result.hasErrors()) {
            model.addAttribute("countries", countryRepository.findAll());
            return "client-edit";
        }

        clientService.updateClient(client, appUserId);

        model.addAttribute("clients", clientService.getAllClientsByAppUserId(appUserId));

        return "clients";
    }

    @GetMapping("/add")
    public String addClient(Model model, HttpSession session) {
        Long appUserId = getLoggedInUserId(session);
        if (appUserId == null) {
            return "redirect:/login";
        }

        model.addAttribute("client", new Client());
        model.addAttribute("countries", countryRepository.findAll());

        return "client-add";
    }

    @PostMapping("/add")
    public String addClientHandle(@Valid Client client, BindingResult result, Model model, HttpSession session) {
        Long appUserId = getLoggedInUserId(session);
        if (appUserId == null) {
            return "redirect:/login";
        }
        if (result.hasErrors()) {
            model.addAttribute("countries", countryRepository.findAll());
            return "client-add";
        }

        clientService.addClient(client, appUserId);

        model.addAttribute("clients", clientService.getAllClientsByAppUserId(appUserId));

        return "clients";
    }

    private Long getLoggedInUserId(HttpSession session) {
        return (Long) session.getAttribute("appUserId");
    }

}
