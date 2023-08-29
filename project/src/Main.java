import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Files.ReadDriverData();

        ConsoleUi CI = new ConsoleUi();
        CI.startPage();


       Files.WriteDriverdata();
    }
}