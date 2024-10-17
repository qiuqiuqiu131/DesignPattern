package Program.View;

import Application.ConsoleApplication.Annotation.ConsoleCommand;
import Application.ConsoleApplication.ConsoleView.ConsoleViewBase;
import Application.ConsoleApplication.Controller.ViewController.IViewController;
import Application.Event.IEventAggragator;
import Application.IOC.Dependence;
import Program.Event.ExecuteEvent;
import Program.Service.Interface.IViewService2;

public class View2 extends ConsoleViewBase {

    @Dependence
    private IViewService2 service;

    private IEventAggragator eventAggragator;

    public View2(IViewController viewController, IEventAggragator eventAggragator) {
        super(viewController);
        this.eventAggragator = eventAggragator;
    }

    @Override
    protected String GetTitle() {
        return "View2";
    }

    @ConsoleCommand(Name = "Execute_View2", Index = 0)
    public String Execute() {
        eventAggragator.GetEmptyEvent(ExecuteEvent.class).Publish();
        return service.Execute();
    }
}
