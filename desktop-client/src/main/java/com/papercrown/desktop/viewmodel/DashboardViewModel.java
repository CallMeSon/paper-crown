package com.papercrown.desktop.viewmodel;

import com.papercrown.desktop.service.BackendClient;
import com.papercrown.shared.dto.AchievementDTO;
import com.papercrown.shared.dto.RunDTO;
import com.papercrown.shared.dto.StatsDTO;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;
import java.util.concurrent.Executors;

public class DashboardViewModel {

    private final BackendClient client;
    private final java.util.concurrent.ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "dashboard-vm");
        t.setDaemon(true);
        return t;
    });

    public final SimpleObjectProperty<StatsDTO> stats = new SimpleObjectProperty<>();
    public final SimpleObjectProperty<List<RunDTO>> recentRuns = new SimpleObjectProperty<>(List.of());
    public final SimpleObjectProperty<List<AchievementDTO>> achievements = new SimpleObjectProperty<>(List.of());
    public final SimpleObjectProperty<RunDTO> unfinishedRun = new SimpleObjectProperty<>();

    public DashboardViewModel(BackendClient client) {
        this.client = client;
    }

    public void load() {
        executor.execute(() -> {
            try { Platform.runLater(() -> stats.set(client.getStats())); } catch (Exception ignored) {}
        });
        executor.execute(() -> {
            try {
                List<RunDTO> runs = client.getAllRuns();
                List<RunDTO> recent = runs.size() > 5 ? runs.subList(0, 5) : runs;
                Platform.runLater(() -> recentRuns.set(recent));
            } catch (Exception ignored) {}
        });
        executor.execute(() -> {
            try {
                List<AchievementDTO> all = client.getAchievements();
                List<AchievementDTO> unlocked = all.stream()
                        .filter(AchievementDTO::isUnlocked)
                        .toList();
                Platform.runLater(() -> achievements.set(unlocked));
            } catch (Exception ignored) {}
        });
        executor.execute(() -> {
            try {
                RunDTO run = client.getUnfinishedRun();
                Platform.runLater(() -> unfinishedRun.set(run));
            } catch (Exception ignored) {}
        });
    }

    public void startNewRun(java.util.function.Consumer<Long> onRunCreated) {
        executor.execute(() -> {
            try {
                RunDTO run = client.startRun();
                Platform.runLater(() -> onRunCreated.accept(run.getId()));
            } catch (Exception e) {
                Platform.runLater(() -> onRunCreated.accept(null));
            }
        });
    }
}
