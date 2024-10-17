package Application.ConsoleApplication;

import java.io.File;
import java.nio.file.Path;

import Application.ConsoleApplication.ConsoleView.IConsoleView;
import Application.IOC.Interface.IServiceCollection;

public class ConsoleApplicationExtension {
    public static void AddConsoleViews(IServiceCollection serviceCollection, String pack) {
        File folder = new File(Path.of("bin", pack).toString());
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".class"));
            if (files == null)
                return;
            for (File file : files) {
                try {
                    String className = file.getName().replace(".class", "");
                    Class<?> clazz = Class.forName(pack.replace('/', '.') + "." + className);
                    if (IConsoleView.class.isAssignableFrom(clazz)) {
                        serviceCollection.RegisterTransient(clazz);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
