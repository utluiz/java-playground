import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * http://pt.stackoverflow.com/questions/171801/fechar-conex%C3%A3o-em-outro-m%C3%A9todo
 */
public class ConnectionExample {

    public static void main(String[] args) {

    }
}

class QualquerOutraClasse {
    public void qualquerOutroMetodo() {
        try (Connection conn = JavaConnect.getConnection()) {
            conn.createStatement().executeQuery("select * from tabela");
            //...
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao acessar o banco de dados: " + e.getMessage());
        }
    }


    public void qualquerOutroMetodoUsandoLambda() {
        JavaConnect.executar(con -> {
            con.createStatement().executeQuery("select * from tabela");
        });
    }
}

class JavaConnect {
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "SQLite Driver não encontrado!");
        }
    }

    public interface OperacaoNoBanco {
        void executar(Connection connection) throws SQLException;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:/home/leo/NetBeansProjects/Controller_workshop/dbControlworkshop.sqlite");
    }

    public static void executar(final OperacaoNoBanco operacaoNoBanco) {
        try (Connection conn = JavaConnect.getConnection()) {
            operacaoNoBanco.executar(getConnection());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}
