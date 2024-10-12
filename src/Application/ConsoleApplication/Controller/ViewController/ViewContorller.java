package Application.ConsoleApplication.Controller.ViewController;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import Application.ConsoleApplication.ConsoleView.IConsoleView;
import Application.IOC.Interface.IServiceProvider;

public class ViewContorller implements IViewController {
    private final IServiceProvider serviceProvider;

    private Stack<IConsoleView> ViewStack = new Stack<>();

    private Map<Class<?>, Object> ViewCollection = new HashMap<>();

    public ViewContorller(IServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public void Push(Class<?> view) throws Exception {
        if (!IConsoleView.class.isAssignableFrom(view)) {
            throw new IllegalArgumentException("The view must implement IConsoleView interface");
        }
        if (ViewCollection.containsKey(view)) {
            ViewStack.push((IConsoleView) ViewCollection.get(view));
        }
        Object obj = serviceProvider.GetService(view);
        ViewCollection.put(view, obj);
        ViewStack.push((IConsoleView) obj);
    }

    public void Pop() {
        ViewStack.pop();
    }

    public IConsoleView GetView() {
        if (ViewStack.empty())
            System.exit(0);
        return ViewStack.peek();
    }
}
