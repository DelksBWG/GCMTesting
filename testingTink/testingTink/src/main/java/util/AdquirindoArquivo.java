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
	if (chosenFile > 0) { //para verificar se o numero é acima de 0
	chosenFileName = listDefaultFiles[chosenFile - 1]; //transformando o numero em um arquivo
	File initialPathToFile = new File(writtenFilePath + chosenFileName + "/"), PathToFile; //criando o segundo arquivo
	while(chosenFile >= 0) { //para acabar, precisa que o chosenFile seja menor que 0
		switch (counter) { //switch case loop dependendo do contador
		case 0: //caso inicial
		listFiles = initialPathToFile.list();
		if (listFiles != null) { //se o arquivo tem arquivos dentro
		for (int i = 0; i < listFiles.length; i++) { //for loop que ocorre pela quantidade de arquivos
			int iPlusOne = i + 1; //criando o número que aparecerá do lado da opção
		System.out.println("(" + (iPlusOne) + ") - " + listFiles[i]); //Imprimir na tela o número da opção e o nome dela
		}
		chosenFile = sc.nextInt(); //o arquivo que entrará será o correspondente ao array de arquivos
		if (chosenFile > 0) { //se o arquivo escolhido for válido
		chosenFileName = listFiles[chosenFile - 1]; //o caminho atualiza
		
		File secondPathToFile = new File(initialPathToFile + "/" + chosenFileName); //inicializando o segundo File com o caminho utilizando o caminho incial e o adicionado
		writtenFilePath = secondPathToFile.getAbsolutePath(); //a partir do segundo File, mudar o String do caminho
		counter =+ 1; //aumentar o contador em 1
		} else { //se for inválido
			break; //acabe com a operação switch
		}
		}
		else { //se o arquivo não tiver arquivos dentro
			writtenFilePath = initialPathToFile.getAbsolutePath(); //o arquivo será o inicial
			chosenFile = 2-3; //acabar com a operação while
			break; //acabar com a operacão switch
		}
		default: //caso geral
			PathToFile = new File(writtenFilePath); //File que será usado é incializado usando o caminho formado pelo string do caminho
			listFiles = PathToFile.list(); //listar os arquivos dentro do arquivo
			if (listFiles != null) { //se tiver algo dentro do arquivo
			for (int i = 0; i < listFiles.length; i++) { //for loop pela duração da lista
				int iPlusOne = i + 1; //criando o número que aparecerá do lado da opção
			System.out.println("(" + (iPlusOne) + ") - " + listFiles[i]); //Imprimir na tela o número da opção e o nome dela
			if (i == listFiles.length - 1) { //se a variável sendo usada para o loop equivale a duração da lista menos 1
				iPlusOne = i + 1; //mudar o número do lado da opção na variável pelo número representando a quantidade de arquivos
				System.out.println("What do you wanna do? (-1 = Stop Searching) (1 - " + iPlusOne + " = Select File)"); //Imprimir as opções
			}
			}
			chosenFile = sc.nextInt(); //o arquivo que entrará será o correspondente ao array de arquivos
			if (chosenFile > 0) { //se o arquivo escolhido for válido
			chosenFileName = listFiles[chosenFile - 1]; //o caminho atualiza
		    writtenFilePath = (writtenFilePath + "/" + chosenFileName); //a partir do caminho atualizado, mudar o String do caminho
			}
			else { //se o arquivo escolhido for inválido
				break; //acabar com a operação 
			}
			} else { //se não tiver nada no arquivo 
				writtenFilePath = PathToFile.getAbsolutePath(); //o caminho atual será o final
				chosenFile = 2-3; //acabar com a operação while
				break; //acabar com a operação switch
			}
		}
	}
	}
		else { //se o número é abaixo de 0
			writtenFilePath = "Inválido"; //O caminho será declarado como inválido
	
		}
	return writtenFilePath;
}
}
