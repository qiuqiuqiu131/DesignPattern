package Program.View;

import Application.ConsoleApplication.Annotation.ConsoleCommand;
import Application.ConsoleApplication.ConsoleView.ConsoleViewBase;
import Application.ConsoleApplication.Controller.ViewController.IViewController;
import Application.Event.IEventAggragator;
import Program.Event.ExecuteEvent;
import Program.Service.Interface.IViewService1;

public class View1 extends ConsoleViewBase {
    private final IViewService1 service;
    private final IEventAggragator eventAggragator;

    public View1(IViewController viewController, IViewService1 service, IEventAggragator eventAggragator) {
        super(viewController);
        this.eventAggragator = eventAggragator;
        this.service = service;
    }

    @Override
    protected String GetTitle() {
        return "View1";
    }

    @ConsoleCommand(Name = "Execute_View1", Index = 0)
    public String Execute() {
        eventAggragator.GetEmptyEvent(ExecuteEvent.class).Publish();
        return service.Execute();
    }

}
