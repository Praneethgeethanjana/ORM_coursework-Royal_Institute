package lk.royal.manage.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainFormController {
    public ImageView courseID;

    public void courseClickOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/royal/manage/view/CourseForm.fxml");
        Parent parent= FXMLLoader.load(resource);
        Scene scene=new Scene(parent);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

    }
}
