package FX;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Created by Андрей on 12.02.2016.
 */
public class MainController {
    @FXML
    private TextField stars;
    @FXML
    private TextField massRange;
    @FXML
    private TextField universeSize;
    @FXML
    private Slider distirtionSpace;
    @FXML
    private Canvas canva;
    @FXML
    private Pane pane;

    private Start mainClass;

    public MainController(){

    }

    @FXML
    private void initialize(){

    }

    public void setMainClass (Start mainClass){
        this.mainClass = mainClass;
    }
}
