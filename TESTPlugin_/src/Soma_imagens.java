import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import ij.gui.GenericDialog;
import ij.IJ;
import ij.ImagePlus;

public class Soma_imagens implements PlugIn {

    public void run(String arg) {
        apresentarInterfaceGrafica();
    }


    public void apresentarInterfaceGrafica() {

        GenericDialog interfaceGrafica = new GenericDialog("Exemplo de uso do Generic Dialog");

        String[] estrategia = {"Truncamento","Wrapping","Normalização"};

        interfaceGrafica.addRadioButtonGroup("Botoes para escolher uma dentre varias estrategias", estrategia, 1, 3, "Média ponderada");
        interfaceGrafica.showDialog();

        ImagePlus imagem3 = IJ.openImage("C:/Users/mateus/Documents/GitHub/ProgramacaoOO/Imagens/Imagem3.jpg");
        ImageProcessor imagem3Processor = imagem3.getProcessor();

        ImagePlus imagem4 = IJ.openImage("C:/Users/mateus/Documents/GitHub/ProgramacaoOO/Imagens/Imagem4.jpg");
        ImageProcessor imagem4Processor = imagem4.getProcessor();

        ImagePlus resultante = IJ.createImage("resultado", "8-bit", imagem3.getWidth(), imagem3.getHeight(), 1);
        ImageProcessor resultanteProcessor = resultante.getProcessor();

        int pixel1 = 0, pixel2 = 0, soma = 0, low_pixel = 255, high_pixel = 0, result = 0;

        if (interfaceGrafica.wasCanceled()) {

            IJ.showMessage("PlugIn cancelado!");

        } else {
            if (interfaceGrafica.wasOKed()) {

                String opcaoSelecionada = interfaceGrafica.getNextRadioButton();

                if (opcaoSelecionada == "Truncamento") {

                    for (int x = 0; x < imagem3Processor.getWidth(); x++) {
                        for (int y = 0; y < imagem3Processor.getHeight(); y++) {
                            pixel1 = imagem3Processor.getPixel(x, y);
                            pixel2 = imagem4Processor.getPixel(x, y);

                            soma = pixel1 + pixel2;

                            resultanteProcessor.putPixel(x, y, soma);

                            if (resultanteProcessor.getPixel(x, y) > 255) {
                                resultanteProcessor.putPixel(x, y, 255);
                            } else if (resultanteProcessor.getPixel(x, y) < 0) {
                                resultanteProcessor.putPixel(x, y, 0);
                            }

                        }
                    }
                    resultante.updateAndDraw();
                    resultante.show();


                } else if (opcaoSelecionada == "Wrapping") {

                    for (int x = 0; x < imagem3Processor.getWidth(); x++) {
                        for (int y = 0; y < imagem3Processor.getHeight(); y++) {
                        	
                            pixel1 = imagem3Processor.getPixel(x, y);
                            pixel2 = imagem4Processor.getPixel(x, y);

                            soma = pixel1 + pixel2;
                            
                            if(soma > 255) {
                                soma -= 255;
                            }
                            resultanteProcessor.putPixel(x, y, soma);
                        }
                    }
                    resultante.updateAndDraw();
                    resultante.show();


                } else if (opcaoSelecionada == "Normalização") {

                    for (int x = 0; x < imagem3Processor.getWidth(); x++) {
                        for (int y = 0; y < imagem3Processor.getHeight(); y++) {
                            pixel1 = imagem3Processor.getPixel(x, y);
                            pixel2 = imagem4Processor.getPixel(x, y);

                            if (low_pixel > pixel1) {

                                low_pixel = pixel1;

                            } else if (low_pixel > pixel2) {

                                low_pixel = pixel2;
                            }

                            if (high_pixel < pixel1) {

                                high_pixel = pixel1;

                            } else if (high_pixel < pixel2) {

                                high_pixel = pixel2;

                            }
                        }
                    }

                    for (int x = 0; x < imagem3Processor.getWidth(); x++) {
                        for (int y = 0; y < imagem3Processor.getHeight(); y++) {
                            pixel1 = imagem3Processor.getPixel(x, y);
                            pixel2 = imagem4Processor.getPixel(x, y);
                            
                            result = pixel1 + pixel2;

                            soma = 255 / (high_pixel - low_pixel) * (result - low_pixel);
                            resultanteProcessor.putPixel(x, y, soma);                           

                        }
                    }

                    resultante.updateAndDraw();
                    resultante.show();

                }
            }

            IJ.showMessage("Plugin encerrado com sucesso!");
        }
    }
}