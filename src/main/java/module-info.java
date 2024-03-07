module org.humeniuc.pt2024_30228_humeniuc_dan_assignment_1 {
    requires javafx.controls;
    requires javafx.fxml;
            
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
            
    opens org.humeniuc to javafx.fxml;
    exports org.humeniuc;
}