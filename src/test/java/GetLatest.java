import com.serverjars.api.JarDetails;
import com.serverjars.api.request.LatestRequest;
import com.serverjars.api.response.LatestResponse;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 7:40 PM
 */
public class GetLatest {

    public static void main(String[] args) {
        LatestResponse latestResponse = new LatestRequest("spigot").send();
        if (latestResponse.isSuccess()) {
            JarDetails jarDetails = latestResponse.getLatestJar();
            System.out.println(jarDetails.toString());
        } else {
            System.out.println(
                    latestResponse.getErrorTitle() + ": " +
                            latestResponse.getErrorMessage()
            );
        }
    }

}
