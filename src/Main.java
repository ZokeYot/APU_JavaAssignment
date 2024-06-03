import Repository.RepoFactory;

import View.Register;

public class Main {

    public static void main(String[] args) {
        RepoFactory repoFactory = new RepoFactory();

        Register register = new Register(repoFactory);

    }

}