package lk.royal.manage.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.royal.manage.bo.BOFactory;
import lk.royal.manage.bo.BOType;
import lk.royal.manage.bo.custom.impl.CourseBOImpl;
import lk.royal.manage.bo.custom.impl.StudentBOImpl;
import lk.royal.manage.dto.CourseDTO;
import lk.royal.manage.dto.StudentDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public ImageView courseID;
    public ImageView student_icon;
    public ImageView register_icon;
    public Label lblStudent;
    public Label lblCourse;

    StudentBOImpl studentBO= BOFactory.getInstance().getBO(BOType.STUDENT);
    CourseBOImpl courseBO=BOFactory.getInstance().getBO(BOType.COURSE);


    public void courseClickOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/royal/manage/view/CourseForm.fxml");
        Parent parent= FXMLLoader.load(resource);
        Scene scene=new Scene(parent);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
        Stage close= (Stage) courseID.getScene().getWindow();
        close.close();

    }

    public void studentClickOnAction(MouseEvent mouseEvent) throws IOException {

        URL resource = this.getClass().getResource("/lk/royal/manage/view/StudentForm.fxml");
        Parent parent= FXMLLoader.load(resource);
        Scene scene=new Scene(parent);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
        Stage close= (Stage) student_icon.getScene().getWindow();
        close.close();


    }

    public void regClickOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/royal/manage/view/RegistrationForm.fxml");
        Parent parent= FXMLLoader.load(resource);
        Scene scene=new Scene(parent);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
        Stage close= (Stage) register_icon.getScene().getWindow();
        close.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            List<StudentDTO> allStudents=studentBO.getAllStudents();
            lblStudent.setText(String.valueOf(allStudents.size()));

            List<CourseDTO> allCourses=courseBO.getAllCourses();
            lblCourse.setText(String.valueOf(allCourses.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
