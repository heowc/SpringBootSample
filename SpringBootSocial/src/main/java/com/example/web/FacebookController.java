package com.example.web;

import com.example.social.facebook.FacebookUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FacebookController {

	@Autowired Facebook facebook;
	@Autowired ConnectionRepository connectionRepository;

	private static final Logger logger = Logger.getLogger(FacebookController.class);

	@GetMapping
	public String helloFacebook(Model model) {
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return "redirect:/connect/facebook";
		}

		FacebookUser facebookUser = facebook.fetchObject("/me", FacebookUser.class);

		logger.info(facebookUser);

		model.addAttribute("facebookProfile", facebookUser);
		return "main";
	}
}