import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
public class FileFuncs {
    public static void checkFile() throws IOException {
        File file = new File(Initialization.filePath);
        if (!file.exists()) {file.createNewFile();}
    }
    public static void writeFile(String pass, String email, String site) throws Exception {
        byte[] encPass = EncryptDecrypt.encrypt(pass, Initialization.secretKey, Initialization.ivParameterSpec);
        JSONObject inventoryData = new JSONObject();
        JSONObject userData = inventoryData.optJSONObject(email);
        JSONArray sites;
        if (userData != null) {
            sites = userData.optJSONArray("site");
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
                newSite.put("password", encPass);
                sites.put(newSite);
                userData.put("sites", sites);
            }
        }else{
            userData = new JSONObject();
            userData.put("email", email);
            sites = new JSONArray();
            JSONObject newSite = new JSONObject();
            newSite.put("siteName", site);
            newSite.put("password", encPass);
            sites.put(newSite);
            userData.put("sites", sites);
            inventoryData.put(email, userData);
        }
        FileWriter writer = new FileWriter(Initialization.filePath);
        writer.write(inventoryData.toString());
        writer.close();
        System.out.println("Inventory updated for email: " + email);
    }
    public static byte[] readFile(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            return fis.readAllBytes();
        }
    }
}