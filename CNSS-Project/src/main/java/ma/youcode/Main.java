package ma.youcode;

import ma.youcode.Controllers.BaseController;

import java.sql.Connection;

public class Main {
    Connection connection;
    public static void main(String[] args) {
        BaseController controller = new BaseController();
        controller.run();
    }
}