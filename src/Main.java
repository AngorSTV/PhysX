import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public List<Star> stars = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShow();
            }
        });
    }

    private static void createAndShow(){
        JFrame frame = new JFrame("PhysX Stars");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
