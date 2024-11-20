import io.github.cdimascio.dotenv.Dotenv;

public class Main
{
    public static void main(String[] args)
    {
        // System.out.println("Current Directory: " + System.getProperty("user.dir"));

        Dotenv dotenv = Dotenv.configure()
                .directory("./")
                .load();

        System.out.println("DB_URL: " + dotenv.get("DB_URL"));
        System.out.println("DB_USER: " + dotenv.get("DB_USER"));
        System.out.println("DB_PASSWORD: " + dotenv.get("DB_PASSWORD"));
    }
}