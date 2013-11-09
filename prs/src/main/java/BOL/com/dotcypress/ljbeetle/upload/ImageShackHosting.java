package BOL.com.dotcypress.ljbeetle.upload;



import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Manages uploading images to image shack
 * Modified Stuart Mathews <stuart@stuartmathews.com>
 */
public class ImageShackHosting 
{
    /**
     * My private API to ImageShack image services.
     */
    private static final String API_KEY = "3AHOSUVYcbd59e9763266693e515ff1e8c97169e";
    private static final String URI = "http://www.imageshack.us/upload_api.php";

    /**
     * Upload an images as bytes to ImageShack
     * @param bytes which is the raw image
     * @return URL on image shacks servers
     */    
    public static String upload(byte[] bytes) 
    {
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****ISBoundarydoyCypress*******";

        try 
        {
            InputStream is = new ByteArrayInputStream(bytes);

            URL url = new URL(URI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setConnectTimeout(Config.CONNECTION_TIMEOUT);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            connection.connect();

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"key\"" + lineEnd + lineEnd);
            outputStream.writeBytes(API_KEY + lineEnd);

            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"fileupload\"; filename=\"file.jpg\"" + lineEnd);
            outputStream.writeBytes("Content-Type: image/jpeg" + lineEnd);
            outputStream.writeBytes("Content-Transfer-Encoding: binary" + lineEnd + lineEnd);
            int bytesAvailable;
            while ((bytesAvailable = is.available()) > 0) {
                    int bufferSize = Math.min(bytesAvailable, 4096);
                    byte[] buffer = new byte[bufferSize];
                    int bytesRead = is.read(buffer, 0, bufferSize);
                    outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.writeBytes(lineEnd + twoHyphens + boundary + twoHyphens + lineEnd);
            is.close();
            outputStream.flush();
            outputStream.close();
            int respcode = connection.getResponseCode();
            //Logger.verbose(String.format("ImageShack response code: %s", respcode));

            InputStream inputStream = connection.getInputStream();

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document dom = builder.parse(inputStream);
            inputStream.close();

            Element root = dom.getDocumentElement();
            NodeList list = root.getElementsByTagName("image_link");
            if (list.getLength() > 0) {
                    NodeList childs = list.item(0).getChildNodes();
                    StringBuilder html = new StringBuilder();
                    for (int pos = 0; pos < childs.getLength(); pos++) {
                            html.append(childs.item(pos).getNodeValue());
                    }
                    //Logger.verbose(html.toString());
                    return String.format("%s", html);
            }

        //} catch (ClientProtocolException e) {
                //Logger.error("ImageShackHosting HTTP error", e);
        } catch (IOException e) {
                //Logger.error("ImageShackHosting HTTP error", e);
        } catch (Throwable e) {
                // Logger.error("ImageShackHosting upload fault ", e);
        }
        return null;
    }
}