package com.papercrown.desktop.component;

import com.papercrown.shared.dto.RoundDTO;
import com.papercrown.shared.dto.RunDTO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.format.DateTimeFormatter;

public class RunCard extends VBox {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm");

    private final VBox detailArea;
    private boolean expanded = false;

    public RunCard(RunDTO run) {
        getStyleClass().add("run-card");
        setPadding(new Insets(16));
        setSpacing(8);

        HBox header = new HBox(16);
        header.setAlignment(Pos.CENTER_LEFT);
        header.getStyleClass().add("run-card-header");

        Label dateLabel = new Label(run.getCreatedAt() != null
                ? run.getCreatedAt().format(DATE_FMT) : "Unknown");
        dateLabel.getStyleClass().add("run-card-date");

        Label statsLabel = new Label("Rounds: " + run.getRoundNumber()
                + "  |  W: " + run.getTotalWins()
                + "  L: " + run.getTotalLosses()
                + "  D: " + run.getTotalDraws()
                + "  HP: " + run.getCurrentHp() + "/" + run.getMaxHp());
        statsLabel.getStyleClass().add("run-card-stats");

        Label expandIcon = new Label("▶");
        expandIcon.getStyleClass().add("run-card-expand");

        header.getChildren().addAll(dateLabel, statsLabel, expandIcon);

        detailArea = new VBox(4);
        detailArea.setPadding(new Insets(8, 0, 0, 0));
        detailArea.setVisible(false);
        detailArea.setManaged(false);
        detailArea.getStyleClass().add("run-card-detail");

        if (run.getRounds() != null && !run.getRounds().isEmpty()) {
            for (RoundDTO round : run.getRounds()) {
                detailArea.getChildren().add(createRoundRow(round));
            }
        } else {
            Label noDetail = new Label("Round details not available");
            noDetail.getStyleClass().add("empty-state");
            detailArea.getChildren().add(noDetail);
        }

        getChildren().addAll(header, detailArea);

        header.setOnMouseClicked(this::toggle);
    }

    private HBox createRoundRow(RoundDTO round) {
        HBox row = new HBox(12);
        row.setPadding(new Insets(4, 0, 4, 0));
        row.getStyleClass().add("run-card-round-row");

        Label roundNum = new Label("R" + round.getRoundNumber());
        roundNum.setPrefWidth(30);

        String outcomeIcon = switch (round.getOutcome()) {
            case WIN -> "✓";
            case LOSS -> "✗";
            case DRAW -> "—";
        };
        Label outcomeLabel = new Label(outcomeIcon);
        outcomeLabel.setPrefWidth(20);

        Label moves = new Label("You: " + round.getPlayerMove()
                + "  Bot: " + round.getBotMove());

        row.getChildren().addAll(roundNum, outcomeLabel, moves);
        return row;
    }

    private void toggle(MouseEvent event) {
        expanded = !expanded;
        detailArea.setVisible(expanded);
        detailArea.setManaged(expanded);
        Label expandIcon = (Label) ((HBox) getChildren().get(0)).getChildren().get(2);
        expandIcon.setText(expanded ? "▼" : "▶");
    }
}
