package com.papercrown.desktop.view;

import com.papercrown.desktop.component.RunCard;
import com.papercrown.desktop.service.BackendClient;
import com.papercrown.desktop.viewmodel.HistoryViewModel;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class HistoryView extends VBox {

    private final HistoryViewModel vm;
    private VBox runList;
    private Label emptyLabel;

    public HistoryView(BackendClient client) {
        this.vm = new HistoryViewModel(client);

        getStyleClass().add("page-view");
        setPadding(new Insets(32));
        setSpacing(16);

        Label title = new Label("History");
        title.getStyleClass().add("page-title");

        emptyLabel = new Label("No completed runs yet.");
        emptyLabel.getStyleClass().add("empty-state");

        runList = new VBox(8);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        VBox content = new VBox(16);
        content.getChildren().addAll(title, emptyLabel, runList);
        scrollPane.setContent(content);

        getChildren().add(scrollPane);

        vm.runs.addListener((obs, old, val) -> {
            Platform.runLater(() -> {
                runList.getChildren().clear();
                boolean empty = val == null || val.isEmpty();
                emptyLabel.setVisible(empty);
                emptyLabel.setManaged(empty);
                if (val != null) {
                    for (var run : val) {
                        runList.getChildren().add(new RunCard(run));
                    }
                }
            });
        });

        vm.load();
    }
}
