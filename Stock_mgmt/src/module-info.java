module Stock {

	exports com.jdc.mdy;

	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	requires lombok;
	requires jdk.javadoc;
	requires java.desktop;

	opens com.jdc.mdy.controller to javafx.fxml;
	opens com.jdc.mdy.entity to javafx.base;

}