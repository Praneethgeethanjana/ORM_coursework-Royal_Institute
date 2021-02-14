package lk.royal.manage.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.royal.manage.bo.BOFactory;
import lk.royal.manage.bo.BOType;
import lk.royal.manage.bo.custom.impl.CourseBOImpl;
import lk.royal.manage.bo.custom.impl.RegistrationBOImpl;
import lk.royal.manage.bo.custom.impl.StudentBOImpl;
import lk.royal.manage.dto.CourseDTO;
import lk.royal.manage.dto.RegistrationDTO;
import lk.royal.manage.dto.StudentDTO;
import lk.royal.manage.entity.Course;
import lk.royal.manage.view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegistrationFormController implements Initializable {

    public JFXTextField txtregNO;
    public ComboBox cmbSID;
    public ComboBox cmbCID;
    public JFXDatePicker dateReg;
    public JFXTextField txtFee;
    public JFXTextField txtSearchReg;
    public JFXTextField txtSname;
    public JFXTextField txtCname;
    public ImageView icon_home;


    RegistrationBOImpl regBO=BOFactory.getInstance().getBO(BOType.REGISTRATION);
    StudentBOImpl studentBO= BOFactory.getInstance().getBO(BOType.STUDENT);
    CourseBOImpl courseBO=BOFactory.getInstance().getBO(BOType.COURSE);


    public void btnSaveOnAction(ActionEvent actionEvent) throws Exception {
        if(Pattern.compile("^[1-9]{1,}$").matcher(txtregNO.getText()).matches()){
            if(Pattern.compile("^[0-9]{1,}.[0-9]{2}$").matcher(txtFee.getText()).matches()){



                        try {
                            int regId = Integer.parseInt(txtregNO.getText());
                            String regDate = dateReg.getValue().toString();
                            double fee = Double.parseDouble(txtFee.getText());
                            StudentDTO studentDTO = new StudentDTO(cmbSID.getValue().toString());
                            CourseDTO courseDTO = new CourseDTO(cmbCID.getValue().toString());

                            boolean flag = regBO.saveRegistration(new RegistrationDTO(regId, regDate, fee, studentDTO, courseDTO));
                            if (flag) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Student Registerd!", ButtonType.OK).show();
                            }
                        } finally {

                        }


            }else {
                txtFee.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                txtFee.requestFocus();
            }

        }else {
            txtregNO.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            txtregNO.requestFocus();
        }


    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }


    public void searchOnAction(ActionEvent actionEvent) throws Exception {
        RegistrationDTO registrationDTO=regBO.getRegistration(Integer.parseInt(txtSearchReg.getText()));
        if(registrationDTO!=null){
            StudentDTO studentDTO=registrationDTO.getStudentDTO();
            CourseDTO courseDTO=registrationDTO.getCourseDTO();
            StudentDTO student=studentBO.getStudent(studentDTO.getId());
            CourseDTO course=courseBO.getCourse(courseDTO.getCode());

            txtregNO.setText(String.valueOf(registrationDTO.getRegNo()));
            dateReg.setValue(LocalDate.parse(registrationDTO.getRegDate()));
            txtFee.setText(String.valueOf(registrationDTO.getRegFee()));
            cmbSID.setValue(student.getId());
            cmbCID.setValue(course.getCode());
            txtSname.setText(student.getStudent_name());
            txtCname.setText(course.getCourse_name());
        }else {
            new Alert(Alert.AlertType.ERROR,"Wrong Registration Number...").show();
        }


    }

    public void getSID() {
        try {
            List<StudentDTO> list=studentBO.getAllStudents();
            for (StudentDTO s:list){
                cmbSID.getItems().addAll(s.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getCID()  {
        try {
            List<CourseDTO>list=courseBO.getAllCourses();
            for (CourseDTO c:list){
                cmbCID.getItems().addAll(c.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getSID();
        getCID();

//        cmbSID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudentTM>() {
//            @Override
//            public void changed(ObservableValue<? extends StudentTM> observable, StudentTM oldValue, StudentTM newValue) {
//                if(newValue==null){
//                    txtSname.clear();
//                    return;
//                }
//                txtSname.setText(newValue.getStudent_name());
//            }
//        });

    }

    public void getSIDOnAction(ActionEvent actionEvent) throws Exception {
        List<StudentDTO> allStudents=studentBO.getAllStudents();
        for(StudentDTO student:allStudents) {
            if (cmbSID.getValue().equals(student.getId())) {
                txtSname.setText(student.getStudent_name());
                return;
            }
        }
    }


    public void getCIDOnAction(ActionEvent actionEvent) throws Exception {
        List<CourseDTO> allCourses=courseBO.getAllCourses();
        for(CourseDTO course:allCourses){
            if (cmbCID.getValue().equals(course.getCode())){
                txtCname.setText(course.getCourse_name());
                return;
            }
        }

    }

    public void newOnAction(ActionEvent actionEvent) {
        int no= 1;
        try {
            no = regBO.getRegNO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtregNO.setText(String.valueOf(no));
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
