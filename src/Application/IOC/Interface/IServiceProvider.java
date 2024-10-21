package Application.IOC.Interface;

public interface IServiceProvider extends IGetService {
    IScopeServiceProvider CreateScope();
}
