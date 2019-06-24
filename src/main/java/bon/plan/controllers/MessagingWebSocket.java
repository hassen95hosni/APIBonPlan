package bon.plan.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

import bon.plan.connectors.ConnectionDb;
import bon.plan.entities.Message;

@RestController
@RequestMapping("/message")
public class MessagingWebSocket {
/*
	private static final String SENDING_URL = "/topic/server-broadcaster";
    private static final String RECEIVING_URL = "/server-receiver";
    
    private final SimpMessagingTemplate template;
    private AtomicLong counter = new AtomicLong(0);

    private String message = "";
    public String id ="";
	
	
//web socket connection
    @Autowired
	public MessagingWebSocket(SimpMessagingTemplate template) {
		super();
		this.template = template;
		
	}
//subscribing to websocket
	@SubscribeMapping(SENDING_URL)
    public String onSubscribe() {
        return "SUBSCRIBED : " + message;
	}
//sending messages
	@Scheduled(fixedRate = 10000)
    public void sendMessage() {
    	ConnectionDb c = new ConnectionDb();
    		  Connection conn = c.getConnection();
    		RethinkDB r = c.getR();
    		List<Message> t = new ArrayList<Message>();
    		Cursor<Message> cursor=r.db("messages").table("test").changes().run(conn);
    	t=cursor.toList();
    	template.convertAndSend(SENDING_URL, buildNextMessage(t.get(t.size()).toString()));		
    		}
  //building message
	private String buildNextMessage(String text) {
        message = text;
        System.out.println("Send message " + message);
	
			return message;
	//	}
		
    }
*/
	
	
	String publisherId = UUID.randomUUID().toString();
	IMqttClient publisher;
	
/*	
	@Scheduled(fixedRate = 10000)
		public void ma() throws MqttException {
		publisher = new MqttClient("tcp://localhost:1883/message",publisherId);
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
	
		Cursor<Message> cursor = r.db("message").table("test").changes().run(connection);
		List<Message> listmsg =cursor.toList();
		for(int i = 0 ; i <listmsg.size();i++) {
    	msg.setPayload(listmsg.get(i).getText().getBytes());
    	publisher.publish("message",msg);	
    }
	*/
//}

	
	
	
@GetMapping("get")
public String getAll() {
	ConnectionDb c= new ConnectionDb();
	Connection connection = c.getConnection();
	RethinkDB r =c.getR();

	Cursor<Message> cursor = r.db("message").table("test").changes().run(connection);
	List<Message> listmsg =cursor.toList();

	
	return listmsg.toString();
	
}
    
@PostMapping("post")
public String add(@RequestBody String msg) {
	try {
		ConnectionDb c= new ConnectionDb();
		Connection connection = c.getConnection();
		RethinkDB r =c.getR();
		r.db("messages").table("test").insert(r.array(r.hashMap("text",msg))).run(connection);
		
		return "true";
		
	} catch (Exception e) {
		
		return "false";
	
	}
}


@GetMapping("delete/{id}")
public String delete(@PathVariable int id)
{try {
	ConnectionDb c= new ConnectionDb();
	Connection connection = c.getConnection();
	RethinkDB r =c.getR();
	r.db("messages").table("test").get(id).delete().run(connection);
	
	return "true";
	
} catch (Exception e) {
	
	return "false";
	
}}


}
