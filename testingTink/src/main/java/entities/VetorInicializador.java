package entities;
import javax.crypto.spec.IvParameterSpec; //classe do Vetor Inicializante, necessário nesse programa para, junto com a chave, formar a cifra e desfazer ela depois
public class VetorInicializador {
private IvParameterSpec iv; //Vetor que será usado

public VetorInicializador(IvParameterSpec iv) { //inicializador da classe, necessita de um IvParameterSpec 
	setIv(iv);                                  //criado pseudo-randômicamente pelo gerador da classe utilitária Conversao
}

public IvParameterSpec getIv() { //retornar o Iv
	return iv;
}

public void setIv(IvParameterSpec iv) { //configurar o Iv, necessita de um IvParameterSpec
	this.iv = iv;
}
}
