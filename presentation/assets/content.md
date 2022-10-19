# Dropwizard
Create `High Performance` web applications



## Why Dropwizard?

  > Its goal is to provide performant, reliable implementations of everything a production-ready web application needs.


[Dropwizard vs Springboot](https://dzone.com/articles/dropwizard-vs-spring-boot)
* Very similar to Springboot
* Full control over your beans
* Quick project startup
* Doesn't allow you a lot of freedom of choice <!-- .element: class="fragment highlight-red"  -->




## Our Sample Project
![Architecture](/assets/hello-world-architecture.png "Project architecture")


### Convention over configuration
| Component |     Description     |
|-----------|:-------------------:|
| Jetty     |     Web Server      |
| Jersey    | JAX-RS Impl library |
| Jackson   |     JSON Mapper     |
| Others    |  Metrics / Logs...  |

[dropwizard.io](https://www.dropwizard.io/en/latest/getting-started.html)


![Folder Structure ](/assets/folder-structure.png "Project Folder Structure")


### Dropwizard bootstrapping
![dropwizard bootstrap](/assets/plantuml/bootstrap.png "Dropwizard bootstraping")


<!-- .slide: data-transition="fade" -->
```java [1-3|5-8|10-19]
    public static void main(String[] args) throws Exception{
        new HelloWorldApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addBundle(databaseBundle());
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new HelloResourceImpl());
        environment.jersey().register(UserResourceImpl.class);
        
        environment.healthChecks()
            .register("BasicDropWizardHealthCheck", 
                new HelloWorldHealthCheck(configuration)
        );
    }
```


<!-- .slide: data-transition="fade" -->
Adding new Bundles
```java[3,5-7,13|15-17]
    ...
    protected HelloWorldJdbiBundle<HelloWorldConfiguration> databaseBundle() {
        return new HelloWorldJdbiBundle<>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(HelloWorldConfiguration config) {
                return config.getDatabaseConfiguration();
            }
        };
    }
    ...
    
    public abstract class HelloWorldJdbiBundle<T extends Configuration> implements ConfiguredBundle<T> {
        public abstract DatabaseConfiguration getDatabaseConfiguration(T config);
        @Override
        public void run(T config, Environment environment) throws Exception {
            ...
        }
    }
```


<!-- .slide: data-transition="fade" -->
Run method is called automatically by Bootrstrap
```java[3|5|6-7|9-10|12-18]
    @Override
    public void run(T config, Environment environment) throws Exception {
        var dbConfiguration = getDatabaseConfiguration(config);

        migrateDatabase(dbConfiguration, environment);
        var jdbi = new JdbiFactory()
                .build(environment, dbConfiguration.getDataSourceFactory(), "hello-world-db-jdbi");

        var healthCheckDao = jdbi.onDemand(HealthCheckDao.class);
        var userDao = jdbi.onDemand(UserDao.class);

        environment.healthChecks().register("db-health", new DatabaseHealthCheck(healthCheckDao));
        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(new UserServiceImpl(userDao)).to(UserService.class);
            }
        });
    }
```




## REST Endpoints


<!-- .slide: data-transition="fade" -->
```java[3]
@Override
public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
    environment.jersey().register(new HelloResourceImpl());
    environment.jersey().register(UserResourceImpl.class);
    
    environment.healthChecks()
        .register("BasicDropWizardHealthCheck", 
            new HelloWorldHealthCheck(configuration)
    );
}
```


<!-- .slide: data-transition="fade" -->
Contains standard `javax.ws.rs` annotations
```java[]
@Path("v1")
public interface HelloResource {

    @Path("/hello/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response get(@Valid @NotNull @PathParam("name") String name);
}
```


<!-- .slide: data-transition="fade" -->
Automatically converts entity to `JSON`
```java[]
public class HelloResourceImpl implements HelloResource {
    @Override
    public Response get(String name) {
        return Response.ok(
            HelloWorldResponse.builder().message("Hello " + name).build()
        ).build();
    }
}
```


<!-- .slide: data-transition="fade" -->
We can inject using HK2 DI Implementation
```java[5,6,7,8]
public class UserResourceImpl implements UserResource {

    private final UserService userService;

    @Inject
    public UserResourceImpl(UserService userService) {
        this.userService = userService;
    }
    ...
```


## Thanks!
Source code [Github](http://)