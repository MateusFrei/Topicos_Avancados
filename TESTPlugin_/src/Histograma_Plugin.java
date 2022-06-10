import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import ij.gui.GenericDialog;

import ij.IJ;
import ij.ImagePlus;

public class Histograma_Plugin implements PlugIn {


    public void run(String arg) {
        apresentarInterfaceGrafica();
    }


    public void apresentarInterfaceGrafica() {


        int vetor[] = new int[3];
        int valor_media = 0;

        GenericDialog interfaceGrafica = new GenericDialog("Exemplo de uso do Generic Dialog");

        ImagePlus imagem = IJ.getImage();
        ImageProcessor processador_imagem = imagem.getProcessor();

        ImagePlus imagem_escalaCinza = IJ.createImage("Imagem_media_grey", "8-bit", imagem.getWidth(), imagem.getHeight(), 1);
        ImageProcessor processador_grey = imagem_escalaCinza.getProcessor();

        String[] estrategia = {"Equalização", "Expansão"};

        interfaceGrafica.addRadioButtonGroup("Defina o tipo de análise da Imagem: ", estrategia, 1, 2, "Equalização");

        interfaceGrafica.showDialog();

        if (interfaceGrafica.wasCanceled()) {
            IJ.showMessage("PlugIn cancelado!");
        } else {
            if (interfaceGrafica.wasOKed()) {

                String opcaoSelecionada = interfaceGrafica.getNextRadioButton();


                if (opcaoSelecionada == "Equalização") {

                	  double elementos = 0;
                	  double vetor_probabilidade[] = new double[255];
                	  double probabilidade_acomuldada[] = new double[255];

                	  int vetor_equalizacao[] = new int[255];
                	  int pixel[] = {0};
                	  int resultado = 0;

                	  for (int x = 0; x < processador_imagem.getWidth(); x++) {
                	      for (int y = 0; y < processador_imagem.getHeight(); y++) {

                	          vetor[0] = processador_imagem.getPixel(x, y, null)[0];
                	          vetor[1] = processador_imagem.getPixel(x, y, null)[1];
                	          vetor[2] = processador_imagem.getPixel(x, y, null)[2];

                	          valor_media = (vetor[0] + vetor[1] + vetor[2]) / 3;

                	          processador_grey.putPixel(x, y, valor_media);

                	      }
                	  }

                	  for (int x = 0; x < processador_imagem.getWidth(); x++) {
                	      for (int y = 0; y < processador_imagem.getHeight(); y++) {

                	          pixel = processador_grey.getPixel(x, y, pixel);
                	          vetor_equalizacao[pixel[0]]++;
                	          elementos++;

                	      }

                	  }

                	  for (int i = 0; i < vetor_equalizacao.length; i++) {

                	      vetor_probabilidade[i] = vetor_equalizacao[i] / elementos;

                	      if (i == 0) {

                	          probabilidade_acomuldada[i] = vetor_probabilidade[i];

                	      }if (i > 0) {
                	          probabilidade_acomuldada[i] = probabilidade_acomuldada[i - 1] + vetor_probabilidade[i];
                	      }

                	  }

                	  for (int x = 0; x < processador_imagem.getWidth(); x++) {
                	      for (int y = 0; y < processador_imagem.getHeight(); y++) {

                	          pixel = processador_grey.getPixel(x, y, pixel);

                	          resultado = (int)(probabilidade_acomuldada[pixel[0]] * 255);
                	          processador_grey.putPixel(x, y, resultado);

                	      }

                	  }
                	  imagem_escalaCinza.updateAndDraw();
                	  imagem_escalaCinza.show();
                	  IJ.run("Histogram");

                	}

                if (opcaoSelecionada == "Expansão") {

                    int pixel = 0;
                    int menor_pixel = 0;
                    int maior_pixel = 255;
                    int low_pixel = 255;
                    int high_pixel = 0;

                    for (int x = 0; x < processador_imagem.getWidth(); x++) {
                        for (int y = 0; y < processador_imagem.getHeight(); y++) {

                            vetor[0] = processador_imagem.getPixel(x, y, null)[0];
                            vetor[1] = processador_imagem.getPixel(x, y, null)[1];
                            vetor[2] = processador_imagem.getPixel(x, y, null)[2];

                            valor_media = (vetor[0] + vetor[1] + vetor[2]) / 3;

                            processador_grey.putPixel(x, y, valor_media);

                        }
                    }

                    for (int x = 0; x < processador_grey.getWidth(); x++) {
                        for (int y = 0; y < processador_grey.getHeight(); y++) {

                            pixel = processador_grey.getPixel(x, y);

                            if (low_pixel > pixel) {

                                low_pixel = pixel;
                            }
                            if (high_pixel < pixel) {

                                high_pixel = pixel;

                            }

                        }
                    }

                    for (int x = 0; x < processador_grey.getWidth(); x++) {
                        for (int y = 0; y < processador_grey.getHeight(); y++) {

                            pixel = processador_grey.getPixel(x, y);

                            pixel = menor_pixel + (pixel - low_pixel) * (maior_pixel - menor_pixel) / (high_pixel - low_pixel);

                            processador_grey.putPixel(x, y, pixel);
                        }
                    }
                    imagem_escalaCinza.updateAndDraw();
                    imagem_escalaCinza.show();
                    IJ.run("Histogram");
                }

                IJ.showMessage("Plugin encerrado com sucesso!");
            }
        }

    }

}