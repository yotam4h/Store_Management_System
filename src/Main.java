import io.github.cdimascio.dotenv.*;

public class Main
{
    public static void main(String[] args)
    {
        // Load the .env file
        Dotenv dotenv = Dotenv.configure().load();

        // Database connection details
        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

    }
}