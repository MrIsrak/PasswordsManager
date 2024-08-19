import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
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
                newSite.put("password", encPass);
                Initialization.sites.put(newSite);
                Initialization.userData.put("Initialization.sites", Initialization.sites);
            }
        } else {
            // If no user data exists for the email, create a new entry with the site and encrypted password.
            Initialization.userData = new JSONObject();
            Initialization.sites = new JSONArray();
            JSONObject newSite = new JSONObject();
            newSite.put("siteName", site);
            newSite.put("password", encPass);
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


    public static void readFile(String email, String site) throws Exception {
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
                    if (siteData.getString("siteName").equals(site)) {
                        // Retrieve the password stored as JSONArray
                        String passwordString = siteData.optString("password");
                        System.out.println(passwordString);

                        System.out.println(Arrays.toString(manualStringToByte(passwordString)));

                        byte[] decryptedBytes = EncryptDecrypt.decrypt(manualStringToByte(passwordString), Initialization.secretKey, Initialization.ivParameterSpec);
                        String decryptedPassword = new String(decryptedBytes, StandardCharsets.UTF_8);

                        // Debug: Print the decrypted byte array
                        System.out.println("Decrypted Bytes: " + Arrays.toString(decryptedBytes));
                        // Print the decrypted password
                        System.out.println("Decrypted Password: " + decryptedPassword);
                        break;
                    } else {
                        System.out.println("Error: Unexpected data type for password");
                    }
                }
            }
        } else {
            System.out.println("User data or email not found.");
        }

    }

    private static byte[] manualStringToByte(String encPassString){
        ArrayList<Byte> encPassList = new ArrayList<>();
        StringBuilder temp = new StringBuilder();

        for (int i = 1; i < encPassString.length(); i++) {
            if (encPassString.charAt(i) != ',' && encPassString.charAt(i) != ']') {
                temp.append(encPassString.charAt(i));
            } else {
                try {
                    byte b = Byte.parseByte(temp.toString());
                    encPassList.add(b);
                    temp = new StringBuilder();
                } catch (NumberFormatException e) {
                    // Handle invalid number format
                    System.err.println("Invalid number: " + temp);
                }
            }
        }
        // Convert ArrayList to byte array
        byte[] encPassByte = new byte[encPassList.size()];
        for (int i = 0; i < encPassList.size(); i++) {
            encPassByte[i] = encPassList.get(i);
        }
        return encPassByte;

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
}