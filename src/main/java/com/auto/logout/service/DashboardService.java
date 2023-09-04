package com.auto.logout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auto.logout.model.User;
import com.auto.logout.model.DashboardItem;
import com.auto.logout.repository.DashboardItemRepository;

import java.util.List;

@Service
public class DashboardService {
    private final DashboardItemRepository dashboardItemRepository;

    @Autowired
    public DashboardService(DashboardItemRepository dashboardItemRepository) {
        this.dashboardItemRepository = dashboardItemRepository;
    }

    public List<DashboardItem> fetchDashboardData(User user) {
        // Implement logic to retrieve and return dashboard data
        // You can fetch data from your database or any other source
        // For example, fetching data from a repository:

        // Assuming you have a DashboardItem entity and repository
        // You can customize this query based on your data model
        List<DashboardItem> dashboardData = dashboardItemRepository.findByUser(user);

        return dashboardData;
    }
    
    public DashboardItem saveDashboardItem(DashboardItem newItem) {
        // Save the new item to the database
        return dashboardItemRepository.save(newItem);
    }
}
