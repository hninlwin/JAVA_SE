package com.jdc.mdy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mdy.entity.User;
import com.jdc.mdy.entity.User.Role;
import com.jdc.mdy.service.UserService;
import com.jdc.mdy.utils.StockException;
import com.jdc.mdy.utils.MessageManager;
import com.jdc.mdy.utils.MessageManager.MessageStyle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UserController implements ButtonManager {

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
	private TableColumn<User, Integer> col_num;

	private UserService service;
	private User user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = new UserService();
		new ButtonManagerControl(this);
		loadView();
		cbo_role.getItems().addAll(Role.values());
		col_num.setCellValueFactory(
				col -> new ReadOnlyObjectWrapper<Integer>(tv_user.getItems().indexOf(col.getValue()) + 1));

		tv_user.setOnMouseClicked(event -> {

			if (event.getClickCount() == 2) {
				user = tv_user.getSelectionModel().getSelectedItem();

				tf_login.setText(user.getLoginId());
				tf_login.setDisable(true);

				tf_userName.setText(user.getName());
				pf_password.setText(user.getPassword());
				ch_active.setSelected(user.isActive());
				cbo_role.setValue(user.getRole());
			}
		});
		
		tf_userName.textProperty().addListener((a,b,c)->{
			
			tv_user.getItems().clear();
			tv_user.getItems().addAll(service.search(c));
		});
	}

	@Override
	public void save() {

		service.saveUser(getUser());
		loadView();
		clear();

	}

	@Override
	public void update() {

		service.update(getUser());
		loadView();
		clear();

	}

	private void loadView() {
		tv_user.getItems().clear();
		tv_user.getItems().addAll(service.findAll());

	}

	private void clear() {
		tf_login.clear();
		tf_userName.clear();
		pf_confirm.clear();
		pf_password.clear();
		ch_active.setSelected(false);
		cbo_role.getSelectionModel().clearSelection();
		tf_login.setDisable(false);
	}

	private User getUser() {
		try {

			if (tf_login.getText().isEmpty() || tf_login.getText() == null) {
				throw new StockException("Please type login id !");
			}

			if (tf_userName.getText().isEmpty() || tf_userName.getText() == null) {
				throw new StockException("Please type user name!");
			}

			if (pf_password.getText().isEmpty() || pf_password.getText() == null) {
				throw new StockException("Please type Password !");
			}

			if (!pf_password.getText().equals(pf_confirm.getText())) {
				throw new StockException("Password and confirm password are not equal !");
			}
			user = new User();
			user.setLoginId(tf_login.getText());
			user.setName(tf_userName.getText());
			user.setPassword(pf_password.getText());
			user.setRole(cbo_role.getValue());
			user.setActive(true);

			return user;

		} catch (

		Exception e) {
			MessageManager.showMessage(e.getMessage(), MessageStyle.error);
		}
		return null;
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		user.setActive(false);
		service.update(user);
		loadView();
		clear();

	}

}
