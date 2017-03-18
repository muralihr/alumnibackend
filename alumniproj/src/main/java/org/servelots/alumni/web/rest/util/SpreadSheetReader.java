package org.servelots.alumni.web.rest.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


import org.servelots.alumni.domain.Alumni;
import org.servelots.alumni.service.impl.AlumniServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ClassUtils;

import java.util.LinkedList;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
 import  org.servelots.alumni.web.rest.util.OSValidator;
 import  org.servelots.alumni.web.rest.AppConstants;

public class SpreadSheetReader {
	/** Application name. */
	private static final String APPLICATION_NAME = "Google Sheets API Java SpreadSheetReader";
	  private static final Logger log = LoggerFactory.getLogger(SpreadSheetReader.class);

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/sheets.googleapis.com-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS, SheetsScopes.DRIVE);

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	@Autowired
	static private Environment environment;
	String errMessageException;
	/**
	 * Global instance of the scopes required by this x.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/sheets.googleapis.com-java-quickstart
	 */

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */

	static	String  clientId = "502552144099-30k4b1n00g29q4cmf7a3tdk2aer8aei4.apps.googleusercontent.com ";
	static String clientSecret = "qVLwdUG1RI_TILjn4HS__W5y ";

	public static Credential readCredentialFromCommandLine(JsonFactory jsonFactory, HttpTransport transport) {
		
		return new GoogleCredential.Builder().setJsonFactory(jsonFactory).setTransport(transport)
				.setClientSecrets(clientId, clientSecret).build();

	}

