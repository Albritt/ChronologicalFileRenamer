module com.renamer {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens com.renamer to javafx.fxml, javafx.graphics;
    exports com.renamer.Controllers;
    opens com.renamer.Controllers to javafx.fxml, javafx.graphics;
    exports com.renamer to javafx.graphics, javafx.fxml;
}