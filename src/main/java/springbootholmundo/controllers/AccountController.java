package springbootholmundo.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.bytebuddy.utility.RandomString;
import springbootholmundo.Utilidad;
import springbootholmundo.models.entity.Users;
import springbootholmundo.service.IUserService;

@Controller
public class AccountController {
	@Autowired
	private IUserService iuserser;
	@Autowired
	private JavaMailSender mailSender;
	@GetMapping("/singup")
	public String singup(Model model) {
		return "singup";
	}
	
	@RequestMapping(value="/new-user", method=RequestMethod.POST)
	public String newUserPost(@ModelAttribute("user") Users user, 
							  @ModelAttribute("new-password") String password, 
							   Model model, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException{
		model.addAttribute("email", user.getEmail());
		model.addAttribute("username", user.getUsername());	
		String randomCode=RandomString.make(64);
		
			
		user = iuserser.crearUsuario(user.getUsername(),  user.getEmail(),password, Arrays.asList("ROLE_USER"), randomCode);	
		String url = Utilidad.getSitioUrl(request);
		sendVerificationEmail(user, url);
		
		return "redirect:/";  
	}

	public void sendVerificationEmail(Users users, String url) throws UnsupportedEncodingException, MessagingException{
		String subject = "Por favor verifique su cuenta";
		String nombreEmpresa = "Sistema Ventas Curso";
		String mailContenido = "Nombre "+users.getUsername();
		mailContenido+="<p>Por favor Verifique su cuenta en el siguiente enlace</p>";
		String verificion = url+"/verificate?code="+users.getVerificationCode();
		mailContenido+="<h3><a href=\""+verificion+"\">Verificar</a></h3>";
		mailContenido+="<p>Gracias por registrarse en Sistame ventas</p>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("dh2179555@gmail.com", nombreEmpresa);
		helper.setTo(users.getEmail());
		helper.setSubject(subject);
		helper.setText(mailContenido, true);
		mailSender.send(message);
		
		
	}
	
	@GetMapping("/verificate")
	public String verficatAccount(@Param("code") String code, Model model) {
		boolean vericaty = iuserser.verificate(code);
		String paginaTitulo=vericaty ? "Se verifico tu cuenta ya puedes loguarte" : "No se pudo verificar intenta de nuevo";
		model.addAttribute("paginatitulo", paginaTitulo);
		return "registre";
	}

}
