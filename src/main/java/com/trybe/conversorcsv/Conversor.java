package com.trybe.conversorcsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * Classe responavel por converter um CSV a um padrão.
 */
public class Conversor {

  /**
   * Função utilizada apenas para validação da solução do desafio.
   *
   * @param args Não utilizado.
   * @throws IOException Caso ocorra algum problema ao ler os arquivos de entrada ou
   *                     gravar os arquivos de saída.
   */
  public static void main(String[] args) throws IOException {
    File pastaDeEntradas = new File("./entradas/");
    File pastaDeSaidas = new File("./saidas/");
    new Conversor().converterPasta(pastaDeEntradas, pastaDeSaidas);
  }

  /**
   * Converte todos os arquivos CSV da pasta de entradas. Os resultados são gerados
   * na pasta de saídas, deixando os arquivos originais inalterados.
   *
   * @param pastaDeEntradas Pasta contendo os arquivos CSV gerados pela página ‘web’.
   * @param pastaDeSaidas   Pasta em que serão colocados os arquivos gerados no formato
   *                        requerido pelo subsistema.
   * @throws IOException Caso ocorra algum problema ao ler os arquivos de entrada ou
   *                     gravar os arquivos de saída.
   */
  public void converterPasta(File pastaDeEntradas, File pastaDeSaidas) throws IOException {

    if (pastaDeEntradas.isDirectory() && pastaDeEntradas.canRead()) {
      // se a pasta de saida não existir, cria-se
      if (!pastaDeSaidas.exists()) {
        pastaDeSaidas.mkdir();
      }

      // lista os arquivos no diretorio de entrada e começa o procedimento de formatação.
      for (File f : Objects.requireNonNull(pastaDeEntradas.listFiles())) {
        File file = new File(f.getPath());
        String caminhoSaida = pastaDeSaidas.getPath() + File.separator + file.getName();
        leArquivo(file, caminhoSaida);
      }
    }

  }

  /**
   * Metodo responsavel por lê cada arquivo e cada linha, para formatar
   * e salvar no diretorio de saida.
   *
   * @param file Atual arquivo trabalhado.
   * @param caminhoSaida Caminho de saida do arquivo atual.
   * @throws IOException Tratamento de Erro.
   */
  private void leArquivo(File file, String caminhoSaida) throws IOException {
    FileReader fileReader = null;
    BufferedReader bufferedReader = null;

    if (file.isFile()) {

      try (FileWriter fileWriter = new FileWriter(caminhoSaida)) {
        fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);

        // Pega o Header do CSV
        String rowContent = bufferedReader.readLine();
        fileWriter.write(rowContent + "\n");

        // Pega cada linha com conteudo, formata e salva no arquivo.
        while ((rowContent = bufferedReader.readLine()) != null) {
          String[] rowSplited = rowContent.split(",");
          String formatedRow = formataTexto(rowSplited);
          salvaArquivo(fileWriter, formatedRow);
        }

      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        fecharObjetos(fileReader, bufferedReader);
      }

    }

  }

  /**
   * Formata a linha passada pelo parametro.
   *
   * @param rowContent linha com o conteudo para ser formatado.
   * @return retorna uma nova linha ja formatada.
   */
  private String formataTexto(String[] rowContent) {
    String name = rowContent[0];
    String dateOfBirth = rowContent[1];
    String email = rowContent[2];
    String cpf = rowContent[3];

    // name
    String formatedName = name.toUpperCase();
    // date
    String[] formatedDate = dateOfBirth.split("/");
    String formatedDateFinal =
        formatedDate[2]
            + "-" + formatedDate[1]
            + "-" + formatedDate[0];
    // cpf
    String formatedCpf =
        cpf.substring(0, 3)
            + "." + cpf.substring(3, 6)
            + "." + cpf.substring(6, 9)
            + "-" + cpf.substring(9);

    String[] formatedRow = new String[]{formatedName, formatedDateFinal, email, formatedCpf};

    return String.join(",", formatedRow);
  }

  /**
   * Metodo responsavel por salva cada linha no arquivo.
   *
   * @param fileWriter O arquivo para ser salvo as linhas.
   * @param row        A linha que será salva no arquivo.
   * @throws IOException Tratamento de Erro.
   */
  private void salvaArquivo(FileWriter fileWriter, String row) throws IOException {
    try {
      fileWriter.write(row + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Metodo responsavel por fechar o Arquivo e seu Buffer.
   *
   * @param fileReader     O arquivo para ser fechado.
   * @param bufferedReader O buffer para ser fechado.
   * @throws IOException Tratamento de Erro.
   */
  private void fecharObjetos(
      FileReader fileReader, BufferedReader bufferedReader
  ) throws IOException {
    try {
      fileReader.close();
      bufferedReader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}