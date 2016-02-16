package FX;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.controlsfx.control.StatusBar;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Андрей on 12.02.2016.
 */
public class MainController {
    private Universe universe;
    private List<Star> stars;
    private int maxThreads;
    private long t;
    private double fps;
    private double totalMass = 0;
    private static double totalFrame = 0;
    private Thread th[];
    private boolean isRun = false;

    @FXML
    private TextField starsQuantity;
    @FXML
    private TextField massRange;
    @FXML
    private TextField universeSize;
    @FXML
    private Slider distortionSpace;
    @FXML
    private Label distortionIndicator;
    DoubleProperty dsi = new SimpleDoubleProperty();
    @FXML
    private Canvas canvas;
    @FXML
    private Pane pane;

    private Start mainClass;

    public MainController(){

    }

    @FXML
    private void initialize(){
        dsi.bind(distortionSpace.valueProperty());
        distortionIndicator.textProperty().bind(dsi.asString("%.1f"));
        /*StatusBar statusBar = new StatusBar();
        statusBar.getLeftItems().add(new Button("Info"));
        statusBar.setProgress(.5);
        statusBar.setMaxWidth(600);
        pane.getChildren().addAll(statusBar);*/
    }

    public void setMainClass (Start mainClass){
        this.mainClass = mainClass;
        universe = mainClass.getUniverse();
        stars = universe.getStars();
        starsQuantity.setText(String.valueOf(universe.getStarsQuantity()));
        massRange.setText(String.valueOf(universe.getMassBand()));
        universeSize.setText(String.valueOf(universe.getSize()));
        distortionSpace.setValue(universe.getDistortionSpace());
    }

    @FXML
    private void hendleStart(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int processors = Runtime.getRuntime().availableProcessors();
        maxThreads = processors * 4;
        this.th = new Thread[maxThreads];

        //сбор параметров из ГУИ
        universe.setStarsQuantity(Integer.valueOf(starsQuantity.getText()));
        universe.setMassBand(Integer.valueOf(massRange.getText()));
        universe.setSize(Integer.valueOf(universeSize.getText()));
        universe.setDistortionSpace(distortionSpace.getValue());

        universe.create();
        stars = universe.getStars();

        // подсчёт общей массы
        for (Star star : stars) {
            totalMass = totalMass + star.m;
        }
        long t1;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };

        while (true) {

            t1 = System.currentTimeMillis();

            //Чистка масива от мёртвых объектов
            Iterator<Star> iStar = stars.iterator();
            while (iStar.hasNext()) {
                if (!iStar.next().isAlive) iStar.remove();
            }

            multiTwo();

            for (Star star2 : stars) {
                star2.Move();
            }

            try {
                fps = t;
                fps = 1000 / fps;
                long pause = 16 - t;
                if (pause < 0) pause = 0;
                if (pause > 16) pause =16;
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            paint(gc);
            t = System.currentTimeMillis() - t1;
        }

    }

    private void paint (GraphicsContext gc){
        System.out.println("paint" + universe.getStarsQuantity());
        double width = canvas.getWidth();
        double height = canvas.getHeight();
        double ratio = height/(universe.getSize()*2);

        int x, y, r;

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        gc.setFill(Color.WHITE);
        gc.setStroke(Color.WHITE);

        totalFrame++;

        double currentTotalMass = 0;
        for (Star star : stars) {
            currentTotalMass = currentTotalMass + star.m;
        }

        for (Star star : stars) {
            x = (int) (ratio * star.current.x);
            y = (int) (ratio * star.current.y);
            r = (int) (ratio * Math.sqrt(star.m) * 0.5);
            if (star.m < 5001) {
                gc.fillOval((x + width / 2) - r / 2, (y + height / 2) - r / 2, r, r);
            }
            gc.strokeOval((x + width / 2) - r / 2, (y + height / 2) - r / 2, r, r);
        }

        starsQuantity.setText(String.valueOf(universe.getStarsQuantity()));
        //massRange.setText(String.valueOf(universe.getMassBand()));
        //universeSize.setText(String.valueOf(universe.getSize()));
        //distortionSpace.setValue(universe.getDistortionSpace());
        //g.drawString("Total starsQuantity:" + starsQuantity.size(), 1, 15);
        //gc.drawString("Change mass: " + String.valueOf((int) (currentTotalMass -totalMass)), 1, 30);
        //g.drawString("Total frame:" + String.valueOf((int) totalFrame), 1, 45);
        //g.drawString("FPS: " + String.valueOf((int) fps), 1, 60);
        //g.drawString("Width: " + String.valueOf(width), 1, 75);
    }

    private void multiTwo() {
        int band = stars.size() / maxThreads;

        for (int i = 0; i < maxThreads; i++) {
            Domen domen = new Domen(stars.subList(i * band, i * band + band));
            th[i] = new Thread(domen);
            th[i].start();
        }

        for (int i = 0; i < maxThreads; i++) {
            try {
                th[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Domen domen = new Domen(stars.subList(band * maxThreads, stars.size()));
        th[0] = new Thread(domen);
        th[0].start();
        try {
            th[0].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void single(Star star) {
        try {
            Thread th = new Thread(star);
            th.run();
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class Domen implements Runnable {
        private List<Star> stars;

        public Domen(List<Star> stars) {
            this.stars = stars;
        }

        public void run() {
            for (Star star : stars) {
                single(star);
            }
        }
    }
}
