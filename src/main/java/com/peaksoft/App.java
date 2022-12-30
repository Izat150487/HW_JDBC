package com.peaksoft;

import java.sql.SQLException;

import static com.peaksoft.db.DataBase.*;

/**
 * Hello world!
 */
public class App {
    public static void main( String[] args ) throws SQLException {

       createTableCities();
        createTableCountry();
        createTableMayor();
        // MAYOR TABLE
        insertTableMayor(1,"Altai Kulginov", 53, "Mayor");
        insertTableMayor(2,"Almaz Mambetov", 46, "Mayor");
        insertTableMayor(3,"Aibek Djunushaliev", 52, "Mayor");
        insertTableMayor(4,"Akbar Shukurov", 38, "Mayor");
        insertTableMayor(5,"Johongir Abidovich", 47, "Mayor");


        //county
        insertCountryTable(1,"Kyrgyzstan",1);
        insertCountryTable(2,"Uzbekistan",2);
        insertCountryTable(3,"Kazakhstan",1);

        //cities
        insertCityTable(1,"Osh",2,1);
        insertCityTable(2,"Astana",1,3);
        insertCityTable(3,"Bishkek",3,1);
        insertCityTable(4,"Samarqand",4,2);
        insertCityTable(5,"Tashkent",5,2);

        getAllMayors();
        getAllCountry();
        getAllCity();

        //CallById city

        System.out.println(callByIdCities(3));
    }
}
