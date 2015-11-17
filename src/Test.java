import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Benjamin on 12-11-15 at 14:21
 */

public class Test extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setScene( new Scene(new Group(), 500,500));
        stage.show();
    }
}
