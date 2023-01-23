package com.chatbot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatbot.Chatbot;
/**
 * This Rest Client will accept the question and ask the bot to get Answer 
 * @author Lovelesh
 */
@RestController
public class AskController {
	public static String preQA="";
	public static int countQuestion=0;
	
	/**
	 *This method accepts user input which will be processed by Alice bot
	 * and construct the html response for displaying it
	 *
	 * @param question is passed 
	 */
	
	@PostMapping("/ask")
    public String  getResponse(@RequestParam String question) 
    
	{

		countQuestion++;
		String result = "";
		if (countQuestion == 1) {
			result = " <form action=\"/ask\" method=\"post\">  <input type= \"text\" name =\"question\" id=\"question\" > <input type=\"submit\"  value=\"ask\" >   </form> "+"Human: " + question;
			return preQA = preQA +result+"</br> Robot : "+ Chatbot.ask(question);
		}
		return preQA = preQA + "</br> Human: " + question + " </br>Robot :"+ Chatbot.ask(question);
		 
		 
		
    }

	
}
