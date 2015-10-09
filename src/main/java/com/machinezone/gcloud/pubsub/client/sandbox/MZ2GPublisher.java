package com.machinezone.gcloud.pubsub.client.sandbox;

import java.io.IOException;
import java.util.List;

import com.google.api.services.pubsub.Pubsub;
import com.google.api.services.pubsub.model.PublishRequest;
import com.google.api.services.pubsub.model.PublishResponse;
import com.google.api.services.pubsub.model.PubsubMessage;
import com.google.common.collect.ImmutableList;

public class MZ2GPublisher {
	public static boolean Publish(String message) throws IOException {
		Pubsub pubsub = PortableConfiguration.createPubsubClient();
		PubsubMessage pubsubMessage = new PubsubMessage();
		// You need to base64-encode your message with
		// PubsubMessage#encodeData() method.
		pubsubMessage.encodeData(message.getBytes("UTF-8"));
		List<PubsubMessage> messages = ImmutableList.of(pubsubMessage);
		PublishRequest publishRequest = new PublishRequest().setMessages(messages);
		PublishResponse publishResponse = pubsub.projects().topics()
		        .publish("projects/dataflow-sandbox-1085/topics/test-bievent", publishRequest)
		        .execute();
		List<String> messageIds = publishResponse.getMessageIds();
		if (messageIds != null) {
		    for (String messageId : messageIds) {
		        System.out.println("messageId: " + messageId);
		    }
		}
		return true;
	}
}
