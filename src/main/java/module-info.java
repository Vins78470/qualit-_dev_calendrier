module com.example.tp1_deuxieme_projet {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.tp1_deuxieme_projet to javafx.fxml;
    exports com.example.tp1_deuxieme_projet;

    exports vue;
    exports modele;
}