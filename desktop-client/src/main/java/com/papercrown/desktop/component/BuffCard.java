package com.papercrown.desktop.component;

import com.papercrown.shared.dto.BuffDTO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class BuffCard extends VBox {

    private boolean selected = false;

    public BuffCard(BuffDTO dto) {
        getStyleClass().add("buff-card");
        setPadding(new Insets(16));
        setSpacing(8);
        setAlignment(Pos.CENTER);
        setPrefWidth(160);
        setMinWidth(120);

        String iconStr = dto.getIcon() != null ? dto.getIcon() : "fas-gem";
        FontIcon icon = new FontIcon(iconStr.startsWith("fas-") ? resolveIcon(iconStr) : FontAwesomeSolid.GEM);
        icon.setIconSize(24);
        icon.getStyleClass().add("buff-icon");

        Label nameLabel = new Label(dto.getName());
        nameLabel.getStyleClass().add("buff-name");

        Label descLabel = new Label(dto.getDescription());
        descLabel.getStyleClass().add("buff-desc");
        descLabel.setWrapText(true);

        getChildren().addAll(icon, nameLabel, descLabel);
    }

    public void setSelected(boolean sel) {
        this.selected = sel;
        if (sel) {
            getStyleClass().add("buff-card-selected");
        } else {
            getStyleClass().remove("buff-card-selected");
        }
    }

    public boolean isSelected() {
        return selected;
    }

    private static FontAwesomeSolid resolveIcon(String iconStr) {
        return switch (iconStr) {
            case "fas-shield-alt" -> FontAwesomeSolid.SHIELD_ALT;
            case "fas-heart" -> FontAwesomeSolid.HEART;
            case "fas-heartbeat" -> FontAwesomeSolid.HEARTBEAT;
            case "fas-fire" -> FontAwesomeSolid.FIRE;
            case "fas-sync-alt" -> FontAwesomeSolid.SYNC_ALT;
            case "fas-gem" -> FontAwesomeSolid.GEM;
            case "fas-star" -> FontAwesomeSolid.STAR;
            default -> FontAwesomeSolid.GEM;
        };
    }
}
