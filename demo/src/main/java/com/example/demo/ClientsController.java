package com.example.demo;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.core.publisher.Flux;

@Controller
public class ClientsController {

    private final ReactiveRedisOperations<String, Client> clientOps;

    ClientsController(ReactiveRedisOperations<String, Client> coffeeOps) {
        this.clientOps = coffeeOps;
    }

    @GetMapping("/get-all-position")
    @ResponseBody
    public Flux<Client> all() {
    	System.out.println("sending all positions");
        return (clientOps.keys("*")
                .flatMap(clientOps.opsForValue()::get));
    }

    public void addClientPosition(String id, double lat, double lon) {
		Flux.just("{ \"client_id\": \""+id+"\", \"latitude\": "+lat+", \"longitude\": "+ lon+" }")
        .map(name -> new Client(id, name))
        .flatMap(coffee -> clientOps.opsForValue().set(coffee.getId(), coffee)).subscribe();
	}

	
	@PostMapping("/clients/positions")
	@ResponseBody
	public String registerLocationForClient(@RequestParam(name="json", required=true) String json) {

		JSONObject jsonText;
		try {
			jsonText = new JSONObject(json);
	    	System.out.println("recived from ClientID: "+jsonText.getString("client_id")+ "\n"+jsonText);
	    	addClientPosition(jsonText.getString("client_id"), jsonText.getDouble("latitude"), jsonText.getDouble("longitude"));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
    	
		return "Success";
	}

	

	
}