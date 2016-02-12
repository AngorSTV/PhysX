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
    Universe universe;
    @FXML
    private TextField stars;
    @FXML
    private TextField massRange;
    @FXML
    private TextField universeSize;
    @FXML
    private Slider distortionSpace;
    @FXML
    private Canvas canva;
    @FXML
    private Pane pane;

    private Start mainClass;

    public MainController(){

    }

    @FXML
    private void initialize(){

        //stars.setText(String.valueOf(universe.getStarsQuantity()));
    }

    public void setMainClass (Start mainClass){
        this.mainClass = mainClass;
        universe = mainClass.getUniverse();
        stars.setText(String.valueOf(universe.getStarsQuantity()));
    }
}
