import controller.LoginController;
import controller.RegisterController;
import controller.VaccineController;
import view.LoginView;
import view.RegisterView;
import view.VaccineView;

import java.awt.EventQueue;


public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView view = new LoginView();
                VaccineView vaccineView = new VaccineView();
                RegisterView registerView = new RegisterView();
                LoginController controller = new LoginController(view, registerView, vaccineView);
                RegisterController registerController = new RegisterController(view,registerView);
                VaccineController vaccineController = new VaccineController(vaccineView,view);
                controller.showLoginView();
            }
        });
    }
}