package Application.IOC.Interface;

public interface IGetService {
    <T> T GetService(Class<T> clazz) throws Exception;
}
