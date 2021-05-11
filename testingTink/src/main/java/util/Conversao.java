package util;


import javax.crypto.KeyGenerator; //classe para gerar chaves
import javax.crypto.SecretKey; //classe de chaves
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec; //classe para criar Vetores Inicializantes
import javax.crypto.spec.SecretKeySpec;

import java.security.SecureRandom; //classe para criar n�meros pseudo-rand�micos
import java.util.Base64;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException; //classe para se certificar de casos de n�o ter um tipo de algoritmo de chave


public class Conversao {
public static SecretKey GerandoChave() throws NoSuchAlgorithmException { //Gerador de chave
	KeyGenerator geradorChave = KeyGenerator.getInstance("AES"); //Gerando uma chave AES
	geradorChave.init(256); //incializando ela
	SecretKey chave = geradorChave.generateKey(); //criando a SecretKey a partir do gerador AES
    return chave; //retornar chave
}
public static IvParameterSpec GerandoIv() { //Gerador de IVs
	byte[] iv = new byte[16]; //criando novo byte array de 16 bytes
	new SecureRandom().nextBytes(iv); //criar novo n�mero pseudo-rand�mico de 16 bytes
	return new IvParameterSpec(iv); //retornar n�mero pseudo-rand�mico
}
public static GCMParameterSpec GerandoGCM(byte[] iv) { //Gerador de Etiqueta de GCM
    GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(16 * 8, iv);
    return gcmParameterSpec;
}
public static String ChaveParaString(SecretKey chave) {
	return Base64.getEncoder().encodeToString(chave.getEncoded());
}
public static SecretKey StringParaChave(String chaveString) throws UnsupportedEncodingException {
	    byte[] chaveByte = Base64.getDecoder().decode(chaveString);
		SecretKey chave = new SecretKeySpec(chaveByte, 0, chaveByte.length, "AES");
		return chave;
}
public static String ByteParaString(byte[] array) throws UnsupportedEncodingException {
	String string = Base64.getEncoder().encodeToString(array);
	return string;
}
public static byte[] StringParaByte(String byteString) throws UnsupportedEncodingException {
	byte[] byteArray = Base64.getDecoder().decode(byteString.getBytes());
		return byteArray;
}
}

