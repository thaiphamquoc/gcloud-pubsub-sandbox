# Requirements

*   Maven [3.1.x,)
*   Eclipse Luna 4.4
*   Java SDK [1.7.x,)
*   Google Cloud Setup

# Setting up local dev environment

## Install Java SDK [1.7.x,)

Install [Java SDK 8][1]

## Install Eclipse Luna 4.4

Install [Eclipse Luna 4.4][2] used in this tutorial. Other Eclipse versions may not have Google APIs plugin.

## Install Google APIs Plugins

*   Open Eclipse 
*   Help -> Install New Software... 
*   Add 'https://dl.google.com/eclipse/plugin/4.4' 
*   Click Next -> Next -> Finish 
*   Restart Eclipse

## Install Maven

Install the latest [Maven][3]

## Set up Google Cloud PubSub

Follow the instructions on [Google Cloud Pubsub][4] page to create

*   Google Account
*   Google Cloud project
*   Enable APIs

## Create a PubSub Topic and Subscription

*   Go to [Google Developer Console][5]
*   Go to your project created previously
*   Select Pub/Sub
*   Create new topic
*   Create new subscription

## Set up Google application default credentials

The simplest way to get a credential for this purpose is to create a service account using the [Google Developers Console][5] in the section APIs & Auth, in the sub-section Credentials. Create a service account or choose an existing one and select Generate new JSON key. Set the environment variable to the path of the JSON file downloaded. - [Google Application Default Credentials][6]

    echo "export GOOGLE_APPLICATION_CREDENTIALS=/path/to/downloaded/authentication/file.json" > ~/.bash_profile
    source ~/.bash_profile
    

# Create a PubSub Client

## Download a Google PubSub Client sample

*   Download [my Google Cloud PubSub sample][7]
*   Prepare a sample log which is used to stream to the Pub/Sub topic
*   Update variable 'file' in com.machinezone.gcloud.pubsub.client.sandbox.MZPublisher.java 
*   Update the topic name in com.machinezone.gcloud.pubsub.client.sandbox.MZPublisher.java 
*   Update the subscription name in com.machinezone.gcloud.pubsub.client.sandbox.MZSubscriber.java 

## Run in command-line

    cd /your/downloaded/sample/code/dir
    mvn clean install
    # start the subscriber
    java -cp target/pubsub-sandbox-0.0.1-SNAPSHOT.jar com.machinezone.gcloud.pubsub.client.sandbox.MZSubscriber
    # start sending messages
    java -cp target/pubsub-sandbox-0.0.1-SNAPSHOT.jar com.machinezone.gcloud.pubsub.client.sandbox.MZPublisher
    

## Run in Eclipse

*   Import as an Eclipse project
*   Right click on the project -> Google -> Add Google APIs...
*   Add Google OAuth API
*   Add Google Cloud Pub/Sub API
*   Prepare a log file (file path is in com.machinezone.gcloud.pubsub.client.sandbox.MZPublisher)
*   Start the Publisher by right click on MZPublisher.java -> Run As... -> Java Application
*   Start the Subscriber by right click on MZSubscriber.java -> Run As... -> Java Application

 [1]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
 [2]: http://www.eclipse.org/downloads/packages/eclipse-standard-44/lunar
 [3]: https://maven.apache.org/install.html
 [4]: https://cloud.google.com/pubsub/prereqs
 [5]: https://console.developers.google.com
 [6]: https://developers.google.com/identity/protocols/application-default-credentials
 [7]: https://github.com/thaiphamquoc/gcloud-pubsub-sandbox