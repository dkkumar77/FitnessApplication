package sample;
import java.sql.*;

class Database {


    Connection connection;

    Database() throws SQLException, ClassNotFoundException {
        connect();


    }

    final String DB_URL = "jdbc:mysql://localhost:3306/";
    final String USER = "root";
    final String PASS = "Tyrantboys1";


    public void connect() throws SQLException {

        try {

            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE DATABASE FitnessData");

            this.connection = DriverManager.getConnection(DB_URL + "FitnessData", USER, PASS);


            Statement st = connection.createStatement();

            st.executeUpdate("CREATE TABLE LOGIN (username VARCHAR(255), password VARCHAR(255), " +
                    "email VARCHAR(255), dob VARCHAR(255), firstname VARCHAR(255), lastname VARCHAR(255))");
            st.executeUpdate("CREATE TABLE CODE(username VARCHAR(255), codeval VARCHAR(255))");


        } catch (SQLException e) {

            if (e.getErrorCode() == 1007) {

                this.connection = DriverManager.getConnection(DB_URL + "FitnessData", USER, PASS);


            } else {
                e.printStackTrace();
            }
        }


    }


    @SuppressWarnings("unused")
    private void drop() throws SQLException {


        Statement statement = connection.createStatement();

        statement.executeUpdate("DROP DATABASE FitnessData");


    }


    public void addData(String username, String password, String email, String dob, String firstname, String lastname)
            throws SQLException {


        Statement s = connection.createStatement();

        String state = "insert into LOGIN (username, password, email, dob, firstname, lastname) VALUES (" + " \"" + username + "\"" + ", " + "\"" + password + "\"" + ", " + " \"" + email + "\"" + ", " + " \"" + dob + "\"" + ", " + " \"" + firstname + "\"" + ", " + " \"" + lastname + "\"" + " )";

        s.executeUpdate(state);

        s.executeUpdate("insert into CODE(username, codeval) VALUES (" + "\"" + username + "\"," + "0)");



    }


    public void updateCode(String username, String code) throws SQLException {
        Statement e = connection.createStatement();
        String s = "update CODE set codeval = " + code + " where username = " + username;
        System.out.println(s);
        e.executeUpdate("update CODE set codeval = " + "\"" + code + "\"" + " where username = " + "\"" + username + "\"");


    }

    public String getPassword(String username) throws SQLException {

        if (usernameExists(username)) {
            Statement rs = connection.createStatement();


            ResultSet result = rs.executeQuery("select password FROM LOGIN WHERE username = " + "\"" + username + "\";");
            String c = "";

            while (result.next()) {

                c = result.getString("password");
            }

            return c;


        }
        return null;


    }

    public String getEmail(String username) throws SQLException {

        if (usernameExists(username)) {
            Statement rs = connection.createStatement();


            ResultSet result = rs.executeQuery("select email FROM LOGIN WHERE username = " + "\"" + username + "\";");
            String c = "";

            while (result.next()) {

                c = result.getString("email");
            }

            return c;


        }
        return null;


    }

    public String getDOB(String username) throws SQLException {

        if (usernameExists(username)) {
            Statement rs = connection.createStatement();


            ResultSet result = rs.executeQuery("select dob FROM LOGIN WHERE username = " + "\"" + username + "\";");
            String c = "";

            while (result.next()) {

                c = result.getString("dob");
            }

            return c;


        }
        return null;


    }




    public String getFirstName(String username) throws SQLException {

        if (usernameExists(username)) {
            Statement rs = connection.createStatement();


            ResultSet result = rs.executeQuery("select firstname FROM LOGIN WHERE username = " + "\"" + username + "\";");
            String c = "";

            while (result.next()) {

                c = result.getString("firstname");
            }

            return c;


        }
        return null;


    }

    public String getLastName(String username) throws SQLException {

        if (usernameExists(username)) {
            Statement rs = connection.createStatement();


            ResultSet result = rs.executeQuery("select lastname FROM LOGIN WHERE username = " + "\"" + username + "\";");
            String c = "";

            while (result.next()) {

                c = result.getString("lastname");
            }

            return c;


        }
        return null;


    }

    public String getCode(String username) throws SQLException {

        if (usernameExists(username)) {
            Statement rs = connection.createStatement();


            ResultSet result = rs.executeQuery("select codeval FROM CODE WHERE username = " + "\"" + username + "\";");
            String c = "";

            while (result.next()) {

                c = result.getString("codeval");
            }

            return c;


        }
        return null;
    }


    public boolean usernameExists(String username) throws SQLException {

        Statement st = connection.createStatement();


        ResultSet rs = st.executeQuery("SELECT EXISTS(SELECT * from LOGIN WHERE username= " + "\"" + username + "\"" + ");");

        String test = "";
        while (rs.next()) {
            test = rs.getString(1);


        }

        return test.equals("1");


    }



    public void print() throws SQLException {

        Statement stmt = connection.createStatement();

        String strSelect = "select * from LOGIN";
        ResultSet result = stmt.executeQuery(strSelect);
        while (result.next()) {
            System.out.println(result.getString("username") + ", "
                    + result.getString("password") + ", "
                    + result.getString("email") + ", "
                    + result.getString("dob") + ", "
                    + result.getString("firstname") + ", "
                    + result.getString("lastname"));
        }
    }
    public void printCode() throws SQLException {

        Statement stmt = connection.createStatement();

        String strSelect = "select * from CODE";
        ResultSet result = stmt.executeQuery(strSelect);
        while (result.next()) {
            System.out.println(result.getString("username") + ", "
                    + result.getString("codeval"));
        }
    }




    public void testRetrieval(String username, String password, String email, String dob, String firstname, String lastname) throws SQLException, ClassNotFoundException{

        System.out.println(getPassword(username));
        System.out.println(getEmail(username));
        System.out.println(getDOB(username));
        System.out.println(getFirstName(username));
        System.out.println(getLastName(username));


    }
        public static void main (String[]args) throws SQLException, ClassNotFoundException {
            Database e = new Database();
            e.drop();



        }
}

