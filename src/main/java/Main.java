import java.io.File;

/**
 * Created by Владислав on 29.11.2015.
 */
public class Main extends ClassLoader{
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        File f = new File("config.xml");
        Class cls = new NetworkClassLoader().findClass("Starter");
        StarterInterface starter = (StarterInterface)cls.newInstance();
        starter.start(f);
    }
}
