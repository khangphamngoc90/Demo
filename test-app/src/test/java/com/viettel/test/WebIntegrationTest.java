package com.viettel.test;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import junit.framework.TestCase;

import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class WebIntegrationTest extends TestCase {

	private int actualPort;

	@Before  
    public void before() {  
         LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",  
                   "org.apache.commons.logging.impl.NoOpLog");  
         java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit")  
                   .setLevel(Level.OFF);  
         java.util.logging.Logger.getLogger("org.apache.commons.httpclient")  
                   .setLevel(Level.OFF);  
    }  
	
	public void testIndex() {
		// beginAt("login");
		//
		// setWorkingForm("login-form");
		//
		// assertFormPresent("login-form");
		//
		// assertFormElementPresent("j_username");
		// assertFormElementPresent("j_password");
		//
		// setFormElement("j_username", "trungkh");
		// setFormElement("j_password", "123456");
		// submit("signIn");
		//
		// beginAt("webui");
		//
		// assertTitleEquals("Framework ViettelOne Demonstration");
	}

	@Test
	public void testDocumentWrite() throws IOException, InterruptedException {
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setAppletEnabled(true);
		webClient.getOptions().setPopupBlockerEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController(){
			
			@Override
			public boolean processSynchron(HtmlPage page, WebRequest settings,
					boolean async) {
				System.out.println("waiting..."+page+" ---- "+settings);
				return super.processSynchron(page, settings, async);
			}
			
		});
		
		HtmlPage page = null;

		page = webClient.getPage("http://localhost:8080/Viettel-One-webUI/login");

		List<HtmlForm> forms = page.getForms();

		HtmlForm ask = forms.get(0);

		System.out.println(ask.asText());

		HtmlSubmitInput button = ask.getInputByName("signIn");
		HtmlTextInput txtUser = ask.getInputByName("j_username");
		HtmlPasswordInput txtPass = ask.getInputByName("j_password");
		txtUser.setValueAttribute("trungkh");
		txtPass.setValueAttribute("123456");

		button.click();
		
		page = webClient.getPage("http://localhost:8080/Viettel-One-webUI/webui/#!/Employee/popup=detail");
		
		webClient.waitForBackgroundJavaScriptStartingBefore(5000);
		
		System.out.println(page.asText());
		
		Thread.sleep(10000);
		webClient.closeAllWindows();

	}

	protected void setUp() throws Exception {

		// Server server = new org.mortbay.jetty.Server(0);
		// server.addHandler(
		// new org.mortbay.jetty.webapp.WebAppContext("src/main/webapp", "/"));
		// server.start();
		//
		// // getLocalPort returns the port that was actually assigned
		// actualPort = server.getConnectors()[0].getLocalPort();
		// getTestContext().setBaseUrl("http://localhost:"+actualPort+"/");
	}
}
