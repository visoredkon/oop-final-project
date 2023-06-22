package SIAKAD;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Mengatur ikon window
        primaryStage
                .getIcons()
                .add(new Image(App.class.getResourceAsStream("resources/logoSIAKAD.png")));

        // Mengatur tampilan Login sebagai tampilan awal
        Parent root = FXMLLoader.load(getClass().getResource("views/LoginView.fxml"));

        // Mengatur judul window
        primaryStage.setTitle("Login");

        // Membuat dan menampilkan window
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    // Jalankan aplikasi
    public static void main(String[] args) {
        launch(args);
    }
}
