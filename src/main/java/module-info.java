module org.humeniuc.pt2024_30228_humeniuc_dan_assignment_ {
    requires javafx.controls;
    requires javafx.fxml;
            
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
            
//    opens org.humeniuc to javafx.fxml;
//    exports org.humeniuc;
    exports org.humeniuc.logic;
    opens org.humeniuc.logic to javafx.fxml;
    exports org.humeniuc.GUI;
    opens org.humeniuc.GUI to javafx.fxml;
    exports org.humeniuc.model;
    opens org.humeniuc.model to javafx.fxml;
}