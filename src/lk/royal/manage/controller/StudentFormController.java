package lk.royal.manage.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.royal.manage.bo.BOFactory;
import lk.royal.manage.bo.BOType;
import lk.royal.manage.bo.custom.impl.StudentBOImpl;
import lk.royal.manage.dto.CourseDTO;
import lk.royal.manage.dto.StudentDTO;
import lk.royal.manage.view.tm.CourseTM;
import lk.royal.manage.view.tm.StudentTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {
    public TextField txtSID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public JFXDatePicker dateDOB;
    public JFXComboBox cmbGender;
    public JFXTextField txtSearchStudent;
    public TableView tblStudents;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;

    StudentBOImpl studentBO= BOFactory.getInstance().getBO(BOType.STUDENT);

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        boolean isAdded=studentBO.saveStudent(new StudentDTO(txtSID.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),dateDOB.getValue().toString(),cmbGender.getValue().toString()));

        if(isAdded){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Student Added Success...");
            alert.showAndWait();
        }
        loadAllStudents();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        studentBO.updateStudent(new StudentDTO(
                txtSID.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),dateDOB.getValue().toString(),cmbGender.getValue().toString()));

        loadAllStudents();

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether you want to delete this Student ?",
                ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType=alert.showAndWait();
        if(buttonType.get()==ButtonType.YES){
            StudentTM selectedItem= (StudentTM) tblStudents.getSelectionModel().getSelectedItem();
            try{
                studentBO.deleteStudent(selectedItem.getId());
                tblStudents.getItems().remove(selectedItem);
                tblStudents.getSelectionModel().clearSelection();

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Failed to delete the Student",ButtonType.OK).show();

            }
        }

    }


    public void searchOnAction(ActionEvent actionEvent) throws Exception {
            StudentDTO studentDTO=studentBO.getStudent(txtSearchStudent.getText());
            if(studentDTO!=null){
                txtSID.setText(studentDTO.getId());
                txtName.setText(studentDTO.getStudent_name());
                txtAddress.setText(studentDTO.getAddress());
                txtContact.setText(studentDTO.getContact());
                dateDOB.setValue(LocalDate.parse(studentDTO.getDob()));
                cmbGender.setValue(studentDTO.getGender());

            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbGender.getItems().addAll("Male","Female");

        colID.setCellValueFactory(new PropertyValueFactory("id"));
        colName.setCellValueFactory(new PropertyValueFactory("student_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colContact.setCellValueFactory(new PropertyValueFactory("contact"));
        colDOB.setCellValueFactory(new PropertyValueFactory("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory("gender"));

        try {
            loadAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tblStudents.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) ->{
                    setData((StudentTM) newValue);
                } );
    }

    private void setData(StudentTM tm){
        txtSID.setText(tm.getId());
        txtName.setText(tm.getStudent_name());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());
        dateDOB.setValue(LocalDate.parse(tm.getDob()));
        cmbGender.setValue(tm.getGender());
    }


    private void loadAllStudents() throws Exception {
        tblStudents.getItems().clear();
        List<StudentDTO> allStudents=studentBO.getAllStudents();
        ObservableList<StudentTM> tmList=FXCollections.observableArrayList();

        for(StudentDTO studentDTO:allStudents){
            StudentTM tm=new StudentTM(
                    studentDTO.getId(),
                    studentDTO.getStudent_name(),
                    studentDTO.getAddress(),
                    studentDTO.getContact(),
                    studentDTO.getDob(),
                    studentDTO.getGender()
            );
            tmList.add(tm);
        }
        tblStudents.setItems(tmList);
    }

    public void newOnAction(ActionEvent actionEvent) {

    }
}
