package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIException;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxItem.Info;
import com.box.sdk.BoxUser;
import com.box.sdk.EncryptionAlgorithm;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;
import com.box.sdk.JWTEncryptionPreferences;

public class BoxApiServiceImpl{

	BoxAPIConnection apiConn = null;

	private String CLIENT_ID = "ogtrt3roxqldqsczco60vvox01phno24";

	private String CLIENT_SECRET = "LPjiAyzRgVF5cgYwGYkkNayifCT3DPup";

	private String developer_token = "VyPgS4X0q1yOu8L07dFEBzRa135SzWpW";

	private String PUBLIC_KEY_ID = "iuiyejwu";

	private String PRIVATE_KEY_PASSWORD = "09876";

	private String MAX_CACHE_ENTRIES = "100";

	private String USER_ID = "285187870";

	final static Logger logger = Logger.getLogger(BoxApiServiceImpl.class);

	private void setBoxAPIConnection() throws Exception {
		if (apiConn == null) {
			try {
				IAccessTokenCache accessTokenCache = null;
				if (PUBLIC_KEY_ID == null || PRIVATE_KEY_PASSWORD == null || MAX_CACHE_ENTRIES == null
						|| CLIENT_ID == null || CLIENT_SECRET == null || USER_ID == null) {
					logger.info(
							"Inside BoxApiServiceImpl::setBoxAPIConnection Method::Unable to read values from property file");
				} else {

					JWTEncryptionPreferences encryptionPref = getJWTEncryptionPref();
					accessTokenCache = new InMemoryLRUAccessTokenCache(Integer.parseInt(MAX_CACHE_ENTRIES));

					this.apiConn = BoxDeveloperEditionAPIConnection.getAppUserConnection(USER_ID, CLIENT_ID,
							CLIENT_SECRET, encryptionPref, accessTokenCache);
					BoxUser.Info userInfo = BoxUser.getCurrentUser(apiConn).getInfo();
					logger.info(
							"Inside BoxApiServiceImpl::setBoxAPIConnection Method::Username is::" + userInfo.getName());

				}
			} catch (Exception e) {
				logger.error("BoxAPIException in BoxApiServiceImpl::setBoxAPIConnection Method::exception is" + e);
				logger.error(e.getMessage());
				e.printStackTrace();
				throw e;
			}
		}
	}

