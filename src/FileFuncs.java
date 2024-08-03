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
        byte[] encPass = EncryptDecrypt.encrypt(pass, Initialization.secretKey, Initialization.ivParameterSpec);
        File file = new File(Initialization.filePath);
        JSONObject inventoryData;
        if (file.exists()) {
            String existingData = readFile(Initialization.filePath);
            if (!existingData.isEmpty() && existingData.startsWith("{")) {
                inventoryData = new JSONObject(existingData);
            } else {
                inventoryData = new JSONObject();
            }
        } else {
            inventoryData = new JSONObject();
        }

        // Update existing data
        JSONObject userData = inventoryData.optJSONObject(email);
        JSONArray sites;
        if (userData != null) {
            sites = userData.optJSONArray("sites");
            if (sites == null) {
                sites = new JSONArray();
            }
            boolean isDublicated = false;
            for (int i = 0; i < sites.length(); i++) {
                if (sites.getJSONObject(i).getString("siteName").equals(site)) {
                    isDublicated = true;
                    System.out.println("Duplicate site found for email: " + email);
                    break;
                }
            }
            if (!isDublicated) {
                JSONObject newSite = new JSONObject();
                newSite.put("siteName", site);
                newSite.put("password", EncryptDecrypt.encrypt(pass, Initialization.secretKey, Initialization.ivParameterSpec));
                sites.put(newSite);
                userData.put("sites", sites);
            }
        } else {
            userData = new JSONObject();
            userData.put("email", email);
            sites = new JSONArray();
            JSONObject newSite = new JSONObject();
            newSite.put("siteName", site);
            newSite.put("password", EncryptDecrypt.encrypt(pass, Initialization.secretKey, Initialization.ivParameterSpec));
            sites.put(newSite);
            userData.put("sites", sites);
            inventoryData.put(email, userData);
        }

        // Write updated data to file
        FileWriter writer = new FileWriter(Initialization.filePath);
        writer.write(inventoryData.toString());
        writer.close();
        System.out.println("Inventory updated for email: " + email);

    }
    public static String readFile(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            byte[] bytes = fis.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }
}