package util;
import java.io.IOException; //classe para se certificar de casos de erros na passagem dos dados a seguir
import java.security.InvalidAlgorithmParameterException; //classe para se certificar de casos do tipo de algoritmo de chave ser Inv�lido
import java.security.InvalidKeyException; //classe para se certificar de casos da chave ser Inv�lida
import java.security.Key;
import java.security.NoSuchAlgorithmException; //classe para se certificar de casos de n�o ter um tipo de algoritmo de chave
import java.io.BufferedReader; //leitor de dados 
import java.io.File; //classe de Arquivos
import java.io.FileOutputStream;
import java.io.FileReader; //leitor de Arquivos
import javax.crypto.BadPaddingException; //classe para se certificar de casos de quando os requerimentos de bytes para os dados recebidos n�o s�o correspondidos
import javax.crypto.Cipher; //classe de Cifras
import javax.crypto.IllegalBlockSizeException; //classe para se certificar de casos do tamanho do bloco n�o corresponder ao requerimento
import javax.crypto.NoSuchPaddingException; //classe para se certificar de casos de quando os requerimentos de bytes para os dados recebidos n�o est�o dispon�veis
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import entities.*; //importar o pacote com as entidades
public class PegandoArquivo {
public static void adquirindo(ChaveCodificar chave, GCMParameterSpec gcm, String algoritmo, Arquivo arquivo, //classe para alterar o File na classe arquivo utilizando a Cipher cifra
		ArquivoCriptografado destino) throws Exception {
	doCrypto(Cipher.ENCRYPT_MODE, chave, gcm, arquivo, destino);
    arquivo.getAceito().close(); //fechando as Streams do Input e Output 
    destino.getAceito().close();
}
public static String adquirindoString(ChaveCodificar chave, GCMParameterSpec gcm, String algoritmo, String arquivo //classe para alterar o File na classe arquivo utilizando a Cipher cifra
 ) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    Cipher cifra = Cipher.getInstance(algoritmo); //inicializar a Cipher cifra na inst�ncia do String algoritmo
    cifra.init(Cipher.ENCRYPT_MODE, chave.getChave(), gcm); //iniciar a encripta��o de cifra com a chave e iv
    byte[] textoCifrado = cifra.doFinal(arquivo.getBytes()); //criar array de bytes com a vers�o finalizada da encripta��o
    String textoCifradoString = Conversao.ByteParaString(textoCifrado);
    testeOutput(textoCifradoString);
    return textoCifradoString;
}
public static void retornando(ChaveCodificar chave, GCMParameterSpec gcm, String algoritmo, Arquivo arquivo, //classe para alterar o File na classe arquivo utilizando a Cipher cifra
		ArquivoCriptografado destino) throws Exception {

    	 
    	 doCrypto(Cipher.DECRYPT_MODE, chave, gcm, arquivo, destino);

    arquivo.getAceito().close(); //fechando as Streams do Input e Output 
    destino.getAceito().close();
}
public static String retornandoString(ChaveCodificar chave, GCMParameterSpec gcm, String algoritmo, String arquivo //classe para alterar o File na classe arquivo utilizando a Cipher cifra
		 ) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		    Cipher cifra = Cipher.getInstance(algoritmo); //inicializar a Cipher cifra na inst�ncia do String algoritmo
		    cifra.init(Cipher.DECRYPT_MODE, chave.getChave(), gcm); //iniciar a encripta��o de cifra com a chave e iv
		    byte[] textoCifrado = cifra.doFinal(Conversao.StringParaByte(arquivo)); //criar array de bytes com a vers�o finalizada da encripta��o
		    String textoCifradoString = Conversao.ByteParaString(textoCifrado);
		    testeOutput(textoCifradoString);
		    return textoCifradoString;
		}
public static void imprimindo(File arquivo) throws IOException{ //classe para imprimir o File arquivo
	try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) { //tentar ler o arquivo
		   String linha; //inicializar a vari�vel representativa da linha lida
		   while ((linha = br.readLine()) != null) { //enquanto a linha lida � diferente de um valor nulo
		       System.out.println(linha); //imprimir a linha
		   }
		}
}
public static void testeOutput(String teste) throws IOException {
 	FileOutputStream outputStream = new FileOutputStream("C:\\Users\\delks\\OneDrive\\Documentos\\teste.txt");
    byte[] byteTeste = Conversao.StringParaByte(teste);
 	outputStream.write(byteTeste);
 	outputStream.close();
}
private static void doCrypto(int cipherMode, ChaveCodificar chave, GCMParameterSpec gcm, Arquivo inputFile,
        ArquivoCriptografado outputFile) throws Exception{
    final String ALGORITHM = "AES";
    final String TRANSFORMATION = "AES/GCM/NoPadding";
    FileOutputStream outputStream = new FileOutputStream(outputFile.getArquivo());
    try {
    	Key secretKey = new SecretKeySpec(chave.getChave().getEncoded(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(cipherMode, secretKey, gcm);
         
        byte[] inputBytes = new byte[(int) inputFile.getArquivo().length()];
        inputFile.getAceito().read(inputBytes);
        
        byte[] outputBytes = cipher.doFinal(inputBytes);
        System.out.println("Byte Output: " + Conversao.ByteParaString(outputBytes));
        outputStream.write(outputBytes);
         
        outputStream.close();
        inputFile.getAceito().close();
         
    } catch (NoSuchPaddingException | NoSuchAlgorithmException
            | InvalidKeyException | BadPaddingException
            | IllegalBlockSizeException | IOException ex) {
        throw new Exception("Error encrypting/decrypting file", ex);
    }
}
}


