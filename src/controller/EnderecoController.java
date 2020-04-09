package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.EnderecoDAO;
import entidade.Endereco;

@Controller
public class EnderecoController {

	@Autowired
	private EnderecoDAO endDao;

	public void setEnderecoDAO(EnderecoDAO endereDao) {
		endDao = endereDao;
	}

	public EnderecoDAO getEnderecoDAO() {
		return endDao;
	}

	@RequestMapping(value = "/cadastrarEnd")
	public String cadastrarEnd() {
		return "enderecoFormulario";
	}

	@RequestMapping(value = "/enderecoAutenticado")
	public String gravarEnd(@RequestParam String bairro, @RequestParam String cep, @RequestParam String cidade,
			@RequestParam String endereco, @RequestParam String pais, HttpSession session) {

		Endereco end = new Endereco();

		end.setPais(pais);
		end.setCidade(cidade);
		end.setBairro(bairro);
		end.setEndereco(endereco);
		end.setCep(cep);
		
		endDao.cria(end);

		session.setAttribute("endereco", end);

		return "usuarioEnderecoAutenticado";

	}
}
