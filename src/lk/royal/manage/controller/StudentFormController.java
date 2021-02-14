package lk.royal.manage.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import lk.royal.manage.bo.custom.impl.StudentBOImpl;
import lk.royal.manage.dto.CourseDTO;
import lk.royal.manage.dto.StudentDTO;
import lk.royal.manage.view.tm.CourseTM;
import lk.royal.manage.view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    public ImageView icon_home;

    StudentBOImpl studentBO= BOFactory.getInstance().getBO(BOType.STUDENT);

    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {

        if(Pattern.compile("^(S)[0-9]{1,}$").matcher(txtSID.getText()).matches()){
            if (Pattern.compile("[A-z]{1,}$").matcher(txtName.getText()).matches()){
             if (Pattern.compile("^[A-z]{1,}$").matcher(txtAddress.getText()).matches()){
                 if (Pattern.compile("^[0-9]{10}$").matcher(txtContact.getText()).matches()){
//                     if (Pattern.compile("^[0-9]{1,12}/[0-9]{1,31}/[0-9]{4}$").matcher(dateDOB.getValue().toString()).matches()){

                         boolean isAdded=studentBO.saveStudent(new StudentDTO(txtSID.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),dateDOB.getValue().toString(),cmbGender.getValue().toString()));

                         if(isAdded){
                             Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                             alert.setHeaderText(null);
                             alert.setContentText("Student Added Success...");
                             alert.showAndWait();
                         }
                         loadAllStudents();

//                     }else {
//                         dateDOB.setStyle("-fx-border-color:red;-fx-border-width:2px;");
//                         dateDOB.requestFocus();
//                     }


                 }else {
                     txtContact.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                     txtContact.requestFocus();
                 }

             }else {
                 txtAddress.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                 txtAddress.requestFocus();
             }

            }else {
                txtName.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                txtName.requestFocus();
            }

        }else {
            txtSID.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            txtSID.requestFocus();
        }


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
        txtSID.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        dateDOB.setValue(null);
        cmbGender.getSelectionModel().clearSelection();


        String s=null;
        try {
            s=studentBO.getStudentID();
        } catch (Exception e) {
            e.printStackTrace();
        }
         txtSID.setText(s);
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
