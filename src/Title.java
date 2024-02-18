import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Title extends Pane{

    private Text text;

    public Title (String name) {
        String spread = "";
        for(char c :name.toCharArray()){
            spread += c +" ";
        }
        text = new Text(spread);
        text.setFont(Font.loadFont(Objects.requireNonNull(Menu.class.getResource("PenumbraHalfSerifStd-Reg.ttf")).toExternalForm(),68));
        text.setFill(Color.WHITE);
        text.setEffect(new DropShadow(30 ,Color.BLACK));

        getChildren().addAll(text);
    }

    public double getTitleWidth() { return text.getLayoutBounds().getWidth(); }
    public double getTitleHeight() { return text.getLayoutBounds().getHeight(); }

}
