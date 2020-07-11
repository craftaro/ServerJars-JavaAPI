import com.serverjars.api.JarDetails;
import com.serverjars.api.request.AllRequest;
import com.serverjars.api.response.AllResponse;

import java.util.List;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 8:00 PM
 */
public class GetAll {

    public static void main(String[] args) {
        AllResponse latestResponse = new AllRequest("spigot").send();
        if (latestResponse.isSuccess()) {
            List<JarDetails> jars = latestResponse.getJars();
            System.out.println("{");
            for (JarDetails jar : jars) {
                System.out.println("  " + jar);
            }
            System.out.println("}");
        } else {
            System.out.println(
                    latestResponse.getErrorTitle() + ": " +
                            latestResponse.getErrorMessage()
            );
        }
    }

}
