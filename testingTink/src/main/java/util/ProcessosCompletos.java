package util;
import java.io.File;
import java.util.Scanner;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;

import entities.*;
public class ProcessosCompletos {
public static void Criptacao(Scanner sc) {
	String caminho = new String(AdquirindoArquivo.ListandoEAdquirindo(sc));
	sc.nextLine();
	try {
	Arquivo arquivoRecebido = new Arquivo(new File(caminho));
    SecretKey chave = Conversao.GerandoChave();
    ChaveCodificar chaveFinal = new ChaveCodificar(chave);
    String chaveString = Conversao.ChaveParaString(chaveFinal.getChave());
    System.out.println(chaveString);
    IvParameterSpec ivParameterSpec = Conversao.GerandoIv();
    VetorInicializador vi = new VetorInicializador(ivParameterSpec);
    String ivString = Conversao.ByteParaString(vi.getIv().getIV());
    System.out.println(ivString);
    String algoritmo = "AES/GCM/NoPadding";
    GCMParameterSpec gcmParameterSpec = Conversao.GerandoGCM(vi.getIv().getIV());
    ChaveGCM gcm = new ChaveGCM(gcmParameterSpec);
    ArquivoCriptografado resultado = new ArquivoCriptografado(new File(caminho));	 
    PegandoArquivo.adquirindo(chaveFinal, gcm.getGcmTag(), algoritmo, arquivoRecebido, resultado);
    PegandoArquivo.imprimindo(resultado.getArquivo());
}
catch(Exception e) {
System.out.println(caminho);
sc.close();
}
}
public static void CriptacaoString(Scanner sc) {
	sc.nextLine();
	try {
		System.out.print("Me conte seu segredo ");
	String texto = sc.nextLine();
    SecretKey chave = Conversao.GerandoChave();
    ChaveCodificar chaveFinal = new ChaveCodificar(chave);
    String chaveString = Conversao.ChaveParaString(chaveFinal.getChave());
    System.out.println(chaveString);
    IvParameterSpec ivParameterSpec = Conversao.GerandoIv();
    VetorInicializador vi = new VetorInicializador(ivParameterSpec);
    String ivString = Conversao.ByteParaString(vi.getIv().getIV());
    System.out.println(ivString);
    String algoritmo = "AES/GCM/NoPadding";
    GCMParameterSpec gcmParameterSpec = Conversao.GerandoGCM(vi.getIv().getIV());
    ChaveGCM gcm = new ChaveGCM(gcmParameterSpec);
    String textoCriptografado = PegandoArquivo.adquirindoString(chaveFinal, gcm.getGcmTag(), algoritmo, texto);
    System.out.println("Resultado : " + textoCriptografado);
}
catch(Exception e) {
sc.close();
}
}
public static void Decriptacao(Scanner sc) {
	String caminho = new String(AdquirindoArquivo.ListandoEAdquirindo(sc));
	sc.nextLine();
	try {
	Arquivo arquivoRecebido = new Arquivo(new File(caminho));
    SecretKey chave = Conversao.StringParaChave(sc.nextLine());
    ChaveCodificar chaveFinal = new ChaveCodificar(chave);
    String chaveString = Conversao.ChaveParaString(chaveFinal.getChave());
    System.out.println(chaveString);
    IvParameterSpec ivParameterSpec = new IvParameterSpec(Conversao.StringParaByte(sc.nextLine()));
    VetorInicializador vi = new VetorInicializador(ivParameterSpec);
    String ivString = Conversao.ByteParaString(vi.getIv().getIV());
    System.out.println(ivString);
    String algoritmo = "AES/GCM/NoPadding";
    GCMParameterSpec gcmParameterSpec = Conversao.GerandoGCM(vi.getIv().getIV());
    ChaveGCM gcm = new ChaveGCM(gcmParameterSpec);
    ArquivoCriptografado resultado = new ArquivoCriptografado(new File(caminho));
    PegandoArquivo.retornando(chaveFinal, gcm.getGcmTag(), algoritmo, arquivoRecebido, resultado);
    PegandoArquivo.imprimindo(resultado.getArquivo());
}
	catch(Exception e) {
		System.out.println(caminho);
		sc.close();
		}
}
public static void DecriptacaoString(Scanner sc) {
	sc.nextLine();
	try {
		System.out.print("Me conte seu segredo ");
	String texto = sc.nextLine();
    SecretKey chave = Conversao.StringParaChave(sc.nextLine());
    ChaveCodificar chaveFinal = new ChaveCodificar(chave);
    String chaveString = Conversao.ChaveParaString(chaveFinal.getChave());
    System.out.println(chaveString);
    IvParameterSpec ivParameterSpec = new IvParameterSpec(Conversao.StringParaByte(sc.nextLine()));
    VetorInicializador vi = new VetorInicializador(ivParameterSpec);
    String ivString = Conversao.ByteParaString(vi.getIv().getIV());
    System.out.println(ivString);
    String algoritmo = "AES/GCM/NoPadding";
    GCMParameterSpec gcmParameterSpec = Conversao.GerandoGCM(vi.getIv().getIV());
    ChaveGCM gcm = new ChaveGCM(gcmParameterSpec);
    String textoDecriptografado = PegandoArquivo.retornandoString(chaveFinal, gcm.getGcmTag(), algoritmo, texto);
    System.out.println("Resultado : " + textoDecriptografado);
}
catch(Exception e) {
sc.close();
}
}
}
