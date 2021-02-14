package lk.royal.manage.bo.custom;

import lk.royal.manage.bo.SuperBO;
import lk.royal.manage.dto.RegistrationDTO;

import java.util.ArrayList;

public interface RegistrationBO extends SuperBO {
    public boolean saveRegistration(RegistrationDTO registrationDTO)throws Exception;
    public boolean deleteRegistration(int id)throws Exception;
    public boolean updateRegistration(RegistrationDTO registrationDTO)throws Exception;
    public RegistrationDTO getRegistration(int id)throws Exception;
    public ArrayList<RegistrationDTO> getAllRegistrations()throws Exception;
    public int getRegNO()throws Exception;
}
