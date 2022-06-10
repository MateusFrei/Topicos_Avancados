import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import ij.gui.GenericDialog;

import ij.IJ;
import ij.ImagePlus;

public class Filtro_Linear implements PlugIn {


	public void run(String arg) {
		apresentarInterfaceGrafica();
	}


	public void apresentarInterfaceGrafica() {
		ImagePlus imagem_RGB = IJ.getImage();
		ImageProcessor processador_RGB = imagem_RGB.getProcessor();	

		ImagePlus imagem_Linear = IJ.createImage("Imagem Resultante", "8-bit", processador_RGB.getWidth(), processador_RGB.getHeight(), 1);
		ImageProcessor processador_Linear = imagem_Linear.getProcessor();

		GenericDialog interfaceGrafica = new GenericDialog("Exemplo de uso do Generic Dialog");

		String[] estrategia = {"filtro passa-baixa", "filtros passa-alta", " filtros de borda"};

		interfaceGrafica.addRadioButtonGroup("Botoes para escolher uma dentre varias estrategias", estrategia, 1, 3, "Média ponderada");

		interfaceGrafica.showDialog();

		if (interfaceGrafica.wasCanceled())
		{
			IJ.showMessage("PlugIn cancelado!");
		} else {
			if (interfaceGrafica.wasOKed()) 
			{

				String opcaoSelecionada = interfaceGrafica.getNextRadioButton();

				if (opcaoSelecionada == "filtro passa-baixa") 
				{
					int[] vetor = new int[9];
					int auxiliar;

					for (int x = 1; x < processador_RGB.getWidth() - 1; x++) {
						for (int y = 1; y < processador_RGB.getHeight() - 1; y++) {
							auxiliar = 0;

//							for(int k = x-1; k <= x+1; k++) {
//								for(int l = y-1; l <= y+1; l++) {
//									auxiliar += processador_RGB.getPixel(k, l);
//								}
//							}
							vetor[0] = processador_RGB.getPixel(x-1,y+1);
							vetor[1] = processador_RGB.getPixel(x+1,y+1);
							vetor[2] = processador_RGB.getPixel(x, y+1);
							vetor[3] = processador_RGB.getPixel(x-1, y-1);
							vetor[4] = processador_RGB.getPixel(x+1,y-1);
							vetor[5] = processador_RGB.getPixel(x-1, y);
							vetor[6] = processador_RGB.getPixel(x+1, y);
							vetor[7] = processador_RGB.getPixel(x, y-1);
							vetor[8] = processador_RGB.getPixel(x, y);
							
							processador_Linear.putPixel(x, y, (auxiliar / 9));

						}
					}
					imagem_Linear.show();

				}

				if (opcaoSelecionada == "filtros passa-alta") 
				{
					int auxiliar = 0;
					int[] vetor = new int[9];


					for (int x = 0; x < processador_RGB.getWidth(); x++) {
						for (int y = 0; y < processador_RGB.getHeight(); y++) {
							auxiliar = 0;

							vetor[0] = processador_RGB.getPixel(x-1,y+1);
							vetor[1] = processador_RGB.getPixel(x+1,y+1);
							vetor[2] = processador_RGB.getPixel(x, y+1);
							vetor[3] = processador_RGB.getPixel(x-1, y-1);
							vetor[4] = processador_RGB.getPixel(x+1,y-1);
							vetor[5] = processador_RGB.getPixel(x-1, y);
							vetor[6] = processador_RGB.getPixel(x+1, y);
							vetor[7] = processador_RGB.getPixel(x, y-1);
							vetor[8] = processador_RGB.getPixel(x, y);

							for (int i = 0; i < 9; i++) {
								if (i >= 0 && i < 8) {
									auxiliar = auxiliar + (vetor[i] * -1);
								} else {
									auxiliar = auxiliar + (vetor[i] * 9);
								}
							}

							processador_Linear.putPixel(x, y, auxiliar);

						}
					}
					imagem_Linear.show();
				}

				if (opcaoSelecionada == " filtros de borda") 
				{
					int auxiliar;
					int[] vetor = new int[9];

					for (int x = 0; x< processador_RGB.getWidth(); x++) {
						for (int y = 0; y < processador_RGB.getHeight(); y++) {
							auxiliar = 0;
							vetor[0] = processador_RGB.getPixel(x-1,y+1);
							vetor[1] = processador_RGB.getPixel(x+1,y+1);
							vetor[2] = processador_RGB.getPixel(x, y+1);
							vetor[3] = processador_RGB.getPixel(x-1, y-1);
							vetor[4] = processador_RGB.getPixel(x+1,y-1);
							vetor[5] = processador_RGB.getPixel(x-1, y);
							vetor[6] = processador_RGB.getPixel(x+1, y);
							vetor[7] = processador_RGB.getPixel(x, y-1);
							vetor[8] = processador_RGB.getPixel(x, y);

							for (int i = 0; i < 9; i++) {
								if (i < 3) {
									auxiliar = auxiliar + (vetor[i] * -1);
								} else if (i >= 3 && i < 8) {
									auxiliar = auxiliar + (vetor[i] * 1);
								} else {
									auxiliar = auxiliar + (vetor[i] * -2);
								}
							}

							processador_Linear.putPixel(x, y, auxiliar);
						}
					}

					imagem_Linear.show();

				}



				IJ.showMessage("Plugin encerrado com sucesso!");
			}
		}

	}

}