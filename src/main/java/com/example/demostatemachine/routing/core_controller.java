package com.example.demostatemachine.routing;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@Controller
public class core_controller {

	@RequestMapping("/")
	public String default_route() {
		return "forward:/entry";
	}

	@RequestMapping("/entry")
	public ResponseEntity<Map<String, String>> entry () {
		return ResponseEntity.ok(Map.of("message", "API entry pointo!"));
	}

	@PostMapping("/greeting")
	public ResponseEntity<Map<String, String>> greeting(@RequestBody @NotNull Map<String, String> payload)
	{
		payload.put("response", "ok");
		return ResponseEntity.ok(payload);
	}
}