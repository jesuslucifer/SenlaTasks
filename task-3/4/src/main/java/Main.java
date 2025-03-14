import config.ApplicationConfig;
import controller.MainController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        MainController mainController = applicationContext.getBean(MainController.class);
        mainController.run();
    }
}