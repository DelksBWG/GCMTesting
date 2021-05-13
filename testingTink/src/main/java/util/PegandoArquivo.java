package util;
import java.io.IOException; //classe para se certificar de casos de erros na passagem dos dados a seguir
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException; //classe para se certificar de casos do tipo de algoritmo de chave ser Inv�lido
import java.security.InvalidKeyException; //classe para se certificar de casos da chave ser Inv�lida
import java.security.Key;
import java.security.NoSuchAlgorithmException; //classe para se certificar de casos de n�o ter um tipo de algoritmo de chave
import java.io.BufferedReader; //leitor de dados 
import java.io.File; //classe de Arquivos
import java.io.FileOutputStream;
import java.io.FileReader; //leitor de Arquivos
import java.io.FileWriter;

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
    File dir = new File("C:\\Users");
    File arq = new File(dir, "User.txt");
    testeOutputEncriptacao(textoCifradoString, arq);
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
		    testeOutputDecriptacao(textoCifradoString);
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
public static void testeOutputEncriptacao(String teste, File arq) throws IOException {



  try {
      arq.createNewFile();
      FileWriter fileWriter = new FileWriter(arq, false);
      PrintWriter printWriter = new PrintWriter(fileWriter);
      printWriter.println(teste);
      printWriter.flush();
      printWriter.close();


      FileReader fileReader = new FileReader(arq);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String linha =  bufferedReader.readLine();
      System.out.println(linha);
      bufferedReader.close();

  } catch (IOException e) {
      e.printStackTrace();
  }
}
  public static void testeOutputDecriptacao(String teste) throws IOException {
	  File dir = new File("C:\\Users");
	  File arq = new File(dir, "User.txt");
	 	FileOutputStream outputStream = new FileOutputStream(arq);
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
         switch(cipherMode) {
         case Cipher.DECRYPT_MODE:
        FileReader readingFile = new FileReader(inputFile.getArquivo());
        char[] inputChar = new char[(int) inputFile.getArquivo().length()];
        readingFile.read(inputChar);
        String inputString = new String(inputChar);
        byte[] inputByte = Conversao.StringParaByte(inputString);
        byte[] outputByte = cipher.doFinal(inputByte); //o sistema tá parando aqui, então o problema deve ser na decriptação
        String outputString = Conversao.ByteParaString(outputByte);
        System.out.println("Byte Output: " + outputString);
        testeOutputDecriptacao(outputString);
         
        outputStream.close();
        inputFile.getAceito().close();
        break;
         case Cipher.ENCRYPT_MODE:
        	 String arquivoString = Conversao.FileParaString(inputFile.getAceito(), "UTF-8");
        	    byte[] textoCifrado = cipher.doFinal(arquivoString.getBytes()); //criar array de bytes com a vers�o finalizada da encripta��o
        	    String textoCifradoString = Conversao.ByteParaString(textoCifrado);
        	    testeOutputEncriptacao(textoCifradoString, outputFile.getArquivo());
        	    
        break;
         
    }
    
    }
    catch (NoSuchPaddingException  ex) {
        throw new Exception("NoSuchPaddingException", ex);
    }
    catch (NoSuchAlgorithmException ex) {
        throw new Exception("NoSuchAlgorithmException", ex);
    }
    catch (InvalidKeyException ex) {
        throw new Exception("InvalidKeyException", ex);
    }
    catch (BadPaddingException ex) {
        throw new Exception("BadPaddingException", ex);
    }
    catch (IllegalBlockSizeException ex) {
        throw new Exception("IllegalBlockSizeException", ex);
    }
    catch (IOException ex) {
        throw new Exception("IOException", ex);
    }
    /*catch (NoSuchPaddingException | NoSuchAlgorithmException
            | InvalidKeyException | BadPaddingException
            | IllegalBlockSizeException | IOException ex) {
        throw new Exception("Error encrypting/decrypting file", ex);
    } */
}


}


