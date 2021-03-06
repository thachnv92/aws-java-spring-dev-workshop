package hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.model.Image;
import hello.repository.ImageRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/workshop/images") // This means URL's start with /workshop (after Application path)
public class ImageController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private ImageRepository imageRepository;

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String userid
			, @RequestParam String bucket , @RequestParam String prefix, @RequestParam String filename) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Image n = new Image();
		n.setUser_id(Integer.parseInt(userid));
		n.setBucket(bucket);
		n.setPrefix(prefix);
		n.setFileName(filename);
		
		imageRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Image> getAllUsers() {
		// This returns a JSON or XML with the users
		return imageRepository.findAll();
	}
	
	@GetMapping(path="/deleteall")
	public @ResponseBody String deleteAllUsers() {
		// This returns a JSON or XML with the users
		try {
			imageRepository.deleteAll();
			return "OK";
		} catch (Exception e) {
			return "Deletel all Error";
		}
	
	}
}
