package entities;
import javax.crypto.SecretKey; //classe para chaves
public class ChaveCodificar {
private SecretKey chave; //Chave que será usada

public ChaveCodificar(SecretKey chave) { //inicializador da ChaveCodificar, necessita de uma SecretKey
	setChave(chave);
}

public SecretKey getChave() { //retorna a SecretKey
	return chave;
}

public void setChave(SecretKey chave) { //configura a SecretKey, necessita de uma SecretKey
	this.chave = chave;
}
}
