package com.pages.utilities;

import java.io.IOException;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.chat.ChatPostMessageRequest;
import com.github.seratch.jslack.api.methods.response.chat.ChatPostMessageResponse;

public class SlackMessageHelper {
	 @SuppressWarnings("finally")
	public ChatPostMessageResponse sendMessage(String channelName, String message, String token) throws IOException {
	        Slack slack = Slack.getInstance();
	        ChatPostMessageResponse postResponse = null;
	        try {
	            postResponse = slack.methods().chatPostMessage(
	                    ChatPostMessageRequest.builder()
	                            .token(token)
	                            .channel(channelName)
	                            .text(message)
	                            .build());
	          
	        } catch (SlackApiException e) {
	        	LoggerProperties.getInstance().getLogger().info(e);
	            
	        }finally {
	        	return postResponse;
	        }
	    }

}
