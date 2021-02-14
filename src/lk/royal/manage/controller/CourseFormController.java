package lk.royal.manage.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.royal.manage.bo.BOFactory;
import lk.royal.manage.bo.BOType;
import lk.royal.manage.bo.custom.impl.CourseBOImpl;
import lk.royal.manage.dto.CourseDTO;
import lk.royal.manage.view.tm.CourseTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CourseFormController implements Initializable {
    public TextField txtCode;
    public TextField txtCrsName;
    public TextField txtCrsType;
    public TextField txtDuration;
    public TableView tblCourse;
    public TableColumn colCode;
    public TableColumn colCrsName;
    public TableColumn colCrsType;
    public TableColumn colDuration;
    public JFXTextField txtSearch;
    public ImageView icon_home;

    CourseBOImpl courseBO= BOFactory.getInstance().getBO(BOType.COURSE);

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {

        if(Pattern.compile("^(C)[0-9]{1,}$").matcher(txtCode.getText()).matches()){
            if (Pattern.compile("[A-z]{1,}$").matcher(txtCrsName.getText()).matches()) {
                if (Pattern.compile("^[1-9]{1,2}[A-z]{1,}$").matcher(txtDuration.getText()).matches()) {


                    boolean b = courseBO.saveCourse(new CourseDTO(txtCode.getText(), txtCrsName.getText(), txtCrsType.getText(), txtDuration.getText()));

                    if (b) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Course Added Success...");
                        alert.showAndWait();

                    }
                    txtCode.setText("");
                    txtCrsName.setText("");
                    txtCrsType.setText("");
                    txtDuration.setText("");

                    loadAllCourses();


                } else {
                    txtDuration.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                    txtDuration.requestFocus();
                }
            }else {
                    txtCrsName.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                    txtCrsName.requestFocus();
                }

            }else {
            txtCode.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            txtCode.requestFocus();

        }




    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        courseBO.updateCourse(new CourseDTO(
                txtCode.getText(),txtCrsName.getText(),txtCrsType.getText(),txtDuration.getText())
        );
        loadAllCourses();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether you want to delete this Course?",
                ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType=alert.showAndWait();
        if(buttonType.get()==ButtonType.YES){
            CourseTM selectedItem= (CourseTM) tblCourse.getSelectionModel().getSelectedItem();
            try{
                courseBO.deleteCourse(selectedItem.getCode());
                tblCourse.getItems().remove(selectedItem);
                tblCourse.getSelectionModel().clearSelection();

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Failed to delete the Course",ButtonType.OK).show();

            }
        }
        txtCode.clear();
        txtCrsName.clear();
        txtCrsType.clear();
        txtDuration.clear();


    }

    public void searchOnAction(ActionEvent actionEvent) throws Exception {
            CourseDTO courseDTO=courseBO.getCourse(txtSearch.getText());
            if(courseDTO !=null){
                txtCode.setText(courseDTO.getCode());
                txtCrsName.setText(courseDTO.getCourse_name());
                txtCrsType.setText(courseDTO.getCourse_type());
                txtDuration.setText(courseDTO.getDuration());
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colCode.setCellValueFactory(new PropertyValueFactory("code"));
        colCrsName.setCellValueFactory(new PropertyValueFactory("course_name"));
        colCrsType.setCellValueFactory(new PropertyValueFactory("course_type"));
        colDuration.setCellValueFactory(new PropertyValueFactory("duration"));

        try {
            loadAllCourses();
        } catch (Exception e) {
            e.printStackTrace();
        }

       tblCourse.getSelectionModel().selectedItemProperty().
               addListener((observable, oldValue, newValue) ->{
                   setData((CourseTM) newValue);
               } );
    }



    private void setData(CourseTM tm){
        txtCode.setText(tm.getCode());
        txtCrsName.setText(tm.getCourse_name());
        txtCrsType.setText(tm.getCourse_type());
        txtDuration.setText(tm.getDuration());
    }



    private void loadAllCourses() throws Exception {
        tblCourse.getItems().clear();
        List<CourseDTO> allCourses=courseBO.getAllCourses();
        ObservableList<CourseTM> tmList= FXCollections.observableArrayList();

        for(CourseDTO courseDTO:allCourses){
            CourseTM tm=new CourseTM(
                    courseDTO.getCode(),
                    courseDTO.getCourse_name(),
                    courseDTO.getCourse_type(),
                    courseDTO.getDuration()
            );
            tmList.add(tm);
        }
        tblCourse.setItems(tmList);
    }

    public void newOnAction(ActionEvent actionEvent) {
        txtCode.clear();
        txtCrsName.clear();
        txtCrsType.clear();
        txtDuration.clear();

        String id= null;
        try {
            id = courseBO.getCourseID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtCode.setText(id);

    }

    public void homeOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/royal/manage/view/MainForm.fxml");
        Parent parent= FXMLLoader.load(resource);
        Scene scene=new Scene(parent);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
        Stage close= (Stage) icon_home.getScene().getWindow();
        close.close();
    }
}
