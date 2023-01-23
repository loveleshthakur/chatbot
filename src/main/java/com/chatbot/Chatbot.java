package com.chatbot;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;

public class Chatbot {
	private static final boolean TRACE_MODE = false;
	static String botName = "super";
	private static Bot bot;
	private static Chat chatSession;
	
	/**
	 * @author Lovelesh
	 * @param qust Human ask quetion
	 * @return Response is the answer by bot is returned.
	 */
	public static String ask(String qust) {
		String response ="";
		try {

			String resourcesPath = getResourcesPath();
			System.out.println(resourcesPath);
			MagicBooleans.trace_mode = TRACE_MODE;
			if (bot==null) {
			 bot = new Bot("super", resourcesPath);
			 chatSession = new Chat(bot);
			}
			bot.brain.nodeStats();
			String textLine = "";

				System.out.print("Human : ");
				textLine = qust;
				if ((textLine == null) || (textLine.length() < 1))
					textLine = MagicStrings.null_input;
				
					String request = textLine;
					if (MagicBooleans.trace_mode)
						System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
					
					// Passing our question to third party ALICE Bot 
					response = chatSession.multisentenceRespond(request);
					while (response.contains("&lt;"))
						response = response.replace("&lt;", "<");
					while (response.contains("&gt;"))
						response = response.replace("&gt;", ">");
					System.out.println("Robot : " + response);
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
/**
 * This method is used to get the current directory path
 * @return resource path
 */
	private static String getResourcesPath() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 2);
		System.out.println(path);
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}

}
