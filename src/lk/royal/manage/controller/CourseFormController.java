package lk.royal.manage.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.royal.manage.bo.BOFactory;
import lk.royal.manage.bo.BOType;
import lk.royal.manage.bo.custom.impl.CourseBOImpl;
import lk.royal.manage.dto.CourseDTO;

public class CourseFormController {
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

    CourseBOImpl courseBO= BOFactory.getInstance().getBO(BOType.COURSE);

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        boolean b = courseBO.saveCourse(new CourseDTO(txtCode.getText(), txtCrsName.getText(), txtCrsType.getText(), txtDuration.getText()));

        if(b){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Course Added Success...");
            alert.showAndWait();
        }
        txtCode.setText("");
        txtCrsName.setText("");
        txtCrsType.setText("");
        txtDuration.setText("");

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        courseBO.updateCourse(new CourseDTO(
                txtCode.getText(),txtCrsName.getText(),txtCrsType.getText(),txtDuration.getText())
        );
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

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
}
