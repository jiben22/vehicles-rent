package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class DashboardController {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public String showDashboard(Model model, HttpServletRequest request, HttpSession session) {
        model.addAttribute("title", "Vue d'ensemble");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            session.setAttribute("employee", employeeService.getEmployeeByEmail(auth.getName()));
        }

        return "dashboard";
    }
}
