module com.javafx.extraclase_datos1_2023 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.Cliente to javafx.fxml;
    exports com.Cliente;
}