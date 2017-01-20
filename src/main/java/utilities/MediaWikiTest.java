package utilities;

import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;

import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;

import com.bitplan.mediawiki.japi.Mediawiki;

import core.Listener;

public class MediaWikiTest {

public void resultUpdate() throws Exception {
		
		Listener listener = new Listener();
		int PassCount = Collections.frequency(listener.getPassFailResult(), "PASSED");
		int failCount  = Collections.frequency(listener.getPassFailResult(), "FAILED");
		int skippedCount = Collections.frequency(listener.getPassFailResult(), "SKIPPED");
		int totalTestCasesCount = PassCount + failCount + skippedCount;
		ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(createAllTrustingClient());
		ResteasyClient client = new ResteasyClientBuilder().httpEngine(engine).build();
		Mediawiki wiki = new Mediawiki("https://wiki.noblemarkets.net/api.php");
		wiki.login("Dilip", "golf200");
		StringBuffer testCountTable = new StringBuffer();
		testCountTable.append("'''Noble Markets Test Report run by Automation Testing'''").append("\n").append("{| class=\"wikitable\"").append("\n")
		.append("! Summary || ").append("\n").append("|-").append("\n").append("| Total test cases ||"+totalTestCasesCount).append("\n").append("|-")
		.append("\n").append("| Passed test cases ||"+PassCount).append("\n").append("|-").append("\n").append("| Failed test cases ||"+failCount)
		.append("\n").append("|-").append("\n").append("| Skipped test cases ||"+skippedCount).append("\n").append("|}").append("\n");
		
		StringBuffer testMethodTable = new StringBuffer();
		testMethodTable.append(testCountTable).append("{|class=\"wikitable\"").append("\n").append("! TestCase Count || Module || Test Case ID || Result").append(listener.getResultList()).append("|}");
		System.out.println("+++++++++++++++++++"+testMethodTable.toString()+"+++++++++++++++++++++++++++++++");
		wiki.edit("TestReportDocuments", testMethodTable.toString().replace("[", "").replace("]", "").replace(",", ""),
				"TestReportDocuments");
		/*wiki.edit("TestReportDocuments", listener.getResultList().toString(),
				"TestReportDocuments");*/
		
	}

	
	private static DefaultHttpClient createAllTrustingClient() throws GeneralSecurityException {
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));

		TrustStrategy trustStrategy = new TrustStrategy() {
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		};
		SSLSocketFactory factory = new SSLSocketFactory(trustStrategy, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER );
		registry.register(new Scheme("https", 443, factory));

		ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
		mgr.setMaxTotal(1000);
		mgr.setDefaultMaxPerRoute(1000);

		DefaultHttpClient client = new DefaultHttpClient(mgr, new DefaultHttpClient().getParams());
		return client;
	}

}
