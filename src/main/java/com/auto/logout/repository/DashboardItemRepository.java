package com.auto.logout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.auto.logout.model.DashboardItem;
import com.auto.logout.model.User;

import java.util.List;

public interface DashboardItemRepository extends JpaRepository<DashboardItem, Long> {
    // Custom query method to find dashboard items by user
    List<DashboardItem> findByUser(User user);

    // Add more custom query methods as needed for your application
}
