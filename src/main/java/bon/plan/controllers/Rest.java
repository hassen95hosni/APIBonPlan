package bon.plan.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;


import bon.plan.connectors.ConnectionDb;
import bon.plan.entities.Category;
import bon.plan.entities.Conversation;
import bon.plan.entities.Message;
import bon.plan.entities.Plan;
import bon.plan.entities.Produit;
import bon.plan.entities.User;
import bon.plan.views.CategoryView;
import bon.plan.views.ConversationView;
import bon.plan.views.PlanView;
import bon.plan.views.ProduitView;
import bon.plan.views.UserView;
import retrofit2.http.GET;

@RestController
public class Rest {
	@Autowired
		UserView userview;
	@Autowired
		PlanView planView;
	@Autowired
		ConversationView conversationView;
	@Autowired
	CategoryView categoryView;
	@Autowired
	ProduitView produitView;


	
	

	//produitapi
	@GetMapping("produits/{id}")
	public List<Produit> getAllproduits(@PathVariable Long id){
		return produitView.getbyplan(id);
	}
	@GetMapping("produit/{id}")
	public Produit getAllproduit(@PathVariable Long id){
		return produitView.getOne(id);
	}

    
	@PostMapping("produit/add")
	public String addproduit(@RequestBody Produit produit) {
	try {
		produitView.saveAndFlush(produit);
		return "true";
		
	} catch (Exception e) {
		
		return "false";
	
	}
}


	@GetMapping("produit/delete/{id}")
	public String deleteproduit(@PathVariable Long id)
	{	try {
		produitView.deleteById(id);
		return "true";
	
		} catch (Exception e) {
	
		return "false";
	
		}
	}


	
	
	
	//plan api
	@GetMapping("plansbycat/{id}")
	public List<Plan> getAllplanbycat(@PathVariable Long id){
		return planView.getbycatg(id);
}
	

@GetMapping("plans")
	public List<Plan> getAllplan(){
		return planView.findAll();
	}

	@GetMapping("plans/auth")
	public List<Plan> getAllplans(){
		return organize(planView.findAll());
	}

	public List<Plan> organize(List<Plan> plans){
		List<Plan> lists = new ArrayList<>();
		int place;
		Plan p = new Plan();
		for (int i = 0;i<plans.size();i++) {
			place = getbigger(plans, p);
			lists.add(plans.get(place));
			plans.remove(place);
		}
		
		return lists;
		
	}
	public int getbigger(List<Plan> plans ,Plan p) {
		int place =0;
		 p = plans.get(0);
		for (int i =0 ; i<plans.size();i++) {
			if (p.getRate()<plans.get(i).getRate()) {
				p = plans.get(i);
				place = i;
			}
		}
		return place;
	}
	
	@GetMapping("plan/getone/{id}")
	public Optional<Plan> getOneplan(@PathVariable Long id ){
		return planView.findById(id);
	}

	@GetMapping("plan/rate/{id}")
	public String rateOneplan(@PathVariable Long id ){
		return "done";
	}

    
@PostMapping("plan/post")
public String add(@RequestBody Plan plan) {
	try {
		planView.saveAndFlush(plan);
		return "true";
		
	} catch (Exception e) {
		
		return "false";
	
	}
}


@GetMapping("plans/delete/{id}")
public String delete(@PathVariable Long id)
{try {
	planView.deleteById(id);
	return "true";
	
} catch (Exception e) {
	
	return "false";
	
}}



//conersation api
@GetMapping("cnv")
public List<Conversation> getAllcnv(){
	return conversationView.findAll();
}

@GetMapping("cnv/getone/{id}")
public Optional<Conversation> getOnecnv(@PathVariable Long id ){
	return conversationView.findById(id);
}


@PostMapping("post")
public String addcnv(@RequestBody Conversation plan) {
try {
	conversationView.saveAndFlush(plan);
	return "true";
	
} catch (Exception e) {
	
	return "false";

}
}


@GetMapping("delete/{id}")
public String deletecnv(@PathVariable Long id)
{try {
conversationView.deleteById(id);
return "true";

} catch (Exception e) {

return "false";

}}

//category api 
@GetMapping("category")
public List<Category> getAllcat(){
	return categoryView.findAll();
}

@GetMapping("cat/getone/{id}")
public Optional<Category> getOnecat(@PathVariable Long id ){
	return categoryView.findById(id);
}





	//authentication

@PostMapping("loginin")
public User authentication(@RequestBody User user) {

	User us =userview.authent(user.getEmail(), user.getPassword());
	if (us.getEmail().equals("")) {
		return us;	
	}
	else 
		return us;	
}


public String crypt(String text ) {
   try {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] array = md.digest(text.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
       }
        return sb.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
    }
    return null;
}

	//user api 
@PostMapping("user/inscrit")
public String adduser(@RequestBody User user) {
try {
	userview.saveAndFlush(user);
	return "true";
	
} catch (Exception e) {
	
	return "false";

}
}



	

	
	
	@GetMapping("hello")
	public String hello() {
		return "hello world ";
	}
	@GetMapping("all")
	public List<User> all() {
		return userview.findAll();
	}
	@GetMapping("allz")
	public User getallz() {
		return userview.authent("hiba@gmail.com", "hiba");
	//return "not yet";
	}
	@GetMapping("convs")
	public List<Conversation> getAllConvs(){
		return conversationView.findAll();
	}
	
    
	}
	
    

