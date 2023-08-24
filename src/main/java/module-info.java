module com.javafx.extraclase_datos1_2023 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cliente to javafx.fxml;
    exports com.cliente;
}