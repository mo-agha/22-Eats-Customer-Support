package bot;

import java.awt.List;

import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.sun.source.tree.Tree;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.CoreQuote;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.util.TypesafeMap;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import javax.swing.JScrollPane;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.Random;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.Translate.Translations;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;


public class bot extends JFrame{

	protected static JTextArea textarea = new JTextArea();
	protected static JTextField chatbox = new JTextField();
	JScrollPane scroll = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	static String [] possibleanswer = {"I'm not sure how I can help you with that.", "Would you like to talk to a real agent? type real agent to do so.","This path is still under construction!", "please provide feedback on what the other options for help may be!", "I'm still learning. Once I grow up I can help you with this"};

	public static String gtext;
	public static String combinedtext;

	public bot() {
		JFrame frame = new JFrame ();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setLayout(null);
		frame.setSize(1600, 1600);
		frame.setTitle("Chat");
		frame.add(textarea);
		frame.add(scroll);
		frame.add(chatbox);
		//for text area
		textarea.setSize(1500, 1400);
		textarea.setLocation(2, 2);
		textarea.add(scroll);

		// for text field
		chatbox.setSize(340, 30);
		chatbox.setLocation(1550,0);

		bot("Welcome to the chat");
		
		//Tried adding a Wikipedia API that reads a fact of the day at the top of the chat, but didn't successfully work so decided to comment it out.
//		URL u = new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&format=json&exintro=&explaintext&redirects=1&pageids=26133246");
//		BufferedReader in = new BufferedReader(
//				new InputStreamReader(u.openStream()));
//
//		String inputLine;
//
//		while ((inputLine = in.readLine()) != null)
//			System.out.println(inputLine);
//		in.close();	
		
		bot("The text box to enter your text is on the right -->");

		bot("");

		translate("Welcome to the chat");
		translate("The text box to enter your text is on the right -->");

		bot("");
		bot("To proceed in English, please say hi!");

		translate("\nTo proceed in French, please say salut!");

		chatbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gtext = chatbox.getText().toLowerCase();
				combinedtext = combinedtext + ". " + gtext;


				textarea.append("you ->" + gtext + "\n");

				chatbox.setText("");

				eng();
				french();

			}

		});

	}
	private static void bot(String s) {
		textarea.append("Bot ->" + s + " \n");
	}
	public static void issuesmenu () {

		bot("1: missing food");
		bot("2: late delivery");
		bot("3: quality of food");
		bot("4: delivery driver");
		bot("5: the app isn't working");
		bot("6: other");
		bot("0: exit");

	}
	
	public static void french_issuesmenu(){
		translate("1: missing food");
		translate("2: late delivery");
		translate("3: quality of food");
		translate("4: delivery driver");
		translate("5: the app isn't working");
		translate("6: other");
		translate("0: exit");
	}

	public static void eng() {

		if(gtext.contains("hi")) {
			bot("Hello, Welcome to T22 Eats Customer Support! Are you an existing member?");
		}
		else if(gtext.contains("yes")) {
			bot("Alright Welcome to the chat. To help you, choose an option from this existing menu." + "\n");
			issuesmenu();
		}else if (gtext.contains("no")) {
			bot("oops, we can only allow registered clients. Get registered and then come back.");
			bot("Are you registered now?");
		}else if (gtext.contains("1")) {
			bot("Enter the order number with the missing food: eg #a 45846");
		}else if(gtext.contains("#a")) {
			bot("Thank you! Could you please tell us which items your order missed?");
		}else if (gtext.contains("fries")||gtext.contains("drink")||gtext.contains("burger")||gtext.contains("chicken")) {
			bot("We apologize for the inconvenience!");
			bot("Would you like a refund or talk to a real agent?");
		}else if(gtext.contains("refund")) {
			bot("Understood. We will provide a refund via in app credits in an hour");
			bot("I hope this solves the problem");
			bot("have a great day. type menu to see the menu again");
		}else if(gtext.contains("talk") || gtext.contains("agent")) {
			bot("Understood. call 4511284 to get the help that I can't provide.");
			bot("have a great day. type menu to see the menu again");
		}

		else if (gtext.contains("2")) {
			bot("Enter the order number with the late delivery: eg #b 45846");
		}
		else if(gtext.contains("#b")) {
			bot("Thank you! Could you please tell us how late the delivery was");
		}
		else if(gtext.contains("thirty")||gtext.contains("ten") ||gtext.contains("fifteen")) {
			bot("I am sorry but this isn't late enough to warrant a refund. would you like to talk a real agent?");
			bot("If yes type real agent");
		}else if (gtext.contains("an hour")||gtext.contains("over an hour") ||gtext.contains("over two hours")) {
			bot("We apologise for the issue. would you like a refund or a conversation with a real agent?");
		}

		else if (gtext.contains("3")) {
			bot("Enter the order number with the poor food quality: eg #c 45846");
		}else if(gtext.contains("#c")) {
			bot("Thank you! Could you please tell us how the food wasn't up to the standards");
		}else if(gtext.contains("cold") || (gtext.contains("not up to the standards"))) {
			bot("We apologise! this must've ruined the experience. We can offer a partial compensation.");
			bot("What items had the low quality");
		}else if (gtext.contains("pizza")||gtext.contains("wrap")||gtext.contains("steak")||gtext.contains("coffee")) {
			bot("Understood. We will provide in app credits to make up for the items. Thank you for letting us know.");
			bot("type menu to check the menu to address another issue. Thank you");
		}

		else if (gtext.contains("4")) {
			bot("Enter the order number with the poor food quality: eg #d 45846");
		}else if(gtext.contains("#d")) {
			bot("Thank you! Could you please tell us what the issue was with the driver");
		}else if(gtext.contains("rude") || gtext.contains("disrespectful") || gtext.contains("shout")) {
			bot("That's awful. We'll get that sorted by talkking to the driver.");
			bot("We'll provide in app credits to make up for the experience. Our apologies");
			bot("If you are unsatisfied, type real agent to talk to a real person and futther discuss this.");
			System.out.println(combinedtext);
		}
		else if (gtext.contains("5")) {
			bot("Thank you for taking the time to provide feedback");
			bot("What is wrong with the app?");
		}else if(gtext.contains("responsive")) {
			bot("WOah! THat's a pain. please try reinstalling the app");
		}else if(gtext.contains("order being placed") || gtext.contains("items in cart")) {
			bot("Understood. This could be as tthe restraunt is closed. if the problem persists, try reinstalling it.");
		}else if(gtext.contains("money being charged is inaccurate")) {
			bot("That's a yikes. have you placed the order already?");
		}else if(gtext.contains("placed food")) {
			bot("Oh no. would you like to refund the order or talk to a real agent?");
		}else if(gtext.contains("haven't ")) {
			bot("Good save. our servers are currently down. please try again later.");
			bot("type menu to return to the main menu!");
		}

		else if (gtext.contains("6")) {
			bot("Check up with a real agent by typing real agent to get stuff fixed that idk how to");


		}else if(gtext.contains("0")) {
			bot("bye bye");
			try {
				FileWriter myWriter = new FileWriter("filename.txt");
				myWriter.write(combinedtext);
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
		else if(gtext.contains("menu")) {
			issuesmenu();
		}else if(gtext.contains("thank you")) {
			bot("You are welcome! I am happy to assist in any way I can");
		}
		else if(gtext.length()==0) {
			bot("Please type something. We can't understand.");
		}
		else {
			if(gtext.contains("salut")) {
				return;
			}
			if(gtext.contains("oui")) {
				return;
			}
			if(gtext.contains("non")) {
				return;
			}
			Random rand = new Random();
			int upperbound = 5;
			int int_rand = rand.nextInt(upperbound);
			bot(possibleanswer[int_rand]);
		}
	}
	public static void french() {
		if(gtext.contains("salut")) {
			translate("Hello, Welcome to T22 Eats Customer Support! Are you an existing member?");
		}
		else if(gtext.contains("oui")) {
			translate("Alright Welcome to the chat. To help you, choose an option from this existing menu." + "\n");
			french_issuesmenu();
		}else if (gtext.contains("non")) {
			translate("oops, we can only allow registered clients. Get registered and then come back.");

			translate("Are you registered now?");
		}else if (gtext.contains("1")) {
			translate("Enter the order number with the missing food: eg #a 45846");
		}else if(gtext.contains("#a")) {
			translate("Thank you! Could you please tell us which items your order missed?");
		}else if (gtext.contains("fries")||gtext.contains("drink")||gtext.contains("burger")||gtext.contains("chicken")) {
			translate("We apologize for the inconvenience!");
			translate("Would you like a refund or talk to a real agent?");
		}else if(gtext.contains("refund")) {
			translate("Understood. We will provide a refund via in app credits in an hour");
			translate("I hope this solves the problem");
			translate("have a great day. type menu to see the menu again");
		}else if(gtext.contains("talk") || gtext.contains("agent")) {
			translate("Understood. call 4511284 to get the help that I can't provide.");
			translate("have a great day. type menu to see the menu again");
		}

		else if (gtext.contains("2")) {
			translate("Enter the order number with the late delivery: eg #b 45846");
		}
		else if(gtext.contains("#b")) {
			translate("Thank you! Could you please tell us how late the delivery was");
		}
		else if(gtext.contains("thirty")||gtext.contains("ten") ||gtext.contains("fifteen")) {
			translate("I am sorry but this isn't late enough to warrant a refund. would you like to talk a real agent?");
			translate("If yes type real agent");
		}else if (gtext.contains("an hour")||gtext.contains("over an hour") ||gtext.contains("over two hours")) {
			translate("We apologise for the issue. would you like a refund or a conversation with a real agent?");
		}

		else if (gtext.contains("3")) {
			translate("Enter the order number with the poor food quality: eg #c 45846");
		}else if(gtext.contains("#c")) {
			translate("Thank you! Could you please tell us how the food wasn't up to the standards");
		}else if(gtext.contains("cold") || (gtext.contains("not up to the standards"))) {
			translate("We apologise! this must've ruined the experience. We can offer a partial compensation.");
			translate("What items had the low quality");
		}else if (gtext.contains("pizza")||gtext.contains("wrap")||gtext.contains("steak")||gtext.contains("coffee")) {
			translate("Understood. We will provide in app credits to make up for the items. Thank you for letting us know.");
			translate("type menu to check the menu to address another issue. Thank you");
		}

		else if (gtext.contains("4")) {
			translate("Enter the order number with the poor food quality: eg #d 45846");
		}else if(gtext.contains("#d")) {
			translate("Thank you! Could you please tell us what the issue was with the driver");
		}else if(gtext.contains("rude") || gtext.contains("disrespectful") || gtext.contains("shout")) {
			translate("That's awful. We'll get that sorted by talkking to the driver.");
			translate("We'll provide in app credits to make up for the experience. Our apologies");
			translate("If you are unsatisfied, type real agent to talk to a real person and futther discuss this.");
			System.out.println(combinedtext);
		}
		else if (gtext.contains("5")) {
			translate("Thank you for taking the time to provide feedback");
			translate("What is wrong with the app?");
		}else if(gtext.contains("responsive")) {
			translate("WOah! THat's a pain. please try reinstalling the app");
		}else if(gtext.contains("order being placed") || gtext.contains("items in cart")) {
			translate("Understood. This could be as tthe restraunt is closed. if the problem persists, try reinstalling it.");
		}else if(gtext.contains("money being charged is inaccurate")) {
			translate("That's a yikes. have you placed the order already?");
		}else if(gtext.contains("placed food")) {
			translate("Oh no. would you like to refund the order or talk to a real agent?");
		}else if(gtext.contains("haven't ")) {
			translate("Good save. our servers are currently down. please try again later.");
			translate("type menu to return to the main menu!");
		}

		else if (gtext.contains("6")) {
			translate("Check up with a real agent by typing real agent to get stuff fixed that idk how to");


		}else if(gtext.contains("0")) {
			translate("bye bye");
			try {
				FileWriter myWriter = new FileWriter("filename.txt");
				myWriter.write(combinedtext);
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
		else if(gtext.contains("menu")) {
			french_issuesmenu();
		}else if(gtext.contains("thank you")) {
			translate("You are welcome! I am happy to assist in any way I can");
		}
		else if(gtext.length()==0) {
			translate("Please type something. We can't understand.");
		}
		else {
			if(gtext.contains("hi")) {
				return;
			}
			if(gtext.contains("yes")) {
				return;
			}
			if(gtext.contains("no")) {
				return;
			}
			Random rand = new Random();
			int upperbound = 5;
			int int_rand = rand.nextInt(upperbound);
			translate(possibleanswer[int_rand]);
		}
	}

	//Translating (to French) method via Google Translate API.

	public static void translate(String phrase) {

		try {
			Translate t = new Translate.Builder(
					GoogleNetHttpTransport.newTrustedTransport()
					, GsonFactory.getDefaultInstance(), null)
					// Set your application name
					.setApplicationName("Stackoverflow-Example")
					.build();
			Translate.Translations.List list = t.new Translations().list(
					Arrays.asList(
							// Pass in list of strings to be translated
							phrase),
					// Target language
					"FR");

			// TODO: Set your API-Key from https://console.developers.google.com/
			list.setKey("AIzaSyD3ciZedcwAgZmD0-1vpVBc2e8fuupcpO0");
			TranslationsListResponse response = list.execute();
			for (TranslationsResource translationsResource : response.getTranslations())
			{
				bot(translationsResource.getTranslatedText());
			}
		}
		catch(IOException e1) {
			System.out.println("IO Exception");
		}
		catch(GeneralSecurityException e2) {
			System.out.println("General Security Exception");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new bot();
		String data = null;
		try {
			File myObj = new File("filename.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = myReader.nextLine();
				System.out.println(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		Properties props = new Properties();
		// set the list of annotators to run
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,quote");
		// set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
		props.setProperty("coref.algorithm", "neural");
		// build pipeline
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		// create a document object
		CoreDocument doc = new CoreDocument(data);
		// annnotate the document
		pipeline.annotate(doc);
		// examples


		System.out.println("---");
		System.out.println("entities found");
		for (CoreEntityMention em : doc.entityMentions())
			System.out.println("\tdetected entity: \t"+em.text()+"\t"+em.entityType());
		System.out.println("---");
		System.out.println("tokens and ner tags");
		String tokensAndNERTags = doc.tokens().stream().map(token -> "("+token.word()+","+token.ner()+")").collect(
				Collectors.joining(" "));
		System.out.println(tokensAndNERTags);

		//		CoreDocument document = pipeline.processToCoreDocument(data);
		//		// display tokens
		//		for (CoreLabel tok : document.tokens()) {
		//			System.out.println(String.format("%s\t%s", tok.word(), tok.tag()));
		//		}





	}

}

