package strongerme.service;

import strongerme.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import strongerme.dto.DashboardSummaryDto;
import strongerme.model.User;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.*;


@Service
public class DashboardService {

    private final WorkoutRepository workoutRepository;

    public DashboardService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public DashboardSummaryDto getSummary(User user) {
        UUID userId = user.getId();
        LocalDateTime start = LocalDate.now().with(DayOfWeek.MONDAY).atStartOfDay();
        LocalDateTime end = start.plusDays(6).with(LocalTime.MAX);

        long totalWorkouts = workoutRepository.countByUserId(userId);
        BigDecimal totalVolume = workoutRepository.calculateTotalVolume(userId);
        int currentWeekCount = workoutRepository.countWorkoutsInWeek(userId, start, end);


        return new DashboardSummaryDto(totalWorkouts, totalVolume, currentWeekCount, user.getWeeklyGoal());
    }
}

