package webservices;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// adnotarea ApplicationPath specifica calea URL spre serviciile web
// e.g., @ApplicationPath("rest") => http://localhost:8080/ProiectServiciiWeb/rest/
@ApplicationPath("api")
public class MyApplication extends Application {

}