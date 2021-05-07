package entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Arquivo {
	private FileInputStream Aceito;
	private File Arquivo;
	public Arquivo(File arquivo) throws FileNotFoundException {
		setAceito(new FileInputStream(arquivo));
		setArquivo(arquivo);
	}
	public FileInputStream getAceito() {
		return Aceito;
	}
	public void setAceito(FileInputStream conteudoArquivo) {
		this.Aceito = conteudoArquivo;
	}
	public File getArquivo() {
		return Arquivo;
	}
	public void setArquivo(File arquivo) {
		Arquivo = arquivo;
	}

}
