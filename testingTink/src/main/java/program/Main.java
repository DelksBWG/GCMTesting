package program;
import util.*;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Qual Processo Deseja Efetuar?");
		System.out.println("1 - Encripta��o       2 - Decripta��o         3 - Teste");
	    int escolha = sc.nextInt();
	    sc.nextLine();
	    switch(escolha) {
	    case 1 :
	    	ProcessosCompletos.CriptacaoString(sc);
	    	break;
	    case 2 :
	    	ProcessosCompletos.DecriptacaoString(sc);
	    	break;
	    case 3 :
	    	PegandoArquivo.testeOutput("Teste Concluído");
	    }
	    System.out.println("Volte Sempre :)");

	}
}