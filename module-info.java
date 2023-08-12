module EngineersApp {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.media;
	requires javafx.swing;
	requires javafx.web;
	requires spring.context;
	exports mainpackage;
	
	opens application to javafx.graphics, javafx.fxml;
}
