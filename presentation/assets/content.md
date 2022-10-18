# Dropwizard
An alternative to develop web applications



## What is Dropwizard

  > Dropwizard straddles the line between being a library and a framework.

Its goal is to provide performant, reliable implementations of everything a production-ready web application needs.


### Out of the box
| Library   |      Description      |
|----------|:-------------:|
| Jetty |  Web Server |
| Jersey |    JAX-RS Impl   |
| Jackson | JSON Mapper |
| Others | Metrics / Logs |

[dropwizard.io](https://www.dropwizard.io/en/latest/getting-started.html)



## Sample Project


### Initialization
![dropwizard bootstrap](/assets/plantuml/bootstrap.png "Dropwizard bootstraping")


```java [1|2-3]
    public static void main(String[] args) {
      var x = 0;
      System.out.println(x);
    }
    ```
