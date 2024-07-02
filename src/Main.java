import Repository.RepoFactory;
import View.Login;

public class Main {

    public static void main(String[] args) {
        RepoFactory repoFactory = new RepoFactory();

        Login login = new Login(repoFactory);
    }

}