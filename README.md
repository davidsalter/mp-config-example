# mp-config-example
MicroProfile Configuration Example Application

1. Checkout the initial stage of the project

```
git checkout initial_stage
```

This contains a project skeleton with Java EE 8 configured for TomEE

2. Note that a simple JAXRS endpoint is created at

```
http://localhost:8080/data/hello
```

3. Add the MicroProfile dependency

```
    <dependency>
      <groupId>org.eclipse.microprofile</groupId>
      <artifactId>microprofile</artifactId>
      <version>1.4</version>
      <type>pom</type>
      <scope>provided</scope>
    </dependency>
```

4. Modify HelloController to inject configuration property

```
@Path("/hello")
@Singleton
public class HelloController {
	
	@Inject
	@ConfigProperty(name="application.mode", defaultValue="unknown")
	private String mode;
	
    @GET
    public String sayHello() {
        return "Hello !  Running in mode " + mode;
    }
}
```

5. Create webapp\WEB-INF\beans.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://xmlns.jcp.org/xml/ns/javaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
       bean-discovery-mode="all">
</beans>
```

6. Run TomEE and curl endpoint

```
mvn package tomee:run
curl http://localhost:8080/data/hello
```

Application is running in `unknown` mode

7. Create new file `src\main\resources\META-INF\microprofile-config.properties`

```
application.mode=dev
```

8. Run TomEE and curl endpoint

```
mvn package tomee:run
curl http://localhost:8080/data/hello
```

Application is running in `dev` mode

9. Set environment variable

```
export APPLICATION_MODE=qa
```

10. Run TomEE and curl endpoint

```
mvn package tomee:run
curl http://localhost:8080/data/hello
```

Application is running in `qa` mode

11. Add configuration option to pom.xml
```
<configuration>
              <args>-Dapplication_mode=prod</args>
```

11. Run TomEE and curl endpoint

```
mvn package tomee:run
curl http://localhost:8080/data/hello
```

Application is running in `prod` mode
