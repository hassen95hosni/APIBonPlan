package bon.plan.controllers;

import java.util.List;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

import bon.plan.connectors.ConnectionDb;
import bon.plan.entities.Message;

@RestController
public class NotificationMqtt {

	
	String publisherId = UUID.randomUUID().toString();
	IMqttClient publisher;
	
	
	@Scheduled(fixedRate = 10000)
		public void ma() throws MqttException {
		publisher = new MqttClient("tcp://localhost:1883/notification",publisherId);
		MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(true);
		options.setConnectionTimeout(10);
		publisher.connect(options);
		MqttMessage msg = new MqttMessage("hi".getBytes());
		msg.setQos(0);
		msg.setRetained(true);
		ConnectionDb c= new ConnectionDb();
		Connection connection = c.getConnection();
		RethinkDB r =c.getR();
	
		Cursor<Message> cursor = r.db("notification").table("test").changes().run(connection);
		List<Message> listmsg =cursor.toList();
		for(int i = 0 ; i <listmsg.size();i++) {
    	msg.setPayload(listmsg.get(i).getText().getBytes());
    	publisher.publish("notification",msg);	
    }
	
}


}
