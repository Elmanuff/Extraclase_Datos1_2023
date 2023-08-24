module com.javafx.extraclase_datos1_ {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.Servidor to javafx.fxml;
    exports com.Servidor;
    exports com.Cliente;
    opens com.Cliente to javafx.fxml;
}