package com.papercrown.desktop.viewmodel;

import com.papercrown.desktop.service.BackendClient;
import com.papercrown.shared.dto.AchievementDTO;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;
import java.util.concurrent.Executors;

public class AchievementsViewModel {

    private final BackendClient client;
    private final java.util.concurrent.ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "achievements-vm");
        t.setDaemon(true);
        return t;
    });

    public final SimpleObjectProperty<List<AchievementDTO>> achievements = new SimpleObjectProperty<>(List.of());

    public AchievementsViewModel(BackendClient client) {
        this.client = client;
    }

    public void load() {
        executor.execute(() -> {
            try {
                List<AchievementDTO> all = client.getAchievements();
                Platform.runLater(() -> achievements.set(all));
            } catch (Exception ignored) {}
        });
    }
}
