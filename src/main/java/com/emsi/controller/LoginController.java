
package com.emsi.controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginController extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        // Création des composants
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Mot de passe:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Se connecter");

        // Création de la grille et ajout des composants
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(10);

        grid.add(emailLabel, 0, 0);
        grid.add(emailField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        // Action du bouton de connexion
        loginButton.setOnAction(event -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            // Vérification des identifiants
            if (isValidCredentials(email, password)) {
                System.out.println("Connexion réussie !");
                // Ajoutez ici le code pour afficher la nouvelle fenêtre ou effectuer d'autres actions après la connexion réussie.
                primaryStage.close();
                GamesManagementApp gamesManagementApp = new GamesManagementApp() {
                    @Override
                    public void start(Stage primaryStage) throws Exception {

                    }
                };
                try {
                    gamesManagementApp.launchApp();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else {
                System.out.println("Identifiants invalides !");
                // Ajoutez ici le code pour afficher un message d'erreur ou effectuer d'autres actions en cas d'identifiants invalides.
            }
        });

        // Création de la scène et affichage de la fenêtre
        Scene scene = new Scene(grid);
                primaryStage.setScene(scene);
        primaryStage.show();
    }
        private boolean isValidCredentials(String email, String password) {
        // Ajoutez ici votre logique de validation des identifiants.
        // Par exemple, vous pouvez vérifier si l'e-mail et le mot de passe sont présents dans une base de données ou si correspondent à une paire valide.

        // Pour cet exemple, nous considérons que les identifiants suivants sont valides.
        String validEmail = "test@example.com";
        String validPassword = "1234";

        return email.equals(validEmail) && password.equals(validPassword);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
package com.emsi.controller;

import com.emsi.App;
import com.emsi.entities.User;
import com.emsi.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    UserService userService = new UserService();
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button auth;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        auth.setOnMouseClicked(arg->{
            if ( auth(constructionUser())){
                try {

                    Stage stage1 = (Stage) auth.getScene().getWindow();
                    stage1.close();
                    Stage stage= new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("etudiant-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 734 , 504);
                    stage.setResizable(false);
                    stage.setTitle("Etuadiant App");
                    stage.setScene(scene);

                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information");
                alert.setHeaderText("Auth erreur");
                alert.setContentText("Veuillez saisir les informations d'authentification .");
                alert.showAndWait();

            }

        });
    }
    public boolean auth(User user){
        if(user!=null) {
            return userService.auth(user);
        }
        else
            return false;
    }
    public User constructionUser(){
        User user = new User();
        if(email.getText().isEmpty() || password.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Auth erreur");
            alert.setContentText("Veuillez saisir les informations d'authentification .");
            alert.showAndWait();

        }
        else {
            user.setPassword(password.getText());
            user.setEmail(email.getText());
        }
        return user;
    }
}
*/
