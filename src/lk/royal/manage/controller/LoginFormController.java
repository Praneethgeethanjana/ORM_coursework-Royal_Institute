package lk.royal.manage.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    public ImageView icnVisible;
    public ImageView icnUnvisible;
    public JFXTextField txtUser;
    public PasswordField password;
    public TextField txtpw;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtpw.setVisible(false);
        icnUnvisible.setVisible(true);
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String username=txtUser.getText().trim();
        String pw=password.getText().trim();

        if (username.length()>0 && pw.length()>0){

            if (username.equalsIgnoreCase("royal")
                    && pw.equals("1234")){


                URL resource = this.getClass().getResource("/lk/royal/manage/view/MainForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene= new Scene(load);
                Stage stage= new Stage();
                stage.setScene(scene);
                stage.show();



            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again !!!!",
                        ButtonType.OK,ButtonType.NO).show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Empty !!!!",
                    ButtonType.OK,ButtonType.NO).show();
        }

    }

    public void eyeOpen(MouseEvent mouseEvent) {
        icnUnvisible.setVisible(true);
        icnVisible.setVisible(false);
        password.setVisible(true);
        txtpw.setVisible(false);
        txtpw.clear();

    }

    public void eyeClose(MouseEvent mouseEvent) {
        icnUnvisible.setVisible(false);
        icnVisible.setVisible(true);
        txtpw.setVisible(true);
        txtpw.setText(password.getText());

    }
}
