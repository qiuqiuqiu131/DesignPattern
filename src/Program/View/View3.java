package Program.View;

import Application.ConsoleApplication.Annotation.ConsoleCommand;
import Application.ConsoleApplication.ConsoleView.ConsoleViewBase;
import Application.ConsoleApplication.Controller.ViewController.IViewController;
import Application.Event.IEventAggragator;
import Program.Event.ExecuteEvent;
import Program.Service.Interface.IViewService3;

public class View3 extends ConsoleViewBase {
    private IViewService3 service;
    private IEventAggragator eventAggragator;

    public View3(IViewController viewController, IViewService3 service, IEventAggragator eventAggragator) {
        super(viewController);
        this.service = service;
    }

    @Override
    protected String GetTitle() {
        return "View3";
    }

    @ConsoleCommand(Name = "Execute_View3", Index = 0)
    public String Execute() {
        eventAggragator.GetEmptyEvent(ExecuteEvent.class).Publish();
        return service.Execute();
    }

}
