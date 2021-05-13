package entities;
import javax.crypto.spec.IvParameterSpec; //classe do Vetor Inicializante, necess�rio nesse programa para, junto com a chave, formar a cifra e desfazer ela depois
public class VetorInicializador {
private IvParameterSpec iv; //Vetor que ser� usado

public VetorInicializador(IvParameterSpec iv) { //inicializador da classe, necessita de um IvParameterSpec 
	setIv(iv);                                  //criado pseudo-rand�micamente pelo gerador da classe utilit�ria Conversao
}

public IvParameterSpec getIv() { //retornar o Iv
	return iv;
}

public void setIv(IvParameterSpec iv) { //configurar o Iv, necessita de um IvParameterSpec
	this.iv = iv;
}
}
