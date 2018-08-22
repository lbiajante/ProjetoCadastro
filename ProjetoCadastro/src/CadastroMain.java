
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastroMain {
	private Scanner entrada;
	private boolean execute;
	private List<Cadastro> cadastro;
	public static void main(String[] args) {
		new CadastroMain();
	}
	private CadastroMain() {
		entrada = new Scanner(System.in);
		execute = true;
		cadastro = new ArrayList<Cadastro>();
		System.out.println("BEM VINDO AO CADASTRO DE USU�RIOS");
		while (execute) {
			String opcao = menu();
			if (opcao.equals("1")) {
				cadastrar();
			} else if (opcao.equals("2")) {
				listarCadastros();
			} else if (opcao.equals("5")) {
				execute = false;
			} else {
				System.out.println("Op��o Inv�lida!!!");
			}
		}
	}
	private String menu() {
		System.out.println("Selecione a op��o:");
		System.out.println("1 - Novo cadastro");
		System.out.println("2 - Listar cadastros");
		System.out.println("5 - Sair");
		return entrada.nextLine();
	}
	private void cadastrar() {
		boolean cadastrando = true;
		while (cadastrando) {
			System.out.println("Cadastro de Usu�rio");
			Cadastro cad = new Cadastro();
			
			cad.setNome(textInput("Nome: "));
			
			System.out.println("Idade: ");
			cad.setIdade(verificaIdade());
			
			String cadastrar = textInput("Adicionar cadastro (S/N)?");
			if (cadastrar.equalsIgnoreCase("s")) {
				System.out.println("Cadastro adicionado!");
				cadastro.add(cad);
			} else if (cadastrar.equalsIgnoreCase("n")){
				System.out.println("Cadastro ignorado!");
			} else {
				System.out.println("Op��o inv�lida, cadastro ignorado!");
			}
			String continua = textInput("Continuar cadastrando (S/N)?");
			if (continua.equalsIgnoreCase("n")) {
				cadastrando = false;
			} else if (continua.equalsIgnoreCase("s")){
			} else {
				System.out.println("\nOp��o inv�lida !!! \n");
				cadastrando = false;
			}
		}
	}
	private void listarCadastros() {
		if (cadastro.size() == 0) {
			System.out.println("\nN�o existem cadastros !!!\n");
		} else {
			System.out.println("\nLista de Cadastros\n");
			for (int i = 0; i < cadastro.size(); i++) {
				Cadastro d = cadastro.get(i);
				System.out.println("Cadastro n�mero: " + i);
				System.out.println("Nome: " + d.getNome());
				System.out.println("Idade: " + d.getIdade());					
			}
			System.out.println("Fim da lista");
		}
	}
	private String textInput(String label) { //m�todo para inser��es de textos e retornos de teclado
		System.out.println(label);
		return entrada.nextLine();
	}	
	public int verificaIdade(){ //m�todo para evitar inser��es de idades indesejadas
		int idade2 = 0;
		boolean confere = true;
		while(confere){
			
			String idd = entrada.nextLine(); 
			try{
				int inteiro = Integer.parseInt(idd); //verificar se � inteiro
				idade2 = inteiro;
				if (idade2 < 0){ //verificar se � positivo
					System.out.println("A idade precisa ser maior que zero");
					confere = true;
				} else if (idade2 >100){ //verificar se n�o � Matusal�m
					System.out.println("A idade precisa ser menor 100");
					confere = true;
				}
				else {
					confere = false;
				}
			}
			catch(Exception e){
				System.out.printf("Voc� n�o digitou um n�mero inteiro!\n");
			}
		}
		return idade2; 
	}
}