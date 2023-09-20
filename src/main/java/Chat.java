import java.sql.*;
import java.util.Scanner;

public class Chat {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("RED SOCIAL JOHAN S.A.S");

        System.out.println("¿Deseas Regitrar usuario o Iniciar sesion?: ");
        String answer = scanner.nextLine();

        if (answer.equals("Registrar usuario")) {

            System.out.println("Ingresa tu nombre de usuario: ");
            String user_record = scanner.nextLine();

            System.out.println("Ingresa tu password: ");
            String password_record = scanner.nextLine();

            if (user_record.equals("") || password_record.equals("")) {
                System.out.println("Estos campos son obligatorios para realizar el debido registro");

            }else{
                Insert(user_record, password_record);
            }
        }

        if (answer.equals("Iniciar sesion")) {

            System.out.println("Ingresa tu nombre de usuario: ");
            String user = scanner.nextLine();

            System.out.println("Ingresa tu password: ");
            String password = scanner.nextLine();

            String session = Session(user, password);

            if (session.equals("")) {
                System.out.println("Lamentable tus credenciales estan incorrectas");
            } else {

                boolean aux = true;

                while (aux) {

                    System.out.println("1. Enviar mensajes: ");
                    System.out.println("2. Leer mensajes: ");
                    System.out.println("3. Cerrar Sesion");
                    System.out.println("Ingrese un numero entre 1 - 5: ");
                    int result = Integer.parseInt(scanner.nextLine());

                    switch (result) {

                        case 1:

                            System.out.print("Ingrese el mensaje que deseas enviar: ");
                            String msg = scanner.nextLine();

                            System.out.println("A que nombre de usuario quieres enviar el mensaje redactado: ");
                            String username = scanner.nextLine();

                            String userbd = Select_user(username);
                            if (userbd.equals("")) {
                                System.out.println("El usuario al que tratas de enviar mensaje no se encuentra registrado en la red social JOHAN S.A.S.");
                            } else {

                                SendMensages(user, msg, username);
                            }

                            break;
                        case 2:

                            Select_Mensage(user);
                            break;
                        case 3:
                            System.out.println("Cerrando Sesion...");

                            aux = false;

                            break;

                        default:

                            System.out.println("Ingrese un numero valido");
                    }
                }
            }
        }
    }

    private static void Insert(String user_record, String password_record) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/redes";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios");


            // Sentencia INSERT
            String sql = "INSERT INTO usuarios (user, password) VALUES (?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user_record);
            preparedStatement.setString(2, password_record);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Registro exitoso, ya puedes comunicarte con tus contactos.");
            } else {
                System.out.println("No se pudo registrar el usuario, intente de nuevo.");
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }

}

    private static void Select_Mensage(String user) throws ClassNotFoundException, SQLException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/redes";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM sociales WHERE user_recibe = ? and estado = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, user);
        statement.setString(2, "Enviado");
        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()){
            System.out.println("No tienes mensajes por leer");
        }
        while (resultSet.next()) {
            String mensage = resultSet.getString("mensaje");
            String user_envia = resultSet.getString("user");
            System.out.println("El usuario: "+ user_envia +" Te envia: " + mensage);
            Edit_Mensage(mensage);
        }
        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();
    }

    private static void Edit_Mensage(String mensage) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/redes";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE sociales SET estado = ? WHERE mensaje = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, "Visto");
        preparedStatement.setString(2, mensage);

        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Has leído exitosamente el mensaje");

        }

        preparedStatement.close();
        connection2.close();
    }

    private static String Select_user(String user) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/redes";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM usuarios WHERE user = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, user);
        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();
        // Procesar el resultado si existe
        if (resultSet.next()) {
            String userbd = resultSet.getString("user");
            password = resultSet.getString("password");
            return userbd;
        }
        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();
        return "";
    }

    private static void SendMensages(String user, String msg, String username1) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/redes";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sociales");


            // Sentencia INSERT
            String sql = "INSERT INTO sociales (user, mensaje, user_recibe, estado) VALUES (?, ?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, msg);
            preparedStatement.setString(3, username1);
            preparedStatement.setString(4, "Enviado");

            // Ejecutar la sentencia
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Mensaje enviado con exito.");
            } else {
                System.out.println("No se pudo enviar el mensaje.");
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static String Session(String user, String pass) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/redes";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM usuarios WHERE user = ? AND password = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, user); // Establecer el valor del parámetro
        statement.setString(2, pass);
        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();
        // Procesar el resultado si existe
        if (resultSet.next()) {
            String userbd = resultSet.getString("user");
            password = resultSet.getString("password");


            System.out.println("BIENVENIDO AL SISTEMA DE RED SOCIAL JOHAN S.A.S, tu user es: " + userbd);

            return userbd;
        }
        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();
        return "";
    }
    }

