package com.papercrown.desktop.viewmodel;

import com.papercrown.desktop.service.BackendClient;
import com.papercrown.shared.dto.RunDTO;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

import java.util.List;
import java.util.concurrent.Executors;

public class HistoryViewModel {

    private final BackendClient client;
    private final java.util.concurrent.ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "history-vm");
        t.setDaemon(true);
        return t;
    });

    public final SimpleObjectProperty<List<RunDTO>> runs = new SimpleObjectProperty<>(List.of());
    public final SimpleObjectProperty<List<RunDTO>> expandedRuns = new SimpleObjectProperty<>(List.of());

    public HistoryViewModel(BackendClient client) {
        this.client = client;
    }

    public void load() {
        executor.execute(() -> {
            try {
                List<RunDTO> all = client.getAllRuns();
                Platform.runLater(() -> runs.set(all));
            } catch (Exception ignored) {}
        });
    }

    public void toggleExpand(RunDTO run) {
        List<RunDTO> expanded = new java.util.ArrayList<>(expandedRuns.get());
        if (expanded.contains(run)) {
            expanded.remove(run);
        } else {
            expanded.add(run);
        }
        expandedRuns.set(expanded);
    }

    public boolean isExpanded(RunDTO run) {
        return expandedRuns.get().contains(run);
    }
}
