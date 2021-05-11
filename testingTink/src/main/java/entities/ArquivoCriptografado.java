package entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ArquivoCriptografado extends Arquivo{
	private FileOutputStream feito; //os dados do arquivo
	public ArquivoCriptografado(File arquivo) throws FileNotFoundException { //inicializador da classe Arquivo, necessita de um File
		super(arquivo);
		setArquivo(arquivo); //criando o OutputStream pelo arquivo
	}
	public FileOutputStream getFeito() { //retornar a OutputStrean
		return feito;
	}
	public void setFeito(File arquivo) throws FileNotFoundException { //configurar a OutputStream, necessita de um arquivo
		this.feito = new FileOutputStream(arquivo.getAbsolutePath());
	}

	}