	/**
	 * Prepare the JWTEncryptionPreferences object with encryption details
	 * 
	 * @return
	 * @throws Exception
	 */
	private JWTEncryptionPreferences getJWTEncryptionPref() throws Exception {

		try {
			JWTEncryptionPreferences encryptionPref = new JWTEncryptionPreferences();
			encryptionPref.setPublicKeyID(PUBLIC_KEY_ID);

			String privateKey = getFileContent("private_key.pem");
			if (privateKey == null) {
				logger.info(
						"Inside BoxApiServiceImpl::setBoxAPIConnection Method::error in loading private_key.pem file");
			}

			encryptionPref.setPrivateKey(privateKey);
			encryptionPref.setPrivateKeyPassword(PRIVATE_KEY_PASSWORD);
			encryptionPref.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);
			return encryptionPref;
		} catch (Exception e) {
			logger.error("BoxAPIException in BoxApiServiceImpl::setBoxAPIConnection Method::exception is" + e);
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Get the content in the file
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private String getFileContent(String fileName) throws Exception {
		String result = null;
		try {
			result = new String(Files.readAllBytes(Paths.get(".\\input-data\\private_key.pem")));
		} catch (Exception e) {
			logger.error("BoxAPIException in BoxApiServiceImpl::setBoxAPIConnection Method::exception is" + e);
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	

	/**
	 * Fetch a folder from BOX
	 * 
	 * @throws Exception
	 */
	public BoxFolder getBoxFolder(String folderName) throws Exception {
		try {
			setBoxAPIConnection();

			BoxFolder rootFolder = BoxFolder.getRootFolder(apiConn);
			logger.info(rootFolder.getID());

			for (BoxItem.Info info : rootFolder) {
				logger.info("File/folder name is " + info.getName());
				if (folderName.equals(info.getName())) {

					logger.info("User Folder found" + info.getName());
					return (BoxFolder) info.getResource();
				}

			}
			return null;

		} catch (Exception e) {
			logger.error("Exception in BoxApiServiceImpl:: searchFolder method :: exception is" + e);
			e.printStackTrace();
			throw e;
		}

	}

	

	/**
	 * Download user's folder from BOX to the path set in the
	 * application.properties file
	 * 
	 * @throws IOException
	 */
	public void downloadFolderFromBox(String folderName, List<String> listInfoType, String destinationDirectoryPath,
			String userName) throws Exception {
		setBoxAPIConnection();
		BoxFile file = null;
		File destinationFile = null;
		FileOutputStream stream = null;

		logger.info("inside:BoxApiServiceImpl::downloadFolderFromBox");
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
						logger.info("file" + infoType + userName + "is created");
						stream.close();
						break;
					}
				}
			}
		} catch (BoxAPIException e) {

			logger.error("FileOperation Boxexception::Error", e);

			e.printStackTrace();
			throw e;

		} catch (FileNotFoundException e) {

			logger.error("FileOperation Boxexception downloadFolderFromBox method::", e);
			e.printStackTrace();
			throw e;
		} catch (IOException e) {

			logger.error("FileOperation IOException::", e);
			e.printStackTrace();
			throw e;
		} catch (Exception e) {

			logger.error("FileOperation Exception ::", e);
			e.printStackTrace();
			throw e;
		}

	}
	
	public List<String> jsonreaderForPersonalInfo(String filePath) {
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
            	  String idIssueDate = (String) o.get("idIssueDate").toString();
            	  dataList.add(idIssueDate);
            	  String idNo = (String) o.get("idNo");
            	  dataList.add(idNo);
            	  String idExpiryDate = (String) o.get("idExpiryDate").toString();
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
	
	public List<String> jsonreaderForAccountInfo(String filePath) {
        JSONParser parser = new JSONParser();
        List<String> dataList = new ArrayList<String>();
        try {
        	JSONObject jsonObject = new JSONObject((parser.parse(new FileReader(filePath))).toString());
            String type = (String) jsonObject.get("type");
            dataList.add(type);
            String userName = (String) jsonObject.get("userName");
            dataList.add(userName);
            String account_type = (String) jsonObject.get("account_type");
            dataList.add(account_type);
            String accountName = (String) jsonObject.get("accountName");
            dataList.add(accountName);
            String account_no = (String) jsonObject.get("account_no").toString();
            dataList.add(account_no);
            String payment_code_one_id = (String) jsonObject.get("payment_code_one_id").toString();
            dataList.add(payment_code_one_id);
            String payment_code_one_ref = (String) jsonObject.get("payment_code_one_ref").toString();
            dataList.add(payment_code_one_ref);
            String corr_payment_code_one_id = (String) jsonObject.get("corr_payment_code_one_id").toString();
            dataList.add(corr_payment_code_one_id);
            String corr_payment_code_one_ref = (String) jsonObject.get("corr_payment_code_one_ref").toString();
            dataList.add(corr_payment_code_one_ref);
            String bank = (String) jsonObject.get("bank").toString();
            dataList.add(bank);
            String walletAccountName = (String) jsonObject.get("walletAccountName").toString();
            dataList.add(walletAccountName);
            String walletAddress = (String) jsonObject.get("walletAddress").toString();
            dataList.add(walletAddress);
            String corraddress1 = (String) jsonObject.get("corraddress1").toString();
            dataList.add(corraddress1);
            String corraddress2 = (String) jsonObject.get("corraddress2").toString();
            dataList.add(corraddress2);
            String corrAccountsCountryName = (String) jsonObject.get("corrAccountsCountryName").toString();
            dataList.add(corrAccountsCountryName);
            String corrstate = (String) jsonObject.get("corrstate").toString();
            dataList.add(corrstate);
            String corrcity = (String) jsonObject.get("corrcity").toString();
            dataList.add(corrcity);
            String corrbankName = (String) jsonObject.get("corrbankName").toString();
            dataList.add(corrbankName);
            String corrzip = (String) jsonObject.get("corrzip").toString();
            dataList.add(corrzip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }
	
	public List<String> jsonreaderForEntityInfo(String filePath) {
        JSONParser parser = new JSONParser();
        List<String> dataList = new ArrayList<String>();
        try {
        	JSONObject jsonObject = new JSONObject((parser.parse(new FileReader(filePath))).toString());
            String type = (String) jsonObject.get("type");
            dataList.add(type);
            String userName = (String) jsonObject.get("userName");
            dataList.add(userName);
            String name = (String) jsonObject.get("name");
            dataList.add(name);
            String base_currency = (String) jsonObject.get("base_currency");
            dataList.add(base_currency);
            String typeOfBusiness = (String) jsonObject.get("typeOfBusiness");
            dataList.add(typeOfBusiness);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }
	
	public static void main(String[] args) throws Exception
	{
		BoxApiServiceImpl boxAPIServices = new BoxApiServiceImpl();
		BoxFolder a = boxAPIServices.getBoxFolder("pankaj2016@mailinator.com_inProgress");
		List<String> al = new ArrayList<String>();
		al.add("AccountInfo");
		al.add("EntityInfo");
		al.add("PersonalInfo");
		for(Info b : a.getChildren())
		{
			System.out.println(b.getName());
			if(b.getName().contains(".txt"))
			{
				boxAPIServices.downloadFolderFromBox("pankaj2016@mailinator.com_inProgress", al, ".\\input-data", "");
			}
		}
		List<String> listDataForPersonalInfo = boxAPIServices.jsonreaderForPersonalInfo(".\\input-data\\pankaj2016@mailinator.com_inProgress\\PersonalInfo_pankaj2016@mailinator.com.txt");
		List<String> listDataForAccountInfo = boxAPIServices.jsonreaderForAccountInfo(".\\input-data\\pankaj2016@mailinator.com_inProgress\\AccountInfo_pankaj2016@mailinator.com.txt");
		List<String> listDataForEntityInfo = boxAPIServices.jsonreaderForEntityInfo(".\\input-data\\pankaj2016@mailinator.com_inProgress\\EntityInfo_pankaj2016@mailinator.com.txt");
		System.out.println(listDataForPersonalInfo);
		System.out.println(listDataForAccountInfo);
		System.out.println(listDataForEntityInfo);
	}
}
