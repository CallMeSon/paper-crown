package com.papercrown.desktop.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;

public class StatCard extends VBox {

    private static final PseudoClass ACCENT_GOLD = PseudoClass.getPseudoClass("gold");
    private static final PseudoClass ACCENT_RED = PseudoClass.getPseudoClass("red");
    private static final PseudoClass ACCENT_PURPLE = PseudoClass.getPseudoClass("purple");
    private static final PseudoClass ACCENT_BLUE = PseudoClass.getPseudoClass("blue");

    private final Label valueLabel;
    private final SimpleStringProperty value = new SimpleStringProperty("0");

    public StatCard(Ikon icon, String label, String accent) {
        getStyleClass().add("stat-card");
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);
        setPrefWidth(180);
        setMinWidth(140);

        FontIcon iconView = new FontIcon(icon);
        iconView.setIconSize(24);
        iconView.getStyleClass().add("stat-icon");

        valueLabel = new Label();
        valueLabel.getStyleClass().add("stat-value");
        valueLabel.textProperty().bind(value);

        Label nameLabel = new Label(label);
        nameLabel.getStyleClass().add("stat-label");

        getChildren().addAll(iconView, valueLabel, nameLabel);

        pseudoClassStateChanged(ACCENT_GOLD, "gold".equals(accent));
        pseudoClassStateChanged(ACCENT_RED, "red".equals(accent));
        pseudoClassStateChanged(ACCENT_PURPLE, "purple".equals(accent));
        pseudoClassStateChanged(ACCENT_BLUE, "blue".equals(accent));
    }

    public void setValue(String val) {
        value.set(val);
    }

    public void setValue(int val) {
        value.set(String.valueOf(val));
    }

    public void setValue(double val) {
        if (val == (long) val) {
            value.set(String.valueOf((long) val));
        } else {
            value.set(String.format("%.1f", val));
        }
    }
}
