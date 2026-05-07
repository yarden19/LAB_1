module org.example.lab_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jfr;


    opens org.example.lab_2 to javafx.fxml;
    exports org.example.lab_2;
}