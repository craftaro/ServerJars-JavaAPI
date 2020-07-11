import com.serverjars.api.request.TypesRequest;
import com.serverjars.api.response.TypesResponse;

import java.util.List;
import java.util.Map;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 7:40 PM
 */
public class GetTypes {

    public static void main(String[] args) {
        TypesResponse typesResponse = new TypesRequest().send();
        if (typesResponse.isSuccess()) {
            if(!typesResponse.isChildren()) {
                // When requesting all categories and types
                Map<String, List<String>> typesMap = typesResponse.getAllTypes();
                System.out.println(typesMap);
            }
        } else {
            System.out.println(
                typesResponse.getErrorTitle() + ": " +
                typesResponse.getErrorMessage()
            );
        }
        typesResponse = new TypesRequest("servers").send();
        if (typesResponse.isSuccess()) {
            if (typesResponse.isChildren()) {
                // When requesting children of a category
                List<String> children = typesResponse.getRequestedChildren();
                System.out.println(children);
            }
        } else {
            System.out.println(
                typesResponse.getErrorTitle() + ": " +
                typesResponse.getErrorMessage()
            );
        }
    }

}
