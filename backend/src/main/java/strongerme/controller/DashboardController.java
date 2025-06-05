package strongerme.controller;

import strongerme.service.DashboardService;
import org.springframework.web.bind.annotation.*;
import strongerme.dto.DashboardSummaryDto;
import strongerme.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;



@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardSummaryDto getDashboardSummary(@AuthenticationPrincipal User user) {
        return dashboardService.getSummary(user);
    }
}
