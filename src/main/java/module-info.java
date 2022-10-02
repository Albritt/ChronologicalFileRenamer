module com.renamer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.renamer to javafx.fxml, javafx.graphics;
    exports com.renamer.Controllers;
    opens com.renamer.Controllers to javafx.fxml, javafx.graphics;
    exports com.renamer;
}