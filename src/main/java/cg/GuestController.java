package cg;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
	@Autowired
	private GuestRepo gr;
	
	@PostMapping
	public Guest save(@RequestBody Guest c) {
		return gr.save(c);
	}
	
	@GetMapping
	public Page<Guest> getAllGuests(@PageableDefault(sort="id", direction=Sort.Direction.ASC) Pageable pageable) {
		return gr.findAll(pageable);
	}
	
	@GetMapping("/{di}")
	public Guest getCheckById(@PathVariable("id") int id) {
		return gr.findById(id).orElse(null);
	}
	
	@PutMapping("/{di}")
	public Guest updateGuest(@RequestBody Guest c,@PathVariable("id") int id) {
		Optional<Guest> e = gr.findById(id);
		if(e.isPresent()) {
			Guest o = e.get();
			o.setName(c.getName());
			o.setAddress(c.getAddress());
			o.setPhonenumber(c.getPhonenumber());
			return gr.save(o);
		}
		return null;
	}
	
	@DeleteMapping("/{di}")
	public String deleteGuestById(@PathVariable("id") int id) {
		if(gr.findById(id).isPresent()) {
			gr.deleteById(id);
			return "Guest Deleted";
		}
		return "No Such Guest";
	}
}
