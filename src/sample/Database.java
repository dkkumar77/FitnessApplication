package sample;
import java.sql.*;
class Database {


    Connection connection;

    Database() throws SQLException {
        if (!exists()) {
            createDatabase();


        }


    }

    final String DB_URL = "jdbc:mysql://localhost:3306/";
    final String USER = "root";
    final String PASS = "Tyrantboys1";


    public boolean exists() throws SQLException {

        String databaseName = "";

        try {
            connection = DriverManager.getConnection(DB_URL , USER , PASS);

            ResultSet resultSet = connection.getMetaData().getCatalogs();


            while (resultSet.next()) {
                databaseName = resultSet.getString(1);
            }

            if (databaseName != null) {
                resultSet.close();
                return true;
            }

        }
        catch(NullPointerException e )

        {
            e.printStackTrace();


        }

        return false;

    }

    public void createDatabase() {

        try {
            connection = DriverManager.getConnection(DB_URL + USER + PASS);
            Statement stmt = connection.createStatement();
            stmt.executeQuery("CREATE DATABASE FitnessData");

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }


    }

    /**
     * USERNAME
     * PASSWORD
     * <p>
     * TABLE - > DATES - > WEIGHT | HEIGHT | CALORIE INTAKE | PROTEIN | FAT | CARB
     * <p>
     * <p>
     * <p>
     * /
     */

    public static void main(String[] args) throws SQLException {
        Database e = new Database();

    }
}
