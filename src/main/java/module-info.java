module com.sistemaacademicotrabalho.sistemaacademicotrabalho {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;

    opens com.sistemaacademicotrabalho.sistemaacademicotrabalho to javafx.fxml;
    exports com.sistemaacademicotrabalho.sistemaacademicotrabalho;
}