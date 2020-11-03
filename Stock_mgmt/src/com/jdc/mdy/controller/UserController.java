package com.jdc.mdy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mdy.entity.User;
import com.jdc.mdy.entity.User.Role;
import com.jdc.mdy.service.UserService;
import com.jdc.mdy.utils.StockException;
import com.jdc.mdy.utils.MessageManager;
import com.jdc.mdy.utils.MessageManager.MessageStyle;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UserController implements ButtonManager{

    @FXML
    private TextField tf_login;

    @FXML
    private TextField tf_userName;

    @FXML
    private PasswordField pf_password;

    @FXML
    private PasswordField pf_confirm;

    @FXML
    private ComboBox<Role> cbo_role;

    @FXML
    private CheckBox ch_active;

    @FXML
    private TableView<User> tv_user;

    @FXML
    private TableColumn<Integer, User> col_num;
    
    private UserService service;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service=new UserService();
		new ButtonManagerControl(this);
		cbo_role.getItems().addAll(Role.values());
	}

	@Override
	public void save() {
		try {
			
			if(tf_login.getText().isEmpty() ||tf_login.getText()==null) {
				throw new StockException("Please type login id !");
			}
			
			if(tf_userName.getText().isEmpty() ||tf_userName.getText()==null) {
				throw new StockException("Please type user name!");
			}
			
			if(pf_password.getText().isEmpty() ||pf_password.getText()==null) {
				throw new StockException("Please type Password !");
			}
			
			if(!pf_password.getText().equals(pf_confirm.getText())) {
				throw new StockException("Password and confirm password are not equal !");
			}
			User user=new User();
			user.setLoginId(tf_login.getText());
			user.setName(tf_userName.getText());
			user.setPassword(pf_password.getText());
			user.setRole(cbo_role.getValue());
			
			service.saveUser(user);
			
		}catch (Exception e) {
			MessageManager.showMessage(e.getMessage(),MessageStyle.error);
		}
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
