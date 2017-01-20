package core;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.FileReader;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import utilities.PropertyDictionary;

public class CommonAction {

	int allclorId;
	private String keyString = "adkj@#$0p#@adflkj)(*jlj@#$#@LKjasdjlkj<.,mo@#$@#kljlkdsuqrs";
	
	public void Wait(int millSec) throws InterruptedException {
		Thread.sleep(millSec);
	}
	
	public String getEncryptedUrl(String hash) {
		 
		try {
			String[] encrypted = encryptObject(hash);
			// url may differ.. based upon project initial context
			String encryptedUrl = "http://10.55.1.131:9591/NobleMktKYC/static/index.html#/login?" + encrypted[0]
					+ "&" + encrypted[1];
			 
			return encryptedUrl;
		} catch (Exception e) {
			System.out.println("Exception : getEncryptedUrl ");
		}
		return null;
	}

	public String[] encryptObject(Object obj) throws Exception {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(stream);
		try {
			// Serialize the object
			out.writeObject(obj);
			byte[] serialized = stream.toByteArray();


			// Setup the cipher and Init Vector
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] iv = new byte[cipher.getBlockSize()];

			new SecureRandom().nextBytes(iv);
			IvParameterSpec ivSpec = new IvParameterSpec(iv);

			// Hash the key with SHA-256 and trim the output to 128-bit for the
			// key
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(keyString.getBytes());
			byte[] key = new byte[16];
			System.arraycopy(digest.digest(), 0, key, 0, key.length);
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

			// encrypt
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

			// Encrypt & Encode the input
			byte[] encrypted = cipher.doFinal(serialized);
			byte[] base64Encoded = Base64.encodeBase64(encrypted);
			String base64String = new String(base64Encoded);
			String urlEncodedData = URLEncoder.encode(base64String, "UTF-8");

			// Encode the Init Vector
			byte[] base64IV = Base64.encodeBase64(iv);
			String base64IVString = new String(base64IV);
			String urlEncodedIV = URLEncoder.encode(base64IVString, "UTF-8");


			return new String[] { urlEncodedData, urlEncodedIV };
		} finally {
			stream.close();
			out.close();
		}
	}

	public String randomEmailGenerate() {
		String randomString;
		String alphabet = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
		int count = alphabet.length();
		randomString = new String();
		Random r = new Random();
		for (int i = 0; i < 10; i++)
			randomString = randomString + alphabet.charAt(r.nextInt(count));
		System.out.println(randomString + "AutoMT" + "@mailinator.com");
		return randomString + "AutoMT" + "@mailinator.com";

	}

	public String randomPhoneNumberGenerate() {
		String subNumber = "00000";
		return subNumber + Math.round(Math.random() * 89999 + 10000);
	}

	public void selectDropdown(WebElement element, String value) {
		Select dropDown = new Select(element);
		try {
			dropDown.selectByVisibleText(value);
		} catch (NoSuchElementException e) {
			dropDown.selectByValue(value);
		}
	}

	public void selectDropdown(WebElement element, int index) {
		Select dropDown = new Select(element);
		dropDown.selectByIndex(index);
	}

	public void waitElementToBeClickable(WebDriver driver, WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public List<String> getAccountListFromTxtFile() throws IOException {
		List<String> account = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(".//input-data//testData.txt"));
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			String arrayString[] = sCurrentLine.split("");
			account.addAll(Arrays.asList(arrayString[9].split("1=")));
			account.removeAll(Arrays.asList(null, ""));
		}
		return account;
	}

	public List<String> getOrderTypeListFromTxtFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(".//input-data//testData.txt"));
		List<String> orderType = new ArrayList<String>();
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			String arrayString[] = sCurrentLine.split("");
			orderType.addAll(Arrays.asList(arrayString[12].split("40=")));
			orderType.removeAll(Arrays.asList(null, ""));
		}
		return orderType;
	}

	public List<String> getInstrumentListFromTxtFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(".//input-data//testData.txt"));
		List<String> instrument = new ArrayList<String>();
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			String arrayString[] = sCurrentLine.split("");
			instrument.addAll(Arrays.asList(arrayString[15].split("55=")));
			instrument.removeAll(Arrays.asList(null, ""));
		}
		return instrument;
	}

	public List<String> getPriceListFromTxtFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(".//input-data//testData.txt"));
		List<String> price = new ArrayList<String>();
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			String arrayString[] = sCurrentLine.split("");
			price.addAll(Arrays.asList(arrayString[13].split("44=")));
			price.removeAll(Arrays.asList(null, ""));
		}
		return price;
	}

	public List<String> getQuantityListFromTxtFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(".//input-data//testData.txt"));
		List<String> quantity = new ArrayList<String>();
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			String arrayString[] = sCurrentLine.split("");
			quantity.addAll(Arrays.asList(arrayString[11].split("38=")));
			quantity.removeAll(Arrays.asList(null, ""));
		}
		return quantity;
	}

	public boolean getResultStatusFromBuild() throws Exception {
		doTrustToCertificates();
		String latestCrumb = getTheLatestcrumb();
		if (!latestCrumb.isEmpty()) {
			String getUrl = PropertyDictionary.map.get("Build_Trigger_Url") + latestCrumb;
			HttpUriRequest request = new HttpPost(getUrl);
			request.addHeader("Authorization", "Basic " + getBasicAuthenticationEncoding());
			request.setHeader("Content-Type", "application/json");
			HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
			if (httpResponse.getStatusLine().getStatusCode() == 201) {
				System.out.println("Build Trigger - SUCCESS");
				int i = 0;
				do {
					String getUrl2 = PropertyDictionary.map.get("Result_Status_Url") + latestCrumb;
					HttpUriRequest request2 = new HttpPost(getUrl2);
					request2.addHeader("Authorization", "Basic " + getBasicAuthenticationEncoding());
					request2.setHeader("Content-Type", "application/json");
					Thread.sleep(10000); // 10 seconds
					HttpResponse httpResponse2 = HttpClientBuilder.create().build().execute(request2);
					String jsonString = EntityUtils.toString(httpResponse2.getEntity());
					System.out.println(jsonString);
					if (jsonString.contains("SUCCESS")) {
						System.out.println("Success");
						return true;
					}
					else
						Reporter.log("Load Simulator Build Failed");
					if (i == 19) {
						System.out.println("No Success result !");
						return false;
					}
					i++;
				} while (i < 20);
			} else {
				System.out.println("Build Not Trigger - Unsuccessful!");
				return false;
			}
		} else {
			return false;
		}
		return false;
	}

	private static String getBasicAuthenticationEncoding() {
		String userPassword = "dilip" + ":" + "dilip";
		return new String(Base64.encodeBase64(userPassword.getBytes()));
	}

	public static String getTheLatestcrumb() throws ClientProtocolException, IOException {
		String getUrl2 = "https://jenkins.noblemarkets.net/crumbIssuer/api/xml?xpath=concat(//crumbRequestField,%22:%22,//crumb)";
		HttpUriRequest request2 = new HttpGet(getUrl2);
		request2.addHeader("Authorization", "Basic " + getBasicAuthenticationEncoding());
		request2.setHeader("Content-Type", "application/json");
		HttpResponse httpResponse2 = HttpClientBuilder.create().build().execute(request2);
		String jsonString = EntityUtils.toString(httpResponse2.getEntity());
		int startIndex = jsonString.indexOf(":") + 1;
		return jsonString.substring(startIndex);
	}

	public List<String> getAllRecordsFromResponse() throws IOException {
		List<String> clourIdList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(PropertyDictionary.map.get("request")));
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			String arrayString[] = sCurrentLine.split("");
			clourIdList.addAll(Arrays.asList(arrayString[8].split("11=")));
			clourIdList.removeAll(Arrays.asList(null, ""));
		}

		setClorId(clourIdList);

		List<String> clourIdListInresponse = new ArrayList<String>();
		BufferedReader br1 = new BufferedReader(new FileReader(PropertyDictionary.map.get("response")));
		String sCurrentLine1;
		for (int count = 0; count < clourIdList.size(); count++)
			while ((sCurrentLine1 = br1.readLine()) != null) {
				String arrayString[] = sCurrentLine1.split("");
				if (sCurrentLine1.contains(clourIdList.get(count))) {
					clourIdListInresponse.addAll(Arrays.asList(arrayString[9].split("11=")));
					clourIdListInresponse.removeAll(Arrays.asList(null, ""));
					clourIdListInresponse.addAll(Arrays.asList(arrayString[12].split("37=")));
					clourIdListInresponse.removeAll(Arrays.asList(null, ""));
					clourIdListInresponse.addAll(Arrays.asList(arrayString[8].split("1=")));
					clourIdListInresponse.removeAll(Arrays.asList(null, ""));
					clourIdListInresponse.addAll(Arrays.asList(arrayString[15].split("40=")));
					clourIdListInresponse.removeAll(Arrays.asList(null, ""));
					clourIdListInresponse.addAll(Arrays.asList(arrayString[18].split("55=")));
					clourIdListInresponse.removeAll(Arrays.asList(null, ""));
					clourIdListInresponse.addAll(Arrays.asList(arrayString[13].split("38=")));
					clourIdListInresponse.removeAll(Arrays.asList(null, ""));
					clourIdListInresponse.addAll(Arrays.asList(arrayString[16].split("44=")));
					clourIdListInresponse.removeAll(Arrays.asList(null, ""));
					clourIdListInresponse.addAll(Arrays.asList(arrayString[17].split("54=")));
					clourIdListInresponse.removeAll(Arrays.asList(null, ""));
					break;
				}
			}
		return clourIdListInresponse;
	}

	public String getRequestText() throws ClientProtocolException, IOException, InterruptedException {
		String latestCrumb = getTheLatestcrumb();
		String getUrl2 = PropertyDictionary.map.get("Request_Url") + latestCrumb;
		HttpUriRequest request2 = new HttpPost(getUrl2);
		request2.addHeader("Authorization", "Basic " + getBasicAuthenticationEncoding());
		request2.setHeader("Content-Type", "application/json");
		HttpResponse httpResponse2 = HttpClientBuilder.create().build().execute(request2);
		String jsonString = EntityUtils.toString(httpResponse2.getEntity());
		return jsonString;
	}

	public String getResponseText() throws ClientProtocolException, IOException, InterruptedException {
		String latestCrumb = getTheLatestcrumb();
		String getUrl2 = PropertyDictionary.map.get("Response_Url") + latestCrumb;
		HttpUriRequest request2 = new HttpPost(getUrl2);
		request2.addHeader("Authorization", "Basic " + getBasicAuthenticationEncoding());
		request2.setHeader("Content-Type", "application/json");
		HttpResponse httpResponse2 = HttpClientBuilder.create().build().execute(request2);
		String jsonString = EntityUtils.toString(httpResponse2.getEntity());
		return jsonString;
	}

	public void setClorId(List<String> allClorIds) {
		allclorId = allClorIds.size();
	}

	public int getClorId() {
		return allclorId;
	}

	// trusting all certificate
	public void doTrustToCertificates() throws Exception {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				return;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				return;
			}
		} };

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verifyHost(String urlHostName, SSLSession session) {
				if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
					System.out.println("Warning: URL host '" + urlHostName + "' is different to SSLSession host '"
							+ session.getPeerHost() + "'.");
				}
				return true;
			}

			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
	}

}
