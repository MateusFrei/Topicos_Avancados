import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;


public class Imagens_HSV implements PlugIn {

	public void run(String arg) {
		ImagePlus imagem = IJ.getImage();
		ImageProcessor processador = imagem.getProcessor();
		
		ImagePlus Imagem_matiz = IJ.createImage("matiz", "8-bit", processador.getWidth(), processador.getHeight(), 1);
		ImageProcessor processadorH = Imagem_matiz.getProcessor();
		
		ImagePlus Imagem_saturacao = IJ.createImage("saturation", "8-bit", processador.getWidth(), processador.getHeight(), 1);
		ImageProcessor processadorS = Imagem_saturacao.getProcessor();
		
		ImagePlus Imagem_brilho = IJ.createImage("brilho", "8-bit", processador.getWidth(), processador.getHeight(), 1);
		ImageProcessor processadorV = Imagem_brilho.getProcessor();

		

		for(int x = 0 ; x < processador.getWidth() ; x++){
			for(int y = 0 ; y < processador.getHeight() ; y++){
				processadorH.putPixel(x, y, matiz(processador.getPixel(x, y, null)));
				processadorS.putPixel(x, y, saturation(processador.getPixel(x, y, null)));
				processadorV.putPixel(x, y, brilho(processador.getPixel(x, y, null)));
			}
		}
		
		Imagem_matiz.show();
		Imagem_saturacao.show();
		Imagem_brilho.show();
		
	}
	
	public int matiz(int[] pixel) {
		
		double hue = 0;
		double pixelauxiliar[] = new double[3];
		double maior = 0, menor = 255;
		
		//							valorRGB ,menorv,maiorv,maxpixel,minpixel;
			pixelauxiliar[0] = normalization(pixel[0], 0, 255, 1.0, 0.0);
			pixelauxiliar[1] = normalization(pixel[1], 0, 255, 1.0, 0.0);
			pixelauxiliar[2] = normalization(pixel[2], 0, 255, 1.0, 0.0);
		
		for(int i = 0 ; i < 3 ; i++) {
			if( maior < pixelauxiliar[i]) {
				maior = pixelauxiliar[i];
			}
			if(menor > pixelauxiliar[i]) {
				menor = pixelauxiliar[i];
			}
		}
						
		if( maior == pixelauxiliar[0] && pixelauxiliar[1] >= pixelauxiliar[2]) {
			hue = 60 * ((pixelauxiliar[1] - pixelauxiliar[2])/(maior - menor)) + 0;
	
		} else if (maior == pixelauxiliar[0] && pixelauxiliar[1] < pixelauxiliar[2]) {
			hue = 60 * ((pixelauxiliar[1] - pixelauxiliar[2])/(maior - menor)) + 360;
		
		} else if (maior == pixelauxiliar[1]) {
			hue = 60 * ((pixelauxiliar[2] - pixelauxiliar[0])/(maior - menor)) + 120;
			
		} else if (maior == pixelauxiliar[2]) {
			hue = 60 * ((pixelauxiliar[0] - pixelauxiliar[1])/(maior - menor)) + 240;

		}
		
		
		int auxiliar = (int)  normalization(hue, 0, 360, 255, 0);
		return auxiliar;
	}
	
	
	public int saturation(int[] pixel){
		double maior = 0, menor = 255;
		double pixelauxiliar[] = new double[3];
		
		pixelauxiliar[0] = normalization(pixel[0], 0, 255, 1.0, 0.0);
		pixelauxiliar[1] = normalization(pixel[1], 0, 255, 1.0, 0.0);
		pixelauxiliar[2] = normalization(pixel[2], 0, 255, 1.0, 0.0);		
		
		for(int i = 0 ; i < 3 ; i++) {
			if( pixelauxiliar[i] > maior) {
				maior = pixelauxiliar[i];
			}
			if(pixelauxiliar[i] < menor) {
				menor = pixelauxiliar[i];
			}
		}
		
		double saturacao = (double) ((maior - menor)/maior); 
		saturacao = normalization(saturacao, 0.0, 1.0, 255, 0);
		return (int) saturacao;
	}
	
	public int brilho(int[] pixel) {
				
		int maior = 0;
		for(int i = 0 ; i < 3 ; i++) {
			if( pixel[i] > maior) {
				maior = pixel[i];
			}
		}
		return maior;
	}
	
	public double normalization(double pixel, double menorvalor, double maiorvalor, double maximopixel, double minpixel) {
		double ret = (pixel - menorvalor) * ( (maximopixel - minpixel)/(maiorvalor - menorvalor) ) + minpixel;
		return ret;	
	}
}
