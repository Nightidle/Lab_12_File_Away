import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver
{
    public static void main(String[] args)
    {
        ArrayList<String> records = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        boolean done = false;

        while(!done)
        {
            String firstName = SafeInput.getNonZeroLenString(in, "Enter First Name");

            String lastName = SafeInput.getNonZeroLenString(in, "Enter Last Name");

            String id = SafeInput.getRegExString(in, "Enter 6-digit ID", "\\d{6}");

            String email = SafeInput.getNonZeroLenString(in, "Enter Email");

            int yearBorn = SafeInput.getRangedInt(in, "Enter Year Born", 1900, 2025);

            String record = firstName + "," + lastName + "," + id + "," + email + "," + yearBorn;

            records.add(record);

            done = SafeInput.getYNConfirm(in, "Are you finished entering records?");
        }

        String fileName = SafeInput.getNonZeroLenString(in, "Enter output file name") + ".csv";

        Path file = Path.of("src", fileName);

        try
        {
            BufferedWriter writer = Files.newBufferedWriter(file);

            for(String record : records)
            {
                writer.write(record);
                writer.newLine();
            }

            writer.close();

            System.out.println("File saved successfully.");
            System.out.println("Location: " + file.toAbsolutePath());
        }
        catch(IOException e)
        {
            System.out.println("Error writing file.");
            e.printStackTrace();
        }
    }
}