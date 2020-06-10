
import entities.Users;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import services.UserService;
import controllers.LoginController;
import controllers.RegistrationController;
import entities.Chauffeur;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import services.ChauffeurService;
import services.MsgServices;

public class Elite extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("gui/Login.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Welcome To Elite ");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        ChauffeurService sc = new ChauffeurService();

        //Chauffeur c = new Chauffeur("adrian","bomaye","21564755","adrian@esprit.tn","545","2020-02-01","49");
        //      sc.ajouterChauffeur(c);
        //sc.SupprimerChauffeur(8);
        launch(args);
    }

}
