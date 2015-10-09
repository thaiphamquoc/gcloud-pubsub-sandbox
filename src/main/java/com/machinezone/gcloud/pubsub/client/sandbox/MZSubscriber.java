package com.machinezone.gcloud.pubsub.client.sandbox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.pubsub.Pubsub;
import com.google.api.services.pubsub.model.AcknowledgeRequest;
import com.google.api.services.pubsub.model.PubsubMessage;
import com.google.api.services.pubsub.model.PullRequest;
import com.google.api.services.pubsub.model.PullResponse;
import com.google.api.services.pubsub.model.ReceivedMessage;

public class MZSubscriber {

	public static void main(String[] args) throws IOException, InterruptedException {
		Pubsub pubsub = PortableConfiguration.createPubsubClient();
		String subscriptionName = "projects/dataflow-sandbox-1085/subscriptions/test-bievent";
		// You can fetch multiple messages with a single API call.
		int batchSize = 10;
		PullRequest pullRequest = new PullRequest()
	        // Setting ReturnImmediately to false instructs the API to
	        // wait to collect the message up to the size of
	        // MaxEvents, or until the timeout.
	        .setReturnImmediately(false)
	        .setMaxMessages(batchSize);
		do {
			Thread.sleep(1000);
		    PullResponse pullResponse = pubsub.projects().subscriptions().pull(subscriptionName, pullRequest).execute();
		    List<String> ackIds = new ArrayList<String>(batchSize);
		    List<ReceivedMessage> receivedMessages = pullResponse.getReceivedMessages();
		    if (receivedMessages == null || receivedMessages.isEmpty()) {
		        // The result was empty.
		        System.out.println("There were no messages.");
		        continue;
		    }
		    for (ReceivedMessage receivedMessage : receivedMessages) {
		        PubsubMessage pubsubMessage = receivedMessage.getMessage();
		        if (pubsubMessage != null && pubsubMessage.decodeData() != null) {		            
	            	System.out.print("Message: ");
	            	System.out.println(new String(pubsubMessage.decodeData(), "UTF-8"));	            
		        }
		        ackIds.add(receivedMessage.getAckId());
		    }
		    // Ack can be done asynchronously if you care about throughput.
		    AcknowledgeRequest ackRequest = new AcknowledgeRequest().setAckIds(ackIds);
		    pubsub.projects().subscriptions().acknowledge(subscriptionName, ackRequest).execute();
		    // You can keep pulling messages by changing the condition below.
		} while (true);	
	}

}
