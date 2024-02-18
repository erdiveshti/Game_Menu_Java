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
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import org.w3c.dom.css.Rect;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Menu extends Application {

    private static final int Width = 1200;
    private static final int Height = 720;

    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Luaj me nje shok", () -> {}),
            new Pair<String, Runnable>("Luaj me kompjuterin", () -> {}),
            new Pair<String, Runnable>("Dil", Platform::exit)
    );

    private Pane root = new Pane();
    private VBox menuBox = new VBox(-5);
    private Line line;

    private Parent createContent() {
        addBackground();
        addTitle();

        double lineX = Width / 2 - 100;
        double lineY = Height / 3 + 50;

        addLine(lineX, lineY);
        addMenu(lineX -40, lineY +5);

        startAnimation();

        return root;
    }
    private void addBackground() {
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("Desktop - 1.png")).toExternalForm()));
        imageView.setFitWidth(Width);
        imageView.setFitHeight(Height);

        root.getChildren().add(imageView);
    }

    private void addTitle() {
        Title title = new Title("Nentsh");
        title.setTranslateX(Width / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(Height / 3);

        root.getChildren().add(title);
    }

    private void addLine(double x, double y) {
        line = new Line(x-50, y, x-50, y +225);
        line.setStrokeWidth(5);
        line.setStroke(Color.color(1,1,1,0.75));
        line.setEffect(new DropShadow(10, Color.BLACK));
        line.setScaleY(0);

        root.getChildren().add(line);
    }

    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setToY(1);
        st.setOnFinished(e -> {
            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            MenuItem item = new MenuItem(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(400 , 60);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });

        root.getChildren().add(menuBox);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Nentsh");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
