import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import ij.gui.GenericDialog;

import ij.IJ;
import ij.ImagePlus;

public class Grey_RGB implements PlugIn {

	
    public void run(String arg) {
        apresentarInterfaceGrafica();
    }

    
    public void apresentarInterfaceGrafica() {
        ImagePlus imagem_RGB = IJ.getImage();
        ImageProcessor processador_imagem_RGB = imagem_RGB.getProcessor();

        GenericDialog interfaceGrafica = new GenericDialog("Exemplo de uso do Generic Dialog");

        String[] estrategia = {"Média", "Luminosidade", "Dessaturação"};

        interfaceGrafica.addRadioButtonGroup("Botoes para escolher uma dentre varias estrategias", estrategia, 1, 3, "Média ponderada");
        interfaceGrafica.addCheckbox("Criar nova imagem", true);
        interfaceGrafica.showDialog();

        if (interfaceGrafica.wasCanceled())
        {
            IJ.showMessage("PlugIn cancelado!");
        } else {
            if (interfaceGrafica.wasOKed()) 
            {

                String opcaoSelecionada = interfaceGrafica.getNextRadioButton();
                boolean cria_imagem = interfaceGrafica.getNextBoolean();

                if (cria_imagem == true) 
                {
                    if (opcaoSelecionada == "Média") 
                    {

                        int media_RGB[] = new int[3];
                        int auxiliar = 0;

                        ImagePlus imagem_escalaCinza = IJ.createImage("Imagem_media_grey", "8-bit", imagem_RGB.getWidth(), imagem_RGB.getHeight(), 1);
                        ImageProcessor processador_grey = imagem_escalaCinza.getProcessor();

                        for (int x = 0; x < processador_imagem_RGB.getWidth(); x++) 
                        {
                            for (int y = 0; y < processador_imagem_RGB.getHeight(); y++) 
                            {

                                media_RGB[0] = processador_imagem_RGB.getPixel(x, y, null)[0];
                                media_RGB[1] = processador_imagem_RGB.getPixel(x, y, null)[1];
                                media_RGB[2] = processador_imagem_RGB.getPixel(x, y, null)[2];

                                auxiliar = (media_RGB[0] + media_RGB[1] + media_RGB[2]) / 3;

                                processador_grey.putPixel(x, y, auxiliar);


                            }
                        }
                        imagem_escalaCinza.show();
                        imagem_escalaCinza.updateAndDraw();
                    }

                    if (opcaoSelecionada == "Luminosidade") 
                    {

                        int media_RGB[] = new int[3];
                        int auxiliar = 0;

                        ImagePlus imagem_escalaCinza = IJ.createImage("Imagem_media_grey", "8-bit", imagem_RGB.getWidth(), imagem_RGB.getHeight(), 1);
                        ImageProcessor processador_grey = imagem_escalaCinza.getProcessor();

                        for (int x = 0; x < processador_imagem_RGB.getWidth(); x++) 
                        {
                            for (int y = 0; y < processador_imagem_RGB.getHeight(); y++) 
                            {

                                media_RGB[0] = processador_imagem_RGB.getPixel(x, y, null)[0];
                                media_RGB[1] = processador_imagem_RGB.getPixel(x, y, null)[1];
                                media_RGB[2] = processador_imagem_RGB.getPixel(x, y, null)[2];

                                auxiliar = (int)(0.2125 * media_RGB[0] + 0.7154 * media_RGB[1] + 0.072 * media_RGB[2]);

                                processador_grey.putPixel(x, y, auxiliar);


                            }
                        }
                        imagem_escalaCinza.show();
                        imagem_escalaCinza.updateAndDraw();
                    }

                    if (opcaoSelecionada == "Dessaturação") 
                    {

                        int media_RGB[] = new int[3];
                        int auxiliar = 0;

                        ImagePlus imagem_escalaCinza = IJ.createImage("Imagem_media_grey", "8-bit", imagem_RGB.getWidth(), imagem_RGB.getHeight(), 1);
                        ImageProcessor processador_grey = imagem_escalaCinza.getProcessor();

                        for (int x = 0; x < processador_imagem_RGB.getWidth(); x++) 
                        {
                            for (int y = 0; y < processador_imagem_RGB.getHeight(); y++) 
                            {

                                media_RGB[0] = processador_imagem_RGB.getPixel(x, y, null)[0];
                                media_RGB[1] = processador_imagem_RGB.getPixel(x, y, null)[1];
                                media_RGB[2] = processador_imagem_RGB.getPixel(x, y, null)[2];

                                auxiliar = (int)(0.299 * media_RGB[0] + 0.587 * media_RGB[1] + 0.114 * media_RGB[2]);

                                processador_grey.putPixel(x, y, auxiliar);


                            }
                        }
                        imagem_escalaCinza.show();
                        imagem_escalaCinza.updateAndDraw();
                    }

                } else {
                    if (opcaoSelecionada == "Média") 
                    {

                        int media_RGB[] = new int[3];
                        int valor_media = 0;

                        for (int x = 0; x < processador_imagem_RGB.getWidth(); x++) 
                        {
                            for (int y = 0; y < processador_imagem_RGB.getHeight(); y++) 
                            {

                                media_RGB[0] = processador_imagem_RGB.getPixel(x, y, null)[0];
                                media_RGB[1] = processador_imagem_RGB.getPixel(x, y, null)[1];
                                media_RGB[2] = processador_imagem_RGB.getPixel(x, y, null)[2];

                                valor_media = (media_RGB[0] + media_RGB[1] + media_RGB[2]) / 3;

                                media_RGB[0] = valor_media;
                                media_RGB[1] = valor_media;
                                media_RGB[2] = valor_media;

                                processador_imagem_RGB.putPixel(x, y, media_RGB);

                            }
                        }
                        imagem_RGB.updateAndDraw();

                    } else {

                        if (opcaoSelecionada == "Luminosidade") 
                        {

                            int media_RGB[] = new int[3];
                            int auxiliar = 0;

                            ImagePlus imagem_escalaCinza = IJ.createImage("Imagem_media_grey", "8-bit", imagem_RGB.getWidth(), imagem_RGB.getHeight(), 1);
                            ImageProcessor processador_grey = imagem_escalaCinza.getProcessor();

                            for (int x = 0; x < processador_imagem_RGB.getWidth(); x++) {
                                for (int y = 0; y < processador_imagem_RGB.getHeight(); y++) {

                                    media_RGB[0] = processador_imagem_RGB.getPixel(x, y, null)[0];
                                    media_RGB[1] = processador_imagem_RGB.getPixel(x, y, null)[1];
                                    media_RGB[2] = processador_imagem_RGB.getPixel(x, y, null)[2];

                                    auxiliar = (int)(0.2125 * media_RGB[0] + 0.7154 * media_RGB[1] + 0.072 * media_RGB[2]);

                                    processador_grey.putPixel(x, y, auxiliar);


                                }
                            }
                            imagem_escalaCinza.show();
                            imagem_escalaCinza.updateAndDraw();
                        } else {
                            if (opcaoSelecionada == "Dessaturação") 
                            {

                                int media_RGB[] = new int[3];
                                int auxiliar = 0;

                                ImagePlus imagem_escalaCinza = IJ.createImage("Imagem_media_grey", "8-bit", imagem_RGB.getWidth(), imagem_RGB.getHeight(), 1);
                                ImageProcessor processador_grey = imagem_escalaCinza.getProcessor();

                                for (int x = 0; x < processador_imagem_RGB.getWidth(); x++) 
                                {
                                    for (int y = 0; y < processador_imagem_RGB.getHeight(); y++) 
                                    {

                                        media_RGB[0] = processador_imagem_RGB.getPixel(x, y, null)[0];
                                        media_RGB[1] = processador_imagem_RGB.getPixel(x, y, null)[1];
                                        media_RGB[2] = processador_imagem_RGB.getPixel(x, y, null)[2];

                                        auxiliar = (int)(0.299 * media_RGB[0] + 0.587 * media_RGB[1] + 0.114 * media_RGB[2]);

                                        processador_grey.putPixel(x, y, auxiliar);


                                    }
                                }
                                imagem_escalaCinza.show();
                                imagem_escalaCinza.updateAndDraw();
                            }
                        }
                    }

                }
                IJ.showMessage("Plugin encerrado com sucesso!");
            }
        }

    }

}