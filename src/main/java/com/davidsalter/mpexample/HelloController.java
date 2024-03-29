package com.davidsalter.mpexample;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 */
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
