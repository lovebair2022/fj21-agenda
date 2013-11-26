package br.com.fj21;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdicionaContatoServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// writer
		PrintWriter out = response.getWriter();
		// BUSCANDO PARAMETROS
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String dataText = request.getParameter("dataNascimento");
		Calendar dataNascimento = null;
		try {
			Date data = new SimpleDateFormat("yyyy-dd-MM").parse(dataText);
			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(data);
		} catch (java.text.ParseException e) {
			out.println("<a href=\"http://localhost:8080/fj21-agenda/lista-contatos-scriptlet.jsp\">Visualiza lista</a>");
			return;// para execu��o do metodo
		}

		// criando contato
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(dataNascimento);
		TabContatos salvaContato = new TabContatos();
		try {
			salvaContato.adciona(contato);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erro ao tentar salvar o contato");
			out.println("<a href=\"http://localhost:8080/fj21-agenda/lista-contatos-scriptlet.jsp\">Visualiza lista</a>");
		}

		// imprime nome do contato
		out.println("<html>");
		out.println("<body align=\"center\">");
		out.println("<p> O contato " + contato.getNome()
				+ " foi salvo corretamente<br /> ");
		out.println("<a href=\"http://localhost:8080/fj21-agenda/lista-contatos-scriptlet.jsp\">Visualiza lista</a>");
		out.println("</body>");
		out.println("</html>");

	}// fim classe
}
