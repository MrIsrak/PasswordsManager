import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileFuncs {
    public static void checkFile() throws IOException {
        File file = new File(Initialization.filePath);
        if (!file.exists()) {file.createNewFile();}
        if (file.length() == 0){
            JSONObject inventory = new JSONObject();
        }
    }
    public static void writeFile(String pass, String email, String site) throws Exception {
        // Encrypt the password using the provided secret key and initialization vector.
        byte[] encPass = EncryptDecrypt.encrypt(pass, Initialization.secretKey, Initialization.ivParameterSpec);

        // Updating variables
        loadData(email);

        // If user data exists for the given email.
        if (Initialization.userData != null) {
            // Retrieve the list of sites associated with the user. If not present, initialize it as a new JSONArray.
            Initialization.sites = Initialization.userData.optJSONArray("sites");
            if (Initialization.sites == null) {
                Initialization.sites = new JSONArray();
            }

            boolean isDuplicated = false;

            // Check if the site already exists in the user's site list.
            for (int i = 0; i < Initialization.sites.length(); i++) {
                if (Initialization.sites.getJSONObject(i).getString("siteName").equals(site)) {
                    isDuplicated = true;
                    // Print a message and break the loop if a duplicate site is found.
                    System.out.println("Duplicate site found for email: " + email);
                    break;
                }
            }

            // If no duplicate is found, add the new site and password to the user's site list.
            if (!isDuplicated) {
                JSONObject newSite = new JSONObject();
                newSite.put("siteName", site);
                newSite.put("password", EncryptDecrypt.encrypt(pass, Initialization.secretKey, Initialization.ivParameterSpec));
                Initialization.sites.put(newSite);
                Initialization.userData.put("Initialization.sites", Initialization.sites);
            }
        } else {
            // If no user data exists for the email, create a new entry with the site and encrypted password.
            Initialization.userData = new JSONObject();
            Initialization.sites = new JSONArray();
            JSONObject newSite = new JSONObject();
            newSite.put("siteName", site);
            newSite.put("password", EncryptDecrypt.encrypt(pass, Initialization.secretKey, Initialization.ivParameterSpec));
            Initialization.sites.put(newSite);
            Initialization.userData.put("Initialization.sites", Initialization.sites);
            Initialization.inventoryData.put(email, Initialization.userData);
        }

        // Write the updated inventory data back to the file.
        FileWriter writer = new FileWriter(Initialization.filePath);
        writer.write(Initialization.inventoryData.toString());
        writer.close();

        // Print a confirmation message indicating the inventory was updated.
        System.out.println("Inventory updated for email: " + email);
    }


    public static byte[] readFile(String email, String site) throws Exception {
        loadData(email);
        byte[] resPass = {};
        if (Initialization.inventoryData != null && Initialization.inventoryData.has(email)) {
            // Retrieve the user's data associated with the email
            JSONArray sites = Initialization.userData.optJSONArray("sites");
//            System.out.println(sites);
            // Check if the sites array is not null
            if (sites != null) {
                for (int i = 0; i < sites.length(); i++) {
                    JSONObject siteData = sites.getJSONObject(i);
                    // Match the site name
                    Object passwordObj = siteData.get("password");
                    if (passwordObj instanceof JSONObject) {
                        byte[] passwordBytes = jsonObjectToByteArray((JSONObject) passwordObj);
                        resPass = EncryptDecrypt.decrypt(passwordBytes, Initialization.secretKey, Initialization.ivParameterSpec);
                    } else if (passwordObj instanceof JSONArray) {
                        // Handle case where password is incorrectly stored as an array
                        // Log an error or fix the structure here
                        System.out.println("Error: Password is stored as JSONArray, not JSONObject");
                    } else {
                        System.out.println("Error: Unexpected data type for password");
                    }
                }
            }
        } else {
            System.out.println("User data or email not found.");
        }

        return resPass;
    }
    private static String loadJsonData(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            byte[] bytes = fis.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }
    private static void loadData(String email) throws IOException {
        // Create a File object pointing to the inventory data file.
        File file = new File(Initialization.filePath);
        // Check if the file exists. If it does, load existing data from the file.
        if (file.exists()) {
            String existingData = loadJsonData(Initialization.filePath);

            // If the data begins with '{', it's considered valid JSON. Otherwise, start with a new JSON object.
            if (existingData.startsWith("{")) {
                Initialization.inventoryData = new JSONObject(existingData);
            } else {
                Initialization.inventoryData = new JSONObject();
            }
        } else {
            // If the file does not exist, start with an empty JSON object.
            Initialization.inventoryData = new JSONObject();
        }

        // Attempt to retrieve user data associated with the given email from the inventory.
        Initialization.userData = Initialization.inventoryData.optJSONObject(email);

    }
    public static byte[] jsonObjectToByteArray(JSONObject jsonObject) {
        // Step 1: Convert JSONObject to String
        String jsonString = jsonObject.toString();

        // Step 2: Convert the String to byte[] using UTF-8 encoding
        return jsonString.getBytes(StandardCharsets.UTF_8);
    }
}