public static GoogleCredential createCredentialForServiceAccountImpersonateUser(
      HttpTransport transport,
      JsonFactory jsonFactory,
      String serviceAccountId,
      Collection<String> serviceAccountScopes,
      File p12File,
      String serviceAccountUser) throws  Exception, IOException {
    return new GoogleCredential.Builder().setTransport(transport)
        .setJsonFactory(jsonFactory)
        .setServiceAccountId(serviceAccountId)
        .setServiceAccountScopes(serviceAccountScopes)
        .setServiceAccountPrivateKeyFromP12File(p12File)
        .setServiceAccountUser(serviceAccountUser)
        .build();
  }
	public static Credential authorize() throws IOException {
		// Load client secrets.
 		InputStream in =	ClassUtils.getDefaultClassLoader().getResourceAsStream("/client_secret.json") ;
		//	InputStream in = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));
		 
 String serviceAccountName = "murali-726@testmaps-149908.iam.gserviceaccount.com";
try {
	
	 log.debug("SEC_FILE_PATH" + environment.getProperty(AppConstants.SEC_FILE_PATH));
		String secfileHome = environment.getProperty(AppConstants.SEC_FILE_PATH);
		if (secfileHome == null) {
			log.debug("SEC_FILE_PATH NULL "   );
			if (OSValidator.isUnix()) {
				secfileHome = AppConstants.SEC_FILE_LINUX;
				log.debug("SEC_FILE_PATH NULL UNIX"   );
				log.debug("UNIX ENV");
			}
			if (OSValidator.isWindows()) {
				secfileHome = AppConstants.SEC_FILE_WIN;
			log.debug("SEC_FILE_PATH NULL WIN"   );	
				log.debug("WINDOWS ENV");
			}
		}

log.debug("SEC_FILE home  "  +secfileHome );
//File p12File = new File(secfileHome);
File p12File = new File("E:/angular2/gsheetv/testmaps-d735f060b87d.p12");
GoogleCredential credential =  createCredentialForServiceAccountImpersonateUser(HTTP_TRANSPORT, JSON_FACTORY,serviceAccountName, SCOPES, p12File, null);

	      System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
} catch(Exception e)
{       System.out.println( "exception"+e             );
}
		/*
		 * Credential credential =
		 * readCredentialFromCommandLine(JSON_FACTORY,HTTP_TRANSPORT );
		 */
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return null;
	}

	/**
	 * Build and return an authorized Sheets API client service.
	 * 
	 * @return an authorized Sheets API client service
	 * @throws IOException
	 */
	
	 
	
	public static Sheets getSheetsService() throws IOException {
	//	Credential credential = authorize();
		
		  Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
	}
	///

	////
	public static List<Alumni> getAlumniData() throws IOException {
		// Build a new authorized API client service
		System.out.println("HELLO");
		 List<Alumni> lAlumni = new  LinkedList<Alumni>();
		Sheets service = getSheetsService();

		// Prints the names and majors of students in a sample spreadsheet:
		// https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
		// String spreadsheetId =
		// "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms";
		// 1tREviBCRjQnoGSBFntkN38gsbBUX0GwgiP0HOG705FA
		// https://docs.google.com/spreadsheets/d/1GnE-B1H137N9YNUcbNWce4HGX6i_WfIqKKGvmzePqtw/edit?usp=sharing
		String spreadsheetId = "1GnE-B1H137N9YNUcbNWce4HGX6i_WfIqKKGvmzePqtw";
		String range = "Class Data";
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		if (values == null || values.size() == 0) {
			System.out.println("No data found.");
		} else {
			System.out.println("Name, Major");
			int index = 0;
			for (List row : values) {

				Alumni alumniObj = new Alumni();
				if (index == 0) {
					index++;
					continue;

				}

				index++;
				
								// Print columns A and E, which correspond to indices 0 and 4.
				System.out.println("SIZR" + row.size());
				for (int i = 0; i < row.size(); i++) {

					
					
					try {
						System.out.println("" + row.get(i));
						
						String cornellAlumniIDNumber = row.get(1).toString();
						
						alumniObj.setCornellAlumniIDNumber(cornellAlumniIDNumber);
						System.out.println("" + cornellAlumniIDNumber);
						String firstName = row.get(2).toString();
						alumniObj.setFirstName(firstName);
						String middleName = row.get(3).toString();
						alumniObj.setMiddleName(middleName);
						String lastName = row.get(4).toString();
						alumniObj.setLastName(lastName);

						
						

						String collegeOrSchool= row.get(5).toString();
						alumniObj.setCollegeOrSchool(collegeOrSchool);

						String major = row.get(6).toString();
						alumniObj.setMajor(major);

						String place= row.get(7).toString();
						alumniObj.setPlace(place);

						String country= row.get(8).toString();
						
						
						alumniObj.setCountry(country);
						
						String city= row.get(9).toString();						
						String province= row.get(10).toString();						
						String photoLink = row.get(11).toString();
						
						String lifeBullet1 = row.get(12).toString();
						alumniObj.setLife_Bullet_1(lifeBullet1);
						String lifeBullet2 = row.get(13).toString();
						alumniObj.setLife_Bullet_2(lifeBullet2);
						String lifeBullet3 = row.get(14).toString();
						alumniObj.setLife_Bullet_3(lifeBullet3);
						String lifeBullet4 = row.get(15).toString();
						alumniObj.setLife_Bullet_4(lifeBullet4);
						String lifeBullet5 = row.get(16).toString();
						alumniObj.setLife_Bullet_5(lifeBullet5);
						String lifeBullet6 = row.get(17).toString();
						alumniObj.setLife_Bullet_6(lifeBullet6);
						String lifeBullet7 = row.get(18).toString();
						alumniObj.setLife_Bullet_7(lifeBullet7);			
						String lifeBullet8 = row.get(19).toString();						
						alumniObj.setLife_Bullet_8(lifeBullet8);
						String lifeBullet9 = row.get(20).toString();
						alumniObj.setLife_Bullet_9(lifeBullet9);
						String lifeBullet10 = row.get(21).toString();
						alumniObj.setLife_Bullet_10(lifeBullet10);			
							String afterGradBullet_1 = row.get(22).toString();						
						alumniObj.setAfter_Grad_Bullet_1(afterGradBullet_1);
						String afterGradBullet_2 = row.get(23).toString();
						alumniObj.setAfter_Grad_Bullet_2(afterGradBullet_2);
						String afterGradBullet_3 = row.get(24).toString();
						alumniObj.setAfter_Grad_Bullet_3(afterGradBullet_3);
						String afterGradBullet_4 = row.get(25).toString();
						alumniObj.setAfter_Grad_Bullet_4(afterGradBullet_4);
						String afterGradBullet_5 = row.get(26).toString();
						alumniObj.setAfter_Grad_Bullet_5(afterGradBullet_5);
						String afterGradBullet_6 = row.get(27).toString();
						alumniObj.setAfter_Grad_Bullet_6(afterGradBullet_6);
						String afterGradBullet_7 = row.get(28).toString();
						alumniObj.setAfter_Grad_Bullet_7(afterGradBullet_7);
						
						String afterGradBullet_8 = row.get(29).toString();
						alumniObj.setAfter_Grad_Bullet_8(afterGradBullet_8);
						String afterGradBullet_9 = row.get(30).toString();
						alumniObj.setAfter_Grad_Bullet_9(afterGradBullet_9);
						String afterGradBullet_10 = row.get(31).toString();
						alumniObj.setAfter_Grad_Bullet_10(afterGradBullet_10);
					/*	String afterGradBullet_11 = row.get(32).toString();
						alumniObj.setAfter_Grad_Bullet_11(afterGradBullet_11);
						String afterGradBullet_12 = row.get(33).toString();
						alumniObj.setAfter_Grad_Bullet_12(afterGradBullet_12);						
						String afterGradBullet_13 = row.get(34).toString();
						alumniObj.setAfter_Grad_Bullet_13(afterGradBullet_13);
						String afterGradBullet_14 = row.get(35).toString();
						alumniObj.setAfter_Grad_Bullet_14(afterGradBullet_14);						
						String afterGradBullet_15 = row.get(36).toString();
						alumniObj.setAfter_Grad_Bullet_15(afterGradBullet_15);		*/
						
						
						String primaryEmail = row.get(37).toString();
						alumniObj.setPrimaryEmail( primaryEmail);
						String alternateEmail = row.get(38).toString();
						alumniObj.setAlternateEmail( alternateEmail);
						String homeAddress = row.get(39).toString();
						alumniObj.setHomeAddress( homeAddress);
						String secondaryAddress = row.get(40).toString();
						alumniObj.setSecondaryAddress( secondaryAddress);
						String telephoneMobile = row.get(41).toString();
						alumniObj.setTelephoneMobile( telephoneMobile);
						String telephoneLandline = row.get(42).toString();
						alumniObj.setTelephoneLandline( telephoneLandline);
						String telephoneOther = row.get(43).toString();
						alumniObj.setTelephoneOther( telephoneOther);
						String personalWebPage1 = row.get(44).toString();
						alumniObj.setPersonalWebPage1( personalWebPage1);
						String personalWebPage2 = row.get(45).toString();
						alumniObj.setPersonalWebPage2( personalWebPage2);
						String blog1 = row.get(46).toString();						
						alumniObj.setBlog1( blog1);
						String blog2 = row.get(47).toString();
						alumniObj.setBlog2( blog2);
						String facebook = row.get(48).toString();
						alumniObj.setFacebookLink( facebook);	
						String twitter = row.get(49).toString();
						alumniObj.setTwitter( twitter);
						String instagram = row.get(50).toString();
						alumniObj.setInstagram( instagram);
						String youTube = row.get(51).toString();
						alumniObj.setYouTube( youTube);
						
						//52 -65
						try{
						  String vimeo= row.get(52).toString();
						  String bandcamp= row.get(53).toString();
						  String otherSocialMedia= row.get(54).toString();
						  String otherUrl= row.get(55).toString();
						  String otherSocialMedia2= row.get(56).toString();
						  String otherURL2= row.get(57).toString();
						  String job= row.get(58).toString();
						  String company= row.get(59).toString();
						  String companyURL= row.get(60).toString();
						  String workAddress= row.get(61).toString();
						  String workEmail= row.get(62).toString();
						  String workTelephone= row.get(63).toString();
						//  String uploadCVURL= row.get(64).toString();
						//  String maidenName= row.get(65).toString();
						  
						  alumniObj.setVimeo( vimeo);
						  alumniObj.setBandcamp( bandcamp);
						  alumniObj.setOtherSocialMedia( otherSocialMedia);
						  alumniObj.setOtherUrl( otherUrl);

						  
						  alumniObj.setOtherSocialMedia2( otherSocialMedia2);
						  alumniObj.setOtherURL2( otherURL2);
						  alumniObj.setJob( job);
						  alumniObj.setCompany( company);

						  
						  alumniObj.setCompanyURL( companyURL);
						  alumniObj.setWorkAddress( workAddress);
						  alumniObj.setWorkEmail( workEmail);
						  alumniObj.setWorkTelephone( workTelephone);
					//	  alumniObj.setUploadCVURL( uploadCVURL);
					//	  alumniObj.setMaidenName( maidenName);		
						  
						  
						} catch(Exception e)
						{}
						  
 

						  				
						  lAlumni.add(alumniObj);

						
						System.out.printf("\n");
					} catch (Exception e) {
						System.out.println("Error" + e);
					}

				}

			}
		}
		
		return lAlumni;
	}

}