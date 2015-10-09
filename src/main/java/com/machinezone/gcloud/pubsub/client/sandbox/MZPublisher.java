package com.machinezone.gcloud.pubsub.client.sandbox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MZPublisher {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String file = "/data/renaissance/raw_data/bi_event/test_bievent.ody.log";
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
	    String line;
	    while ((line = br.readLine()) != null) {
	    	MZ2GPublisher.Publish(line);
	    }				
	}
}
