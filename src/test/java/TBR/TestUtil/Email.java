package TBR.TestUtil;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import com.sun.mail.util.MailSSLSocketFactory;

public class Email {

	private static int timeToWait = 10; //10 seconds per each iteration
	
	public Email(){
	}	
	/**
	 * HTML or rich text mail
	 * @param email - username
	 * @param password
	 * @param subject - it is the subject of the email that we are looking for
	 * @return email content
	 * @throws Exception
	 */
	
		public String receiveAndDeleteMultiPart(String email, String pass, String subject) throws Exception {
			//Properties props = System.getProperties();
			Properties props = new Properties();
			props.put("mail.store.protocol", "imaps");
			//the four lines are for trusting the imaps ssl certificate
			MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
			socketFactory.setTrustAllHosts(true);
			props.put("mail.imaps.ssl.socketFactory", socketFactory);
			props.put("mail.pop3s.ssl.socketFactory", socketFactory);
			try{
				Session session = Session.getDefaultInstance(props);
				Store store = session.getStore("imaps");
                store.connect("imap.gmail.com", email, pass);
                
                Folder folder = store.getFolder("INBOX");
                folder.open(Folder.READ_WRITE);
                
                Message[] messages = null;
                boolean isMailFound = false;
                Message mailFromGod = null;
                
                Flags seen = new Flags(Flags.Flag.SEEN);
                FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
                SearchTerm searchTerm = new AndTerm(new SubjectTerm(subject), unseenFlagTerm);
                
                //Search for mail
                for (int i=0; i<timeToWait; i++){
                messages = folder.search(searchTerm, folder.getMessages());
                //Wait for 10 seconds
                if(messages.length ==0)
                	Thread.sleep(10000);
                else
                	break;
                }
	            
                //Search for unread mail
                //This is to avoid using the mail for which Registration is already done
                for(Message mail : messages) {
                if(!mail.isSet(Flags.Flag.SEEN)){
                	mailFromGod = mail;
                	isMailFound = true;
                  }
                }
                
                //Test fails if no unread mail was found
                if(!isMailFound){
                	throw new Exception("Could not find new mail with subject: "+ subject + ",  after "+ (timeToWait * 10) + "s.");
                	
                // Read the content of mail and launch registration
                }else {
                	Multipart mp = (Multipart) mailFromGod.getContent();
                	String content = "";
                	for(int i=0; i<mp.getCount(); i++){
                		BodyPart bp = mp.getBodyPart(i);
                		if(bp.isMimeType("multipart/*")){
                			Multipart mmp = (Multipart)bp.getContent();
                			int count = mmp.getCount();
                			for(int j=0; j<count; j++){
                				if(mmp.getBodyPart(j).isMimeType("text/html")){
                					content = mmp.getBodyPart(j).getContent().toString();
                			}
                		}
                	}
                }
                	if(content.isEmpty()){
                		BodyPart bp = mp.getBodyPart(0);
                		content = bp.getContent().toString();
                	}
                	mailFromGod.setFlag(Flags.Flag.DELETED, true);
                	return content;
                }
                	}catch (Exception e){
                	throw new Exception("Problems to receive email. " + e.toString());
                	}
                }
			
			
			public String receiveAndDelete(String email, String pass, String subject)
					throws Exception {
				//Properties props = System.getProperties();
				Properties props = new Properties();
				props.put("mail.store.protocol", "imaps");
				MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
				socketFactory.setTrustAllHosts(true);
				props.put("mail.imaps.ssl.socketFactory", socketFactory);
				props.put("mail.pop3s.ssl.socketFactory", socketFactory);
				try {
					Session session = Session.getDefaultInstance(props);
					Store store = session.getStore("imaps");
					store.connect("imap.gmail.com", email, pass);

					Folder folder = store.getFolder("INBOX");
					folder.open(Folder.READ_WRITE);

					Message[] messages = null;
					boolean isMailFound = false;
					Message mailFromGod = null;
					Flags seen = new Flags(Flags.Flag.SEEN);
					FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
					SearchTerm searchTerm = new AndTerm(new SubjectTerm(subject),unseenFlagTerm);
					// Search for mail from God
					for (int i = 0; i < 5; i++) {
						messages = folder.search(searchTerm, folder.getMessages());
						// Wait for 10 seconds
						if (messages.length == 0)
							Thread.sleep(10000);
						else
							break;
						}
					// Search for unread mail from God
					// This is to avoid using the mail for which
					// Registration is already done
					for (Message mail : messages) {
						if (!mail.isSet(Flags.Flag.SEEN)) {
							mailFromGod = mail;
							// System.out.println("Message Count is: " +
							// mailFromGod.getMessageNumber());
							isMailFound = true;
							}
						}

					// Test fails if no unread mail was found from God
					if (!isMailFound) {
						throw new Exception("Could not find new mail with subject: "+ subject + ", after 50s.");

						// Read the content of mail and launch registration URL
						} else {
							String content = mailFromGod.getContent().toString();
							mailFromGod.setFlag(Flags.Flag.DELETED, true);
							return content;
							}
					} catch (Exception e) {
						throw new Exception("Problems to receive email. " + e.toString());
						}
				}
			}

