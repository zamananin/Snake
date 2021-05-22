import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class RootBorderPane {
    AnchorPane center;
    AnchorPane top;

    public RootBorderPane(Canvas canvas){
        top = setTop();
        center = setCenter(canvas);
    }

    private AnchorPane setTop(){
        Label title = new Label("SNAKE");
        AnchorPane top  = new AnchorPane(title);
        AnchorPane.setTopAnchor(title, 10.0);
        AnchorPane.setLeftAnchor(title, 20.0);
        AnchorPane.setRightAnchor(title, 20.0);
        AnchorPane.setBottomAnchor(title, 5.0);
        return top;
    }

    private AnchorPane setCenter(Canvas canvas){
        AnchorPane center = new AnchorPane(canvas);
        AnchorPane.setTopAnchor(canvas, 15.0);
        AnchorPane.setLeftAnchor(canvas, 20.0);
        AnchorPane.setRightAnchor(canvas, 20.0);
        AnchorPane.setBottomAnchor(canvas, 10.0);
        return center;
    }

    public BorderPane toBorderPain() {
        BorderPane bp = new BorderPane();
        bp.setTop(top);
        bp.setCenter(center);
        return bp;
    }
}
