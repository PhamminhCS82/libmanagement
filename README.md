# Lib_management

Sửa App.java 
	scene = new Scene(loadFXML("primary"));
Sửa PrimaryController
	clYear.setCellValueFactory(new PropertyValueFactory<>("year"));
	clAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
Thêm module-info.java
	opens com.pqm.pojo to javafx.base