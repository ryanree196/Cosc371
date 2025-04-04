package bankmanagement.controller;

import DB.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private AnchorPane anchorPaneLeft;

    @FXML
    private Button cancelButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Hyperlink forgotPasswordHyperLink;

    @FXML
    private Hyperlink helpHyperlink;

    @FXML
    private AnchorPane imageAnchorPane;

    @FXML
    private ImageView lockIcon;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Hyperlink signUpHyperlink;

    @FXML
    private TextField usernameTextField;

    @FXML
    void cancelOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    

    @FXML
    void loginOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordField.getText().isBlank()) {

            String usernameInput = usernameTextField.getText();
            String password = passwordField.getText();
            checkLoginCredentials(usernameInput, password);
        } else {
            errorLabel.setText("Please enter your username and password !!!");
        }

        KeyCombination keyCombination = new KeyCodeCombination(KeyCode.E, KeyCombination.SHIFT_DOWN);
        loginButton.getScene().getAccelerators().put(keyCombination, this::navigateToDashboard);
    }
    @FXML
void forgotPasswordOnAction(ActionEvent event) {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ForgotPassword.fxml"));
        Scene forgotPasswordScene = new Scene(fxmlLoader.load());
        Stage forgotPasswordStage = new Stage();
        forgotPasswordStage.setScene(forgotPasswordScene);
        forgotPasswordStage.initStyle(StageStyle.UTILITY);
        forgotPasswordStage.setTitle("Reset Password");
        forgotPasswordStage.show();
    } catch (Exception e) {
        e.printStackTrace();
        errorLabel.setText("Failed to open Forgot Password window.");
    }
}


    private void checkLoginCredentials(String usernameInput, String password) {
        String verifyLogin = "SELECT email, password FROM users WHERE email = ?";
        try (
                Connection connection = DBConnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(verifyLogin);
        ) {
            preparedStatement.setString(1, usernameInput);
            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next()) {
                String existingPassword = queryResult.getString("password");
                if (existingPassword.equals(password)) {
                    navigateToDashboard();
                    errorLabel.setText("Login successful");
                } else {
                    passwordField.clear();
                    usernameTextField.clear();
                    errorLabel.setText("Invalid Login. Please try again.");
                }
            } else {
                errorLabel.setText("User not found. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorLabel.setText("An error occurred. Please try again later.");
        }
    }

    private void navigateToDashboard() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../BankMain.fxml"));
            Scene dashboardScene = new Scene(fxmlLoader.load());
            Stage dashboardStage = new Stage();
            dashboardStage.setScene(dashboardScene);
            dashboardStage.initStyle(StageStyle.DECORATED);
            dashboardStage.show();
            Stage loginStage = (Stage) loginButton.getScene().getWindow();
            loginStage.close();
        } catch (Exception e) {
            System.out.printf("Error loading dashboard: %s", e.getMessage());
            e.printStackTrace();
        }

    }

}















































