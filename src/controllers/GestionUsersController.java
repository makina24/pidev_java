/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Users;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import services.UserService;
import utils.BCrypt;
import utils.Connexion;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class GestionUsersController implements Initializable {
    
            ObservableList<String> RoleList = FXCollections.observableArrayList("ROLE_USER", "ROLE_ADMIN");


    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtPassword;
    @FXML
    private ComboBox<String> rolecombo;
    @FXML
    private JFXButton ajoutBoutton;
    @FXML
    private TableColumn<Users,String> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> userName;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> password;
    @FXML
    private TableColumn<?, ?> role;
    @FXML
    private JFXButton modifierBoutton;
    @FXML
    private JFXButton logoutBoutton;
    @FXML
    private JFXButton supprimerBoutton;
    @FXML
    private JFXButton actualiserBoutton;
    @FXML
    private JFXButton resetBoutton;
    @FXML
    private TableView<Users> tableusers;
    List<Users> listUsers;
    ObservableList<Users> listViewUsers;
        private Users selectedid;
        
       
    @FXML
    private JFXButton btnActiver;
    @FXML
    private JFXButton btnDesactiver;
    @FXML
    private JFXButton saveU;
    @FXML
    private JFXButton retourBoutton;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXButton homeBoutton;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        UserService us = new UserService();
        
        
                 
             
      
            rolecombo.setItems(RoleList);

        btnActiver.setVisible(false);
        btnDesactiver.setVisible(false);
        
        tableusers.setOnMouseClicked((event) -> {
          int  a=tableusers.getSelectionModel().getSelectedItem().getEnabled();
          Users b = tableusers.getSelectionModel().getSelectedItem();
          int c = tableusers.getSelectionModel().getSelectedItem().getId();
          System.out.println(c);
                try {
                    System.out.println(getUserById(c));
                } catch (SQLException ex) {
                    Logger.getLogger(GestionUsersController.class.getName()).log(Level.SEVERE, null, ex);
                }

              
        
                try {
                    if (  getUserById(c).getEnabled()==0  )
                    {
                        btnActiver.setVisible(true);
                        btnDesactiver.setVisible(false);
                    }
                    else if (getUserById(c).getEnabled() == 1){
                        {
                            btnActiver.setVisible(false);
                            btnDesactiver.setVisible(true);
                            
                        }
                    }} catch (SQLException ex) {
                    Logger.getLogger(GestionUsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
});
        
         try {
            initCol();
            loadData();
        } catch (Exception e) {
        }
         
                 FilteredList<Users> filteredData = new FilteredList<>(listViewUsers, e -> true);

       
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(a -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();

                if (a.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else  if (a.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true ; 
                }else if  (a.getUsername().toLowerCase().contains(lowerCaseFilter)){
                    return true ;
                }else if (a.getEmail().toLowerCase().contains(lowerCaseFilter)){
                    return true ;
                    
                }else if (a.getRoles().toLowerCase().contains(lowerCaseFilter)){
                    return true ;
                }else
                return false; 
            });
        });

         
        SortedList<Users> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableusers.comparatorProperty());
        tableusers.setItems(sortedData);
  
        
    } 
          
       
    
                    


 private void initCol() {
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        userName.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        role.setCellValueFactory(new PropertyValueFactory<>("roles"));
        

    }  
 
  private void loadData() {

        /*  try{
        AdressesService adresse = new AdressesService();
        ObservableList<Document> data = FXCollections.observableArrayList(adresse.listeAdresses(1));
        //tableView.setItems(data);
        tableView.getItems().setAll(data);
        list = data;
        }catch(Exception e){
        Logger.getLogger(DocumentsServices.class.getName()).log(Level.SEVERE, null, e);
        }*/
        try {
            UserService sr = new UserService();
            listUsers = new ArrayList<>();
            listUsers = sr.getAllUsers();
            System.err.println(listUsers);
            listViewUsers = FXCollections.observableArrayList(listUsers);
            tableusers.setItems(listViewUsers);
            initCol();
        } catch (Exception e) {
        }

    }
  
   
    private boolean validateInputs() {
        if ((txtUserName.getText().isEmpty()) || (txtNom.getText().isEmpty())
                || (txtPrenom.getText().isEmpty()) || (txtEmail.getText().isEmpty())
                || (txtPassword.getText().isEmpty())
                 ) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez remplir tout les champs");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        
        } else if (!(validate(txtEmail.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } 
        return true;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    
    
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }

        return true;
    }
    

    @FXML
    private void AjouterUser(ActionEvent event) {
        
        UserService sr = new UserService();
        //String image = "";
     

        //if (radioHomme.isSelected()) {
           // valueRadio = "Homme";
       // } else if (radioFemme.isSelected()) {
         //   valueRadio = "Femme";
        //}

        if (validateInputs()) {
            

            

           
            Users u= new Users(txtUserName.getText(), txtEmail.getText(), txtPassword.getText(), rolecombo.getValue().toString(), txtNom.getText(), txtPrenom.getText());
            UserService us = new UserService();
               txtUserName.setText("");
          
            try{
            sr.ajouterUser(u);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.setContentText("Operation effectuée avec succée !");
            alert.show();
          
          
            }catch (SQLException ex){
                             Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);

            }
       

           
        
      

    }

}
 
      

    @FXML
    private void modifierUser(ActionEvent event) throws IOException, SQLException {
        
       
        if (tableusers.getSelectionModel().getSelectedItem() == null) {

            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Vous devez selectionner l'évenement à modifier");
            alert1.setHeaderText(null);
            alert1.show();
        } else {
             txtNom.setText(tableusers.getSelectionModel().getSelectedItem().getNom());
       txtPrenom.setText(tableusers.getSelectionModel().getSelectedItem().getPrenom());
       txtEmail.setText(tableusers.getSelectionModel().getSelectedItem().getEmail());
       txtPassword.setText(tableusers.getSelectionModel().getSelectedItem().getPassword());

      
       txtUserName.setText(tableusers.getSelectionModel().getSelectedItem().getUsername());
       rolecombo.setValue(tableusers.getSelectionModel().getSelectedItem().getRoles());
            
           
        
        }
        
        
    }

    @FXML
    private void logout(ActionEvent event) {
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
       
        
          
    

        


    @FXML
    private void supprimerUser(ActionEvent event ) throws SQLException {
        
if (tableusers.getSelectionModel().getSelectedItem()==null){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Vous devez selectionner l'utilisateur à supprimer");
            alert1.setHeaderText(null);
            alert1.show();
        } else {
        
          ButtonType ok = new ButtonType("oui",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("non", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,"Voulez vous vraiment supprimer cet user? ",ok,cancel);

        alert.setTitle("Suppresion");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get()== ok) {  
        UserService us = new UserService();
            ObservableList<Users> EventSelected,allEvents;
               allEvents = tableusers.getItems();

            EventSelected = tableusers.getSelectionModel().getSelectedItems();
                       us.SupprimerUser(EventSelected.get(0).getId());
                        EventSelected.forEach(listViewUsers::removeAll);

                }

        }

        
    }   
    

    @FXML
    private void actualiser(ActionEvent event) {
         this.listViewUsers.clear();
        loadData();
    }

    @FXML
    private void reset(ActionEvent event) {
        
         txtNom.setText("");
             txtPrenom.setText("");
                txtPassword.setText("");
                   txtUserName.setText("");
                      txtEmail.setText("");
          
    }

    @FXML
    private void ActiverUser(ActionEvent event) throws IOException {
      
          UserService us= new UserService();
         
        try {
            us.UserSetEnable(tableusers.getSelectionModel().getSelectedItem().getId());
        } catch (SQLException ex) {
            Logger.getLogger(GestionUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GestionUsers.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
        tableusers.getScene().setRoot(root);

    }
    
    @FXML
    private void DesactiverUser(ActionEvent event) throws SQLException, IOException {
        
         
        UserService us= new UserService();
        
       
            us.UserSetDisable(tableusers.getSelectionModel().getSelectedItem().getId());
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GestionUsers.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
        tableusers.getScene().setRoot(root);
    }

    @FXML
    private void saveUpdates(ActionEvent event) throws SQLException {
    
             if (validateInputs()) {
                 UserService sr = new UserService();

                Users user=new Users(txtUserName.getText(), txtEmail.getText(), BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt()),  rolecombo.getValue(), txtNom.getText(), txtPrenom.getText());
                sr.ModifierClient(user, tableusers.getSelectionModel().getSelectedItem().getId());
        
       
                 
             }
         
      

       
            
            
        }
        
        
    
    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
            retourBoutton.getScene().getWindow().hide();
        Stage rb = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gui/BackAdmin.fxml"));
        Scene scene = new Scene(root);
        rb.setScene(scene);
        rb.show();
        rb.setResizable(false);
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        
         homeBoutton.getScene().getWindow().hide();
        Stage signup = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }
    
       
    public Users getUserById(int id) throws SQLException {
          Connection con = Connexion.getInstance().getConnection();

        Users u = null;
        String req = "SELECT * FROM `fos_user` WHERE id = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            u = new Users();

            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setRoles(rs.getString("roles"));
            u.setNom(rs.getString("nom"));
           
            u.setPrenom(rs.getString("prenom"));
          
            u.setPassword(rs.getString("password"));
            u.setEnabled(rs.getInt("enabled"));

            System.out.println("Utilisateur trouvé !");
        }
        return u;
    }

    
    
}
