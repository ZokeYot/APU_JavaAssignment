import Repository.RepoFactory;
import View.RegisterView;

public class Main {

    public static void main(String[] args) {
        RepoFactory repoFactory = new RepoFactory();

        RegisterView register = new RegisterView(repoFactory);


    }

}