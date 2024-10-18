# 设计模式大作业

实现了三个组件，帮助快速开发程序。
Applicatoin 下 ConsoleApplication(控制台应用)、EventAggragator(事件分发器)、IOC 容器。

## 一、IOC 容器

仿照 Microsoft.Extenstion.DependenceInjection 包，编写一个 IOC 容器组件。
两个核心类：ServiceCollection,ServiceProvider

### 1、ServiceCollection

用于服务的配置注册，在注册服务时会生成对应服务的描述符，存入服务描述符数组。
ServiceCollection 在 BuildServiceProvider()后会将服务描述符数组传入 ServiceProvider，由 ServiceProvider 实现服务对象的创建和管理。

```java
IServiceCollection builder = new ServiceCollection();
builder.RegisterTransient(IMyService.class,MyService.class);
builder.RegisterSingleton(MySingletonService.class);

IServiceProvider services = builder.BuildServiceProvider();
```

如上代码中：

1. 在 IOC 容器中注册了 MyService 类,作为瞬态服务，使用 IMyService 接口作为 MyService 的索引，只能用接口拿取对应的服务实例。
2. 在 IOC 容器中注册了 MySingletonService 类,作为单例服务，这里并没用使用接口，可以直接用类拿取服务实例。

### 2、ServiceProvider

用于对 ServiceCollection 提供的注册服务进行示实例创建、生命周期管理等功能。
我们可以通过 GetService()函数后去服务实例。

```java
// 继续上述代码
IMyService service1 = services.GetService(IMyService.class);
MySingletonService service2 = services.GetService(MySingletonService.class);
```

### 3、依赖注入的支持

#### （1）、构造函数注入

```java
public class NumberCount
{
    private int number;
}

public class NumberService
{
    private NumberCount count;

    //在构造函数的参数列表中添加需要依赖的对象，IOC会自动注入
    public NumberService(NumberCount count)
    {
        this.count = count;
    }
}

public static Main()
{
    IServiceCollection builder = new ServiceCollection();
    builder.RegisterSingleton(NumberCount.class);
    builder.RegisterTransient(NumberService.class);

    IServiceProvider services = builder.BuildServiceProvider();
}
```

#### （2）、注解注入

```java
public class NumberCount
{
    private int number;
}

public class NumberService
{
    // 为字段添加注解，IOC会自动为这个注解添加实例
    @Dependence
    private NumberCount count;
}

public static Main()
{
    IServiceCollection builder = new ServiceCollection();
    builder.RegisterSingleton(NumberCount.class);
    builder.RegisterTransient(NumberService.class);

    IServiceProvider services = builder.BuildServiceProvider();
}
```

#### （3）、IServiceProvider 获取

```java
public class NumberCount
{
    private int number;
}

public class NumberService
{
    private NumberCount count;

    //在构造函数的参数列表中添加IServiceProvider，通过IServiceProvider获取实例
    public NumberService(IServiceProvider provider)
    {
        count = provider.GetService(NumberCount.class);
    }
}

public static Main()
{
    IServiceCollection builder = new ServiceCollection();
    builder.RegisterSingleton(NumberCount.class);
    builder.RegisterTransient(NumberService.class);

    IServiceProvider services = builder.BuildServiceProvider();
}
```

## 二、EventAggragator（事件分发器）

观察者模式，略

## 三、ConsoleApplication

### 1、控制器

使用了 IOC 容器和 EventAggragator。

内置了三个控制器，ViewController(视图控制器:管理视图栈)、ConsoleController(控制台控制器:管理控制台运行逻辑)、IOContorller(输入输出控制器:管理控制台的输入输出)。

### 2、视图基类(ConsoleViewBase)

提供了视图基类，项目中的视图需要继承 ConsoleViewBase。

### 3、如何使用
