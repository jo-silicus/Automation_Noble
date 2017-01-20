package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIException;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxItem.Info;

public class BoxAPIServices {
	BoxAPIConnection apiConn = null;
	private static String clientId = "ogtrt3roxqldqsczco60vvox01phno24";
	private static String clientSecret = "LPjiAyzRgVF5cgYwGYkkNayifCT3DPup";
	private static String developer_token = "uQ8KuRASdtylXbNl4nJ2f6QwuuH1UdWp";
	
	public void downloadFolderFromBox(String folderName, List<String> listInfoType, String destinationDirectoryPath,
			String userName) throws Exception {
		setBoxAPIConnection();
		BoxFile file = null;
		File destinationFile = null;
		FileOutputStream stream = null;
		try {
			new File(destinationDirectoryPath + "\\" + folderName).mkdirs();
			BoxFolder userFolder = getBoxFolder(folderName);
			for (Info info : userFolder) {
				for (String infoType : listInfoType) {
					if (info.getName().contains(infoType)) {
						file = (BoxFile) info.getResource();

						destinationFile = new File(
								destinationDirectoryPath + "\\" + folderName + "\\" + info.getName());

						stream = new FileOutputStream(destinationFile);
						file.download(stream);
						stream.close();
						jsonreader("." + "\\" + folderName + "\\" + info.getName());
						break;
					}
				}
			}
		} catch (BoxAPIException e) {
			e.printStackTrace();
			throw e;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	private void setBoxAPIConnection() {
		if (apiConn == null) {
			this.apiConn = new BoxAPIConnection(developer_token);
		}
	}
	
	public BoxFolder getBoxFolder(String folderName) {
		setBoxAPIConnection();

		BoxFolder rootFolder = BoxFolder.getRootFolder(apiConn);
		try {
			for (BoxItem.Info info : rootFolder) {
				if (folderName.equals(info.getName())) {
					return (BoxFolder) info.getResource();
				}

			}
			return null;

		} catch (BoxAPIException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	public List<String> jsonreader(String filePath) {
        JSONParser parser = new JSONParser();
        List<String> dataList = new ArrayList<String>();
        try {
        	JSONObject jsonObject = new JSONObject((parser.parse(new FileReader(filePath))).toString());
            String type = (String) jsonObject.get("type");
            dataList.add(type);
            String userName = (String) jsonObject.get("userName");
            dataList.add(userName);
            String email = (String) jsonObject.get("email");
            dataList.add(email);
            String firstName = (String) jsonObject.get("firstName");
            dataList.add(firstName);
            String lastName = (String) jsonObject.get("lastName");
            dataList.add(lastName);
            String nationalIdentifier = (String) jsonObject.get("nationalIdentifier").toString();
            dataList.add(nationalIdentifier);
            JSONArray documentUploadDetail = (JSONArray) jsonObject.getJSONArray("documentUploadDetail");
            
            for(int i=0; i<documentUploadDetail.length(); i++){   
            	  JSONObject o = documentUploadDetail.getJSONObject(i); 
            	  String newFileName = (String) o.get("newFileName");
            	  dataList.add(newFileName);
            	  String originalName = (String) o.get("originalName");
            	  dataList.add(originalName);
            	  String documentType = (String) o.get("documentType");
            	  dataList.add(documentType);
            	  String dtype = (String) o.get("dtype");
            	  dataList.add(dtype);
            	  String idIssueDate = (String) o.get("idIssueDate");
            	  dataList.add(idIssueDate);
            	  String idNo = (String) o.get("idNo");
            	  dataList.add(idNo);
            	  String idExpiryDate = (String) o.get("idExpiryDate");
            	  dataList.add(idExpiryDate);
            	  String country_issue = (String) o.get("country_issue");
            	  dataList.add(country_issue);  
            	}
            
            JSONObject contact_information= jsonObject.getJSONObject("contact_information");
            Iterator key1 = contact_information.keys();

            while (key1.hasNext()){
                String key = (String) key1.next();
                dataList.add((String) contact_information.get(key).toString());
            }
            
            JSONObject contact_address= jsonObject.getJSONObject("contact_address");
            Iterator key2 = contact_address.keys();

            while (key2.hasNext()){
                String key = (String) key2.next();
                dataList.add((String) contact_address.get(key).toString());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }
	
	public static void main(String[] args) throws Exception
	{
		BoxAPIServices boxAPIServices = new BoxAPIServices();
		BoxFolder a = boxAPIServices.getBoxFolder("abc@abc.com_inProgress");
		List<String> al = new ArrayList<String>();
		al.add("PersonalInfo");
		for(Info b : a.getChildren())
		{
			System.out.println(b.getName());
			if(b.getName().contains(".txt"))
			{
				boxAPIServices.downloadFolderFromBox("abc@abc.com_inProgress", al, ".\\input-data", "");
			}
			
			}
	}


}
