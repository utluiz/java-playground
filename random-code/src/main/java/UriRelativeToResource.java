import java.net.URI;

/**
 * Calculates the URI of a resource specified in a CSS file, which is relative to the CSS URI.
 */
public class UriRelativeToResource {

    private static String getResourceUriRelativeToStylesheet(String resourceUrl, URI cssUri) {
        String relativePath = cssUri.getPath().substring(0, cssUri.getPath().lastIndexOf('/') + 1);
        return URI.create(relativePath + resourceUrl).normalize().toString();
    }

    public static void main(String[] args) {
        String resourceUrl = "../../../images/gadgets/loading.gif";
        URI cssUri = URI.create("//abc.cdn.net/bla.bla.bla/s/path/_/download/css/batch.css?param=1&param=2");
        System.out.println(getResourceUriRelativeToStylesheet(resourceUrl, cssUri));

    }
}
