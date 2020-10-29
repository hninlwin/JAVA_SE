module Stock {
	
	exports com.jdc.mdy;

	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	
	opens com.jdc.mdy.controller to javafx.fxml;
	
	
}