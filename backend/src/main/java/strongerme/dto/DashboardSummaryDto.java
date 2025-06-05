package strongerme.dto;

import java.math.BigDecimal;

public class DashboardSummaryDto {
    private long totalWorkouts;
    private BigDecimal totalVolume;
    private int currentWeekCount;
    private int weeklyGoal;

    public DashboardSummaryDto() {}

    public DashboardSummaryDto(long totalWorkouts, BigDecimal totalVolume, int currentWeekCount, int weeklyGoal) {
        this.totalWorkouts = totalWorkouts;
        this.totalVolume = totalVolume;
        this.currentWeekCount = currentWeekCount;
        this.weeklyGoal = weeklyGoal;
    }

    public long getTotalWorkouts() {
        return totalWorkouts;
    }

    public void setTotalWorkouts(long totalWorkouts) {
        this.totalWorkouts = totalWorkouts;
    }

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    public int getCurrentWeekCount() {
        return currentWeekCount;
    }

    public void setCurrentWeekCount(int currentWeekCount) {
        this.currentWeekCount = currentWeekCount;
    }

    public int getWeeklyGoal() {
        return weeklyGoal;
    }

    public void setWeeklyGoal(int weeklyGoal) {
        this.weeklyGoal = weeklyGoal;
    }
}
