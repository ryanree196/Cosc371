/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class BankManagement extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/Login.fxml")));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    //   VM Options
//  --module-path "C:\Program Files\Java\javafx-sdk-21.0.6\lib" --add-modules javafx.controls,javafx.fxml
//  --module-path "C:\Program Files\Java\javafx-sdk-21.0.6\lib" --add-modules javafx.controls,javafx.fxml --add-exports javafx.base/com.sun.javafx.event=ALL-UNNAMED


}
