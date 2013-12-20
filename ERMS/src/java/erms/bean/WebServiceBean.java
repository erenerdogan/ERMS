/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import erms.model.CategoryModel;
import erms.service.FactoryDao;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author eren
 */
@ManagedBean
@RequestScoped
public class WebServiceBean implements Serializable{
    private String message;

    public String getMessage() {
        try {
            List<CategoryModel> l = FactoryDao.getCategoryDao().getAllCategory(3);
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.writeValueAsString(l);
            System.out.println(message);
            return message;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(WebServiceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
