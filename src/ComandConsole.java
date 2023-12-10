import TaxiPark.TaxiDataBase;

import java.sql.SQLException;
import java.util.Scanner;

public class ComandConsole{
    Scanner input = new Scanner(System.in);
    public TaxiDataBase taxipark;
    public ComandConsole() throws SQLException {
        taxipark = new TaxiDataBase();
        String command;
        System.out.print("\nCommands:\n/quit\n/addcar\n/deletecar\n/print\n/printall\n/prinparkcost\n/printsorted\n/help\n/minspeed");
        while(true){
            System.out.println("\n ... ");
            command = input.nextLine();
            switch (command){
                case ("/quit"):
                    System.out.println(" ... U quit ... ");
                    return;
                case ("/addcar"):
                    taxipark.AddCarToPark();
                    break;
                case ("/deletecar"):
                    taxipark.DeleteCar();
                    break;
                case ("/minspeed"):
                    taxipark.DataBaseOutputMinSpeed();
                    break;
                case ("/print"):
                    taxipark.DataBaseOutput();
                    break;
                case ("/printall"):
                    taxipark.DataBaseOutputAllInfo();
                    break;
                case ("/prinparkcost"):
                    taxipark.CostSum();
                    break;
                case ("/printsorted"):
                    taxipark.SortedDataBaseOutput();
                    break;
                case ("/help"):
                    System.out.print("\nCommands:\n/quit\n/addcar\n/deletecar\n/print\n/printall\n/prinparkcost\n/printsroted\n/help");
                    break;
                default:
                    System.out.println("There is no such command");
                    break;
            }
        }
    }

}
