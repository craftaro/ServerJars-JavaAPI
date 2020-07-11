import com.serverjars.api.Response;
import com.serverjars.api.request.JarRequest;

import java.io.File;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 8:51 PM
 */
public class GetJar {

    public static void main(String[] args) {
        Response jarResponse = new JarRequest("spigot", new File("exampleserver.jar")).send();
        if(jarResponse.isSuccess()) {
            System.out.println("Download Success!");
        } else {
            System.out.println(
                jarResponse.getErrorTitle() + ": " +
                jarResponse.getErrorMessage()
            );
        }
    }

}
