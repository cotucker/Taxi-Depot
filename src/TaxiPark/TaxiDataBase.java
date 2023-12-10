package TaxiPark;

import Cars.*;

import java.util.Scanner;
import java.sql.*;

public class TaxiDataBase {
    Scanner input = new Scanner(System.in);
    Connection conn;
    Statement stat;
    String url;
    String user;
    String password;
    public static void main(String[] args){
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "2234")){
            System.out.println("Connection created--------------");
            Statement stat = conn.createStatement();
            //  stat.executeUpdate("insert into books(id, name) values (2, '2g1c')");
            ResultSet resultSet = stat.executeQuery("select * from taxipark");
            int counter = 0;
            while (resultSet.next()) {
                counter++;
                System.out.println(resultSet.getInt(1) + " | " + resultSet.getString(2));
            }
            System.out.println("Count: " + counter);
        }catch(Exception exception){
            System.out.println("Connection failed--------------");
        }
    }
    public TaxiDataBase(){
        System.out.print("\nConnect to your database ...\n  URL ...\n  User ...\n  Password ...");
        url = input.nextLine();
        System.out.print("\n  User ...");
        user = input.nextLine();
        System.out.print("\n  Password ...");
        password = input.nextLine();
/*        url = "jdbc:postgresql://localhost:5432/postgres";
        user = "postgres";
        password = "2235";*/
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            System.out.println("Connection created--------------");
            this.conn = connection;
            stat = conn.createStatement();
            String sql = """
                    create table taxipark(
                        id int primary key not null,
                        brand varchar(20),
                        model_id text,
                        type text,
                        mass float,
                        hp float,
                        fuelСonsumption float,
                        price float,
                        max_speed float,
                        color text,
                        time1_100 float,
                        clearance float,
                        payload float                   \s
                    );""";
            stat.executeUpdate(sql);
        }catch(Exception exception){
          //  System.out.println("Connection failed--------------");
        }
    }
    public void DataBaseOutput() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        ResultSet resultSet = stat.executeQuery("select " + "*" + " from taxipark");
        System.out.println("Output");
        ResultSetMetaData rsmd = resultSet.getMetaData();
        System.out.println(Space(rsmd.getColumnName(1), (rsmd.getColumnName(1).length() + 6)) + " | " + Space(rsmd.getColumnName(2), rsmd.getColumnName(2).length() + 6) + Space(rsmd.getColumnName(3), rsmd.getColumnName(3).length() + 6) + " | " + Space(rsmd.getColumnName(4), rsmd.getColumnName(4).length() + 6) + "|");
        while (resultSet.next()) {
            System.out.println(Space(resultSet.getString(1), (rsmd.getColumnName(1).length() + 6)) + " | " + Space(resultSet.getString(2), rsmd.getColumnName(2).length() + 6) + Space(resultSet.getString(3), rsmd.getColumnName(3).length() + 6) + " | " + Space(resultSet.getString(4), rsmd.getColumnName(4).length() + 6) + "|");
        }
    }
    public void DataBaseOutputAllInfo() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        ResultSet resultSet = stat.executeQuery("select " + "*" + " from taxipark");
        System.out.println("Output");
        ResultSetMetaData rsmd = resultSet.getMetaData();
        StringBuilder text = new StringBuilder();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            text.append(Space(rsmd.getColumnName(i), rsmd.getColumnName(i).length() + 6)).append(" | ");
        }
        System.out.println(text);
        while (resultSet.next()) {
            text = new StringBuilder();
            String temp;
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                int textSize = rsmd.getColumnName(i).length() + 6;
                if(resultSet.getString(i) == null){
                    temp = Space("", textSize);
                }else {
                    temp = Space(resultSet.getString(i), textSize);
                }
                text.append(Space(temp, textSize)).append(" | ");
            }
            System.out.println(text);
        }
    }
    public void SortedDataBaseOutput() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        stat = conn.createStatement();
        System.out.print("Print taxi park sorted by ...\n 1. Fuel consumption\n 2. Max speed\n 3. Price\n");
        int choice = input.nextInt();
        int tempColume = 0;
        ResultSet resultSet = stat.executeQuery("select " + "*" + " from taxipark");
        ResultSetMetaData rsmd = resultSet.getMetaData();
        tempColume = switch (choice) {
            case (1) -> {
                resultSet = stat.executeQuery("select " + "*" + " from taxipark order by fuelСonsumption DESC");
                yield 7;
            }
            case (2) -> {
                resultSet = stat.executeQuery("select " + "*" + " from taxipark order by max_speed DESC");
                yield 9;
            }
            case (3) -> {
                resultSet = stat.executeQuery("select " + "*" + " from taxipark order by price DESC");
                yield 8;
            }
            default -> tempColume;
        };
        System.out.println(Space(rsmd.getColumnName(1), (rsmd.getColumnName(1).length() + 6)) + " | " + Space(rsmd.getColumnName(2), rsmd.getColumnName(2).length() + 6) + Space(rsmd.getColumnName(3), rsmd.getColumnName(3).length() + 6) + " | " + Space(rsmd.getColumnName(4), rsmd.getColumnName(4).length() + 6) + "|" +  Space(rsmd.getColumnName(tempColume) + "↓", rsmd.getColumnName(tempColume).length() + 6)+ "|");
        while (resultSet.next()){
            System.out.println(Space(resultSet.getString(1), (rsmd.getColumnName(1).length() + 6)) + " | " + Space(resultSet.getString(2), rsmd.getColumnName(2).length() + 6) + Space(resultSet.getString(3), rsmd.getColumnName(3).length() + 6) + " | " + Space(resultSet.getString(4), rsmd.getColumnName(4).length() + 6) + "|" + Space(resultSet.getString(tempColume), rsmd.getColumnName(tempColume).length() + 6) + "|");
        }
    }
    public void DataBaseOutputMinSpeed() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        ResultSet resultSet = stat.executeQuery("select " + "*" + " from taxipark");
        ResultSetMetaData rsmd = resultSet.getMetaData();
        System.out.println("\nEnter minimal acceptable speed ...");
        double min_speed = input.nextInt();
        System.out.println(Space(rsmd.getColumnName(1), (rsmd.getColumnName(1).length() + 6)) + " | " + Space(rsmd.getColumnName(2), rsmd.getColumnName(2).length() + 6) + Space(rsmd.getColumnName(3), rsmd.getColumnName(3).length() + 6) + " | " + Space(rsmd.getColumnName(4), rsmd.getColumnName(4).length() + 6) + "|" +  Space(rsmd.getColumnName(9) + "↓", rsmd.getColumnName(9).length() + 6)+ "|");
        while (resultSet.next()) {
            if(resultSet.getDouble(9) >= min_speed) {
                System.out.println(Space(resultSet.getString(1), (rsmd.getColumnName(1).length() + 6)) + " | " + Space(resultSet.getString(2), rsmd.getColumnName(2).length() + 6) + Space(resultSet.getString(3), rsmd.getColumnName(3).length() + 6) + " | " + Space(resultSet.getString(4), rsmd.getColumnName(4).length() + 6) + "|" + Space(resultSet.getString(9), rsmd.getColumnName(9).length() + 6) + "|");
            }
        }
    }
    public void AddCarToPark() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        stat = conn.createStatement();
        System.out.print("Chose type of the car:\n 1. Sedan\n 2. Pickup\n 3. SUV\n 4. Sportcar\n ");
        int type = input.nextInt();
        Car car;
        String sql;
        int count = 0;
        ResultSet resultSet = stat.executeQuery("select " + "*" + " from taxipark");
        while (resultSet.next()){
            count = resultSet.getInt(1);
        }
        //resultSet.last();
        switch (type){
            case 1:
                car = new Sedan();
                sql = "insert into taxipark(id, brand, model_id, type, mass, hp, fuelСonsumption, max_speed, price, color)\n" +
                      "values(" + (count + 1) + ", " + car._getSpecs() + ")";
                stat.executeUpdate(sql);
                return;
            case 2:
                car = new Pickup();
                sql = "insert into taxipark(id, brand, model_id, type, mass, hp, fuelСonsumption, max_speed, price, color, payload)\n" +
                        "values(" + (count + 1) + ", " + car._getSpecs() + ")";
                stat.executeUpdate(sql);
                return;
            case 3:
                car = new SUV();
                sql = "insert into taxipark(id, brand, model_id, type, mass, hp, fuelСonsumption, max_speed, price, color, clearance)\n" +
                        "values(" + (count + 1) + ", " + car._getSpecs() + ")";
                stat.executeUpdate(sql);
                return;
            case 4:
                car = new Sportcar();
                sql = "insert into taxipark(id, brand, model_id, type, mass, hp, fuelСonsumption, max_speed, price, color, time1_100)\n" +
                        "values(" + (count + 1) + ", " + car._getSpecs() + ")";
                stat.executeUpdate(sql);
                return;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public void DeleteCar() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        this.DataBaseOutputAllInfo();
        int rowNumber;
        rowNumber = input.nextInt();
        stat.executeUpdate("delete from taxipark where id = " + rowNumber + ";");
    }

    public double CostSum()throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        double sum = 0;
        ResultSet resultSet = stat.executeQuery("select " + "*" + " from taxipark");
        while (resultSet.next()){
            sum += resultSet.getFloat("price");
        }
        System.out.println(sum);
        return sum;
    }
    public static String Space(String text, int l){
        StringBuilder s = new StringBuilder();
        for(int i = 1; i <= l-text.length(); i++){
            if( i == (l-text.length())/2+1){
                s.append(text);
            }else{
                s.append(" ");
            }
        }
        return s.toString();
    }

}
