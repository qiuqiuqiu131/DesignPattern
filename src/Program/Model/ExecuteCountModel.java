package Program.Model;

import Application.Event.IEventAggragator;
import Program.Event.ExecuteEvent;

public class ExecuteCountModel implements IExecuteCountModel {
    private int count = 0;

    public ExecuteCountModel(IEventAggragator eventAggragator) {
        eventAggragator.GetEmptyEvent(ExecuteEvent.class)
                .Subscribe(this::Execute);
    }

    private void Execute() {
        count++;
    }

    @Override
    public int GetCount() {
        return count;
    }

}
