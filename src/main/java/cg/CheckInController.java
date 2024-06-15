package cg;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/check-in")
public class CheckInController {
	@Autowired
	private CheckInRepo cr;
	
	@PostMapping
	public CheckIn save(@RequestBody CheckIn c) {
		c.setGuests(c.getGuests());
		return cr.save(c);
	}
	
	@GetMapping
	public Page<CheckIn> getAllChecks(@PageableDefault(sort="id", direction=Sort.Direction.ASC) Pageable pageable) {
		return cr.findAll(pageable);
	}
	
	@GetMapping("/{di}")
	public CheckIn getCheckById(@PathVariable("id") int id) {
		return cr.findById(id).orElse(null);
	}
	
	@PutMapping("/{di}")
	public CheckIn updateCheckin(@RequestBody CheckIn c,@PathVariable("id") int id) {
		Optional<CheckIn> e = cr.findById(id);
		if(e.isPresent()) {
			CheckIn o = e.get();
			o.setCheckintime(c.getCheckintime());
			o.setLeavingtime(c.getLeavingtime());
			o.setRoomno(c.getRoomno());
			o.setGuests(c.getGuests());
			return cr.save(o);
		}
		return null;
	}
	
	@DeleteMapping("/{di}")
	public String deleteCheckById(@PathVariable("id") int id) {
		if(cr.findById(id).isPresent()) {
			cr.deleteById(id);
			return "CheckIn Deleted";
		}
		return "No Such CheckIn";
	}
}
