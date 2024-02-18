import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MenuItem extends Pane {

    private Text text;

    private Effect shadow = new DropShadow(10 , Color.BLACK);
    private Effect blur = new BoxBlur(1, 1, 3);

    public MenuItem(String name) {
        Polygon bg = new Polygon(
                0, 0,
                270, 0,
                290, 30,
                270, 60,
                0, 60
        );
        bg.setStroke(Color.color(1,1,1,0.75));
        bg.setEffect(new GaussianBlur());

        bg.fillProperty().bind(
                Bindings.when(pressedProperty())
                        .then(Color.color(0,0,0,0.75))
                        .otherwise(Color.color(0.5, 0.3,0.25))
        );

        text = new Text(name);
        text.setTranslateX(10);
        text.setTranslateY(35);
        text.setFont(Font.loadFont(Objects.requireNonNull(Menu.class.getResource("PenumbraHalfSerifStd-Reg.ttf")).toExternalForm(), 22));
        text.setFill(Color.WHITE);

        text.effectProperty().bind(
                Bindings.when(hoverProperty())
                        .then(shadow)
                        .otherwise(blur)
        );

        getChildren().addAll(bg, text);
    }

    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }

}
