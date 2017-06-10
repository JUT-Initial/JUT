import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by Epul on 6/10/2017.
 */
public class DataSourceImporter {

    //get from properties
    private static final String JSON_DIRECTORY_KEY = "json.directory";
    private static final String PROPERTIES_FILE_NAME = "dsImporter.properties";
    private static final String PRETTY_PRINT = "?pretty";

    //get from properties
    private static final String ELASTIC_SEARCH_SERVER_URL_KEY = "es.server.url";

    Properties properties;

    public DataSourceImporter() throws IOException {
        properties = readPropertiesFromFile(PROPERTIES_FILE_NAME);
    }

    public void uploadDataset() throws Exception {

        final File folder = new File(properties.getProperty(JSON_DIRECTORY_KEY));

        String serverURL = properties.getProperty(ELASTIC_SEARCH_SERVER_URL_KEY);

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) { //first directories are are elastic index...
                String urlWithElasticIndex = serverURL + "/" + fileEntry.getName();
                for (final File innerFileEntry : fileEntry.listFiles()) {
                    if (innerFileEntry.isDirectory()) { //second directories are elastic type....
                        String urlWithElasticIndexAndType = urlWithElasticIndex + "/" + innerFileEntry.getName();
                        uploadDocumentsThroughRest(urlWithElasticIndexAndType, Arrays.asList(innerFileEntry.listFiles()));
                    }
                }
            }
        }
    }

    private Properties readPropertiesFromFile(String filename) throws IOException {
        Properties prop;
        InputStream inputStream = null;

        try {
            prop = new Properties();
            inputStream = getClass().getResourceAsStream(filename);
            prop.load(inputStream);
        } finally {
            inputStream.close();

        }
        return prop;
    }

    private String getFilenameWithoutExtension(File file) {

        String name = null;
        int pos = file.getName().lastIndexOf(".");
        if (pos != -1) {
            name = file.getName().substring(0, pos);
        }
        return name;
    }

    private boolean isValidResponseCode(int responseCode) {
        //when document is first created, response code is 201, overwriting document returns response code 200
        if ((responseCode != HttpURLConnection.HTTP_CREATED) || (responseCode != HttpURLConnection.HTTP_OK)) {
            return false;
        }
        return true;
    }

    private void uploadDocumentsThroughRest(String urlString, List<File> docsToUpload) throws IOException {
        HttpURLConnection conn = null;
        try {
            for (File document : docsToUpload) {
                String filename = getFilenameWithoutExtension(document);
                URL url = new URL(urlString + "/" + filename + PRETTY_PRINT); //the json file name is the document id in elastic...
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Content-Type", "application/json");

                FileInputStream fis = new FileInputStream(document);
                byte[] data = new byte[(int) document.length()];
                fis.read(data);
                fis.close();

                //upload the json through rest...
                String input = new String(data, "UTF-8");
                OutputStream os = conn.getOutputStream();
                os.write(input.getBytes());
                os.flush();


                if (isValidResponseCode(conn.getResponseCode())) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
