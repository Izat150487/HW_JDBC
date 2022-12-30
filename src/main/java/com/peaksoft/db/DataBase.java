package com.peaksoft.db;

import com.peaksoft.model.City;
import com.peaksoft.model.Country;
import com.peaksoft.model.Mayor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static final String url = "jdbc:postgresql://localhost:5432/testhw";
    private static final String login = "postgres";
    private static final String password = "izi12345";

    public static Connection connection(){
        Connection connection = null;
        try {
            connection= DriverManager.getConnection(url,login,password);
            System.out.println("Connection ");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
    public static void createTableMayor(){
        String SQL = "CREATE TABLE IF NOT EXISTS mayor(" +
                "id INT PRIMARY KEY," +
                "name VARCHAR (50) NOT NULL ," +
                "age INT," +
                "position VARCHAR(30) NOT NULL)";
        try(Connection connect= DataBase.connection();
            Statement statement = connect.createStatement()){
            statement.executeUpdate(SQL);
            System.out.println("connection ");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public static void  insertTableMayor(int id,String name, int age, String position){
        String SQL = "INSERT INTO mayor(id,name,age,position) VALUES(?,?,?,?)";
        try(Connection connection = connection();
            PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.setInt(3,age);
            statement.setString(4,position);
            statement.executeUpdate();
            System.out.println("Successfully " + name );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Mayor> getAllMayors() {
        String sql = "SELECT * FROM mayor";
        List<Mayor> mayors = new ArrayList<>();
        try (Connection conn = DataBase.connection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Mayor mayor = new Mayor();
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String position = resultSet.getString("position");
                mayor.setId(id);
                mayor.setName(name);
                mayor.setAge(age);
                mayor.setPosition(position);
                mayors.add(mayor);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mayors;
    }

    //City
    public static void createTableCities(){
        String SQL = "CREATE TABLE IF NOT EXISTS cities(" +
                " id INT PRIMARY KEY," +
                " name VARCHAR(50) NOT NULL," +
                " mayor_id INT REFERENCES mayor(id)," +
                "country_id INT REFERENCES countries(id));";
        try(Connection connect = DataBase.connection();
                Statement statement = connect.createStatement()){
            statement.executeUpdate(SQL);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void insertCityTable(int id,String name,int mayor_id,int country_id){
        String SQL = "INSERT INTO cities(id,name) VALUES(?,?,?,?)";
        try(Connection connection = DataBase.connection();
            PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.setInt(3,mayor_id);
            statement.setInt(4,country_id);
            statement.executeUpdate();
            System.out.println("Successfully " + name );
        }catch (SQLException e){
            System.out.println(e.getMessage() + "Error");
        }
    }
    public static List<City> callByIdCities(int id){
        City city = new City();
        String SQL_ID = "SELECT * FROM cities WHERE id =" +id ;
        List<City> cities = new ArrayList<>();
        try(Connection connect = DataBase.connection();
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_ID)){
            while (resultSet.next()){
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString("name"));
                city.setMayorId(resultSet.getInt("mayor_id"));
                city.setCountryId(resultSet.getInt("country_id"));
                cities.add(city);

            }
        }catch (SQLException e){
            System.out.println(e.getMessage() + "Not call by id");
        }

        return cities;
    }
    public static List<City> getAllCity(){
        String sql = "SELECT * FROM cities";
        List<City> cities = new ArrayList<>();
        try (Connection connection = DataBase.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                City city = new City();
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int mayor_id = resultSet.getInt("mayor_id");
                int country_id = resultSet.getInt("country_id");
                city.setId(id);
                city.setName(name);
                city.setMayorId(mayor_id);
                city.setCountryId(country_id);
                cities.add(city);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return cities;
    }
    //Country
    public static void createTableCountry(){
        String SQL = "CREATE TABLE IF NOT EXISTS countries(" +
                " id INT PRIMARY KEY," +
                " name VARCHAR(50) NOT NULL UNIQUE," +
                " city_id INT REFERENCES  city(id));";
        try(Connection connect= DataBase.connection();
            Statement statement = connect.createStatement()){
            statement.executeUpdate(SQL);
            System.out.println("connection ");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public static void insertCountryTable(int id,String name,int country_id){
        String SQL = "INSERT INTO countries(id,name) VALUES(?,?,?)";
        try(Connection connection = connection();
            PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.setInt(3,country_id);
            statement.executeUpdate();
            System.out.println("Successfully " + name );
        }catch (SQLException e){
            System.out.println(e.getMessage() + "Error");
        }
    }
    public static  List<Country> getAllCountry() {
        Country country = new Country();
        String SQL_COUNTRY = "SELECT * FROM countries";
        List<Country> countries = new ArrayList<>();
        try (Connection connect = DataBase.connection();
             Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_COUNTRY)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int country_id = resultSet.getInt("country_id");
                countries.add(country);
                System.out.println(id + " " + name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } return countries;
    }
}