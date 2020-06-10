/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import entities.Users;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.BCrypt;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private JFXButton signupBoutton;
    @FXML
    private JFXButton loginBoutton;
    @FXML
    private JFXCheckBox rememberme;
    @FXML
    private JFXButton forgetpasswordB;
    @FXML
    private Label labelElite;
    @FXML
    private JFXTextField txtEmailRecover;
    @FXML
    private JFXButton btnsendrecover;
    @FXML
    private Label txtetat;
    @FXML
    private JFXButton facebookButton;
    public static Users userConnected;
    @FXML
    private Label txtET;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtEmailRecover.setVisible(false);
        btnsendrecover.setVisible(false);

        //signupBoutton.setOnAction(e->{
        //  try {
        //    Parent root;
        //  root = FXMLLoader.load(getClass().getResource("gui/Registration.fxml"));
        //signupBoutton.getScene().setRoot(root);
        //} catch (IOException ex) {
        //  Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        //} 
        // }); 
    }

    @FXML

    public void login(ActionEvent e1) throws SQLException, IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/gui/home.fxml"));

        if (validateInputs()) {
            UserService us = new UserService();
            String pseudo = txtusername.getText();
            String password = txtpassword.getText();

            userConnected = us.searchByPseudoPass(pseudo, password);
            System.out.println(userConnected);
            if (userConnected != null && userConnected.getEnabled() == 1 && BCrypt.checkpw(txtpassword.getText(), userConnected.getPassword())) {
                //.user = u;
                //.user_id = u.getId();
                /*System.out.println("sooooo nice");*/
                txtetat.setText("");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Authentifié");
                alert.setContentText("Vous êtes connecté en tant que :" + userConnected.getUsername());

                alert.showAndWait();

                root = getRole(userConnected);

                String tit = "Authentification réussite";
                String message = "Bienvenue a Elite Jardin d'enfant";
                NotificationType notification = NotificationType.SUCCESS;

                TrayNotification tray = new TrayNotification(tit, message, notification);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(javafx.util.Duration.seconds(2));

                txtpassword.getScene().setRoot(root);

            } else if (userConnected != null && userConnected.getEnabled() == 0) {

                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("echec");
                alert1.setHeaderText("oupss");
                alert1.setContentText("Votre compte est desactivé.");

                alert1.showAndWait();

            } else {

                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("echec");
                alert2.setHeaderText("oupss");
                alert2.setContentText("verifier info.");

                alert2.showAndWait();
                txtetat.setText("Identifiants incorrects.");
            }

        }

        System.out.println(userConnected.getId());

    }

    public AnchorPane getRole(Users user) throws IOException {
        AnchorPane root;
        //ObservableList<String> RoleList = FXCollections.observableArrayList("ROLE_USER", "ROLE_ADMIN");
        if (user.getRoles().contains("ROLE_ADMIN")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gesenfant/Views/back.fxml"));
            root = (AnchorPane) loader.load();
//            primaryStage.setMinHeight(1158); 
//            primaryStage.setMinWidth(904); 
     
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gesenfant/Views/front.fxml"));
            root = (AnchorPane) loader.load();
            Integer a = userConnected.getId();

            gesenfant.Views.FrontController ajout = loader.getController();
            ajout.myFunction2(a.toString());

        }
        return root;
    }

    @FXML
    public void signUp(ActionEvent e) throws IOException {
        loginBoutton.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Registration.fxml"));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();

    }

    private boolean validateInputs() throws SQLException {

        if (txtusername.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez saisir votre nom d'utilisateur");
            alert1.setHeaderText("Controle de saisie");
            alert1.show();
            return false;
        }
        if (txtpassword.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez saisir votre mot de passe");
            alert1.setHeaderText("Controle de saisie");
            alert1.show();
            return false;
        }
        return true;
    }

    @FXML
    private void MissedPassword(ActionEvent event) {
        txtEmailRecover.setVisible(true);
        btnsendrecover.setVisible(true);

    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    @FXML
    private void Recover(ActionEvent event) throws SQLException {
        if (!txtEmailRecover.getText().isEmpty() && txtEmailRecover.getText().contains("@")) {
            UserService usr = new UserService();

            Users user = new Users();
            user = usr.getUserByEmail(txtEmailRecover.getText());
            if (user != null) {
                String plainpassword = getSaltString();
                String password = BCrypt.hashpw(plainpassword, BCrypt.gensalt());
                System.out.println("Le nouveau mot de passe de " + user.getEmail() + "est " + plainpassword);

                usr.changePassword(password, txtEmailRecover.getText());
                sendEmail(user, plainpassword);
                txtEmailRecover.setVisible(false);
                btnsendrecover.setVisible(false);
                txtET.setText("Mot de passe envoyé par email.");

                String tit = "Nouveau mot de passe envoyé";
                String message = "Votre mot de passe est  changé , verifier votre mail";
                NotificationType notification = NotificationType.SUCCESS;

                TrayNotification tray = new TrayNotification(tit, message, notification);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(javafx.util.Duration.seconds(2));
            } else {
                txtET.setText("Utilisateur introuvable");
            }

        }

    }

    @FXML
    private void AuthFB(ActionEvent event) throws SQLException, IOException {
        try {
            FacebookConnect();
        } catch (ParseException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void closeApplication(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vous allez quitter l'application");
        alert.setHeaderText("Vous allez quitter l'application");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            alert.close();
        }
    }

    public void sendEmail(Users compte, String plainpassword) {
        try {
            String host = "smtp.gmail.com";
            String user = "rahmak2424@gmail.com";
            String pass = "24242106";
            String to = txtEmailRecover.getText();
            String from = "rahmak2424@gmail.com";
            String subject = "Elite Jardin d'enfant - Nouveau mot de passe";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};

            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            //msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText("Bonjour " + compte.getNom() + " " + compte.getPrenom() + ", votre nouveau mot de passe est : " + plainpassword);
            multipart.addBodyPart(messageBodyPart);

            msg.setContent(multipart);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Email envoyé");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void FacebookConnect() throws SQLException, IOException, ParseException {

        String domain = "https://localhost";
        String appId = "344309509416204";
        String appSecret = "ce2ad147b8ac69cb16df8c90c8c62990";

        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=public_profile,email";

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);

        System.out.println(driver.getCurrentUrl());
        String accessToken;

        boolean b = true;
        while (b) {
            if (!driver.getCurrentUrl().contains("facebook.com")) {

                String url = driver.getCurrentUrl();
                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
                System.out.println("test");

                driver.quit();
                b = false;
                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                String fields = "first_name,last_name,email";
                User user = fbClient.fetchObject("me", User.class, Parameter.with("fields", fields));
                System.out.println(user.getName());
                System.out.println(user.toString());

                UserService usr = new UserService();

                if (usr.getUserByEmail(user.getEmail()) != null) {
                    Users u = usr.getUserByEmail(user.getEmail());
                    userConnected = u;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Home.fxml"));
                    AnchorPane root = (AnchorPane) loader.load();
                    loginBoutton.getScene().setRoot(root);

                } else {
                    Users u = new Users(user.getFirstName(), user.getLastName(), user.getEmail());
                    userConnected = u;
                    usr.AddFbUser(u);
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Home.fxml"));
                AnchorPane root = (AnchorPane) loader.load();
                loginBoutton.getScene().setRoot(root);

            }

        }
    }
}

/* 
      else if (isNotInteger(Phone.getText())) {

            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("erreur dans le champ rang veiller mettre un numero");
            alert1.setHeaderText(null);
            alert1.show();
            return false;

        } 

        return true;

    }
    
    public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }
 */
