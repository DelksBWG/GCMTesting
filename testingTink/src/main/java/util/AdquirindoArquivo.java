package util;

import java.io.File; //classe para arquivos
import java.util.Scanner; //classe para escanear Inputs

public class AdquirindoArquivo {
public static String ListandoEAdquirindo(Scanner sc){
	int chosenFile, counter = 0; //numero para mover para a pasta e o contador para mais de 2 arquivos de distancia
	String chosenFileName, writtenFilePath = new String("C:/"); //strings das descricoes dos caminhos
	File defaultPath = new File(writtenFilePath); //File do caminho inicial
	String[] listDefaultFiles = defaultPath.list(), listFiles; //listas de arquvios
	for (int i = 0; i < listDefaultFiles.length; i++) { 
		int iPlusOne = i + 1;
	System.out.println("(" + (iPlusOne) + ") - " + listDefaultFiles[i]); //escrita da lista do primeiro diretorio
	}
	chosenFile = sc.nextInt(); //recebe o numero
	if (chosenFile > 0) { //para verificar se o numero � acima de 0
	chosenFileName = listDefaultFiles[chosenFile - 1]; //transformando o numero em um arquivo
	File initialPathToFile = new File(writtenFilePath + chosenFileName + "/"), PathToFile; //criando o segundo arquivo
	while(chosenFile >= 0) { //para acabar, precisa que o chosenFile seja menor que 0
		switch (counter) { //switch case loop dependendo do contador
		case 0: //caso inicial
		listFiles = initialPathToFile.list();
		if (listFiles != null) { //se o arquivo tem arquivos dentro
		for (int i = 0; i < listFiles.length; i++) { //for loop que ocorre pela quantidade de arquivos
			int iPlusOne = i + 1; //criando o n�mero que aparecer� do lado da op��o
		System.out.println("(" + (iPlusOne) + ") - " + listFiles[i]); //Imprimir na tela o n�mero da op��o e o nome dela
		}
		chosenFile = sc.nextInt(); //o arquivo que entrar� ser� o correspondente ao array de arquivos
		if (chosenFile > 0) { //se o arquivo escolhido for v�lido
		chosenFileName = listFiles[chosenFile - 1]; //o caminho atualiza
		
		File secondPathToFile = new File(initialPathToFile + "/" + chosenFileName); //inicializando o segundo File com o caminho utilizando o caminho incial e o adicionado
		writtenFilePath = secondPathToFile.getAbsolutePath(); //a partir do segundo File, mudar o String do caminho
		counter =+ 1; //aumentar o contador em 1
		} else { //se for inv�lido
			break; //acabe com a opera��o switch
		}
		}
		else { //se o arquivo n�o tiver arquivos dentro
			writtenFilePath = initialPathToFile.getAbsolutePath(); //o arquivo ser� o inicial
			chosenFile = 2-3; //acabar com a opera��o while
			break; //acabar com a operac�o switch
		}
		default: //caso geral
			PathToFile = new File(writtenFilePath); //File que ser� usado � incializado usando o caminho formado pelo string do caminho
			listFiles = PathToFile.list(); //listar os arquivos dentro do arquivo
			if (listFiles != null) { //se tiver algo dentro do arquivo
			for (int i = 0; i < listFiles.length; i++) { //for loop pela dura��o da lista
				int iPlusOne = i + 1; //criando o n�mero que aparecer� do lado da op��o
			System.out.println("(" + (iPlusOne) + ") - " + listFiles[i]); //Imprimir na tela o n�mero da op��o e o nome dela
			if (i == listFiles.length - 1) { //se a vari�vel sendo usada para o loop equivale a dura��o da lista menos 1
				iPlusOne = i + 1; //mudar o n�mero do lado da op��o na vari�vel pelo n�mero representando a quantidade de arquivos
				System.out.println("What do you wanna do? (-1 = Stop Searching) (1 - " + iPlusOne + " = Select File)"); //Imprimir as op��es
			}
			}
			chosenFile = sc.nextInt(); //o arquivo que entrar� ser� o correspondente ao array de arquivos
			if (chosenFile > 0) { //se o arquivo escolhido for v�lido
			chosenFileName = listFiles[chosenFile - 1]; //o caminho atualiza
		    writtenFilePath = (writtenFilePath + "/" + chosenFileName); //a partir do caminho atualizado, mudar o String do caminho
			}
			else { //se o arquivo escolhido for inv�lido
				break; //acabar com a opera��o 
			}
			} else { //se n�o tiver nada no arquivo 
				writtenFilePath = PathToFile.getAbsolutePath(); //o caminho atual ser� o final
				chosenFile = 2-3; //acabar com a opera��o while
				break; //acabar com a opera��o switch
			}
		}
	}
	}
		else { //se o n�mero � abaixo de 0
			writtenFilePath = "Inv�lido"; //O caminho ser� declarado como inv�lido
	
		}
	return writtenFilePath;
}
}
