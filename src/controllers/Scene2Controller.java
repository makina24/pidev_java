/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author Genuine Coder
 */
public class Scene2Controller implements Initializable {

    @FXML
    private TextField textfield;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void transferMessage(String message) {
        textfield.setText(message);
    }
}