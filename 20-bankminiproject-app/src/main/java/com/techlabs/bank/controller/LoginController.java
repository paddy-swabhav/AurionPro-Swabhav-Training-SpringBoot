package com.techlabs.bank.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.management.RuntimeErrorException;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.techlabs.bank.dto.JwtAuthResponse;
import com.techlabs.bank.dto.LoginDto;
import com.techlabs.bank.dto.RegistrationDto;
import com.techlabs.bank.entity.User;
import com.techlabs.bank.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/bank/users")
public class LoginController {

	@Autowired
	private AuthService authService;
	
	private final DefaultKaptcha defaultKaptcha;

     @Autowired
     public LoginController(DefaultKaptcha defaultKaptcha) {
         this.defaultKaptcha = defaultKaptcha;
     }
	
     
     
	@PostMapping("/register")
	public ResponseEntity<User> register(@Valid @RequestBody RegistrationDto registrationDto)
	{
		return ResponseEntity.ok(authService.register(registrationDto));
	}
	
	@PostMapping("login")
	public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginDto loginDto,HttpServletRequest request, @RequestParam("captcha") String captcha)
	{

		boolean flag;
		String captchaSession = (String) request.getSession().getAttribute("captcha");
        if (!captcha.equals(captchaSession)) 
            throw new RuntimeException("Wrong Captcha");

		
		
		String token = authService.login(loginDto);
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		
		return ResponseEntity.ok(jwtAuthResponse);
	}
	


     @GetMapping("/captcha")
     public void getCaptcha(HttpServletResponse response,HttpServletRequest request) throws IOException {

         String text = defaultKaptcha.createText();
         BufferedImage image = defaultKaptcha.createImage(text);

          request.getSession().setAttribute("captcha", text);

         response.setContentType("image/jpeg");
         OutputStream outputStream = response.getOutputStream();
         ImageIO.write(image, "jpg", outputStream);
         outputStream.close();
     }

}
