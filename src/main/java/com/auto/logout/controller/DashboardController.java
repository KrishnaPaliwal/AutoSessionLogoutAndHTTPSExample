package com.auto.logout.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.auto.logout.model.DashboardItem;
import com.auto.logout.model.User;
import com.auto.logout.service.DashboardService;
import com.auto.logout.service.UserService;

@Controller
public class DashboardController {
    private final DashboardService dashboardService;
    private final UserService userService; // Ensure this field is annotated

    @Autowired
    public DashboardController(DashboardService dashboardService, UserService userService) {
        this.dashboardService = dashboardService;
        this.userService = userService; // Make sure to inject userService
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        // Fetch the currently logged-in user
        User user = userService.loadUserByUsername(principal.getName());

        // Fetch dashboard data
        List<DashboardItem> dashboardData = dashboardService.fetchDashboardData(user);

        // Add the dashboard data to the model
        model.addAttribute("dashboardData", dashboardData);
        model.addAttribute("userName", principal.getName());

        return "dashboard";
    }
    
    @PostMapping("/add-item")
    public String addItemToDashboard( @RequestParam String title, @RequestParam String description, Principal principal) {
    	
        String username = principal.getName();
        User user = userService.loadUserByUsername(username);

        // Create a new dashboard item
        DashboardItem newItem = new DashboardItem();
        newItem.setTitle(title);
        newItem.setDescription(description);
        newItem.setUser(user);

        // Save the new item to the database
        dashboardService.saveDashboardItem(newItem);

        // Redirect back to the dashboard
        return "redirect:/dashboard";
    }

}
