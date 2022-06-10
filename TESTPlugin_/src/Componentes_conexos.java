import java.util.LinkedList;
import java.util.Queue;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
 
public class Componentes_conexos  implements PlugIn {
	ImagePlus imagem_Original = IJ.getImage();
	ImageProcessor processador_Original= imagem_Original.getProcessor();	
	
	
	ImagePlus imagem_rotulada = IJ.createImage("Imagem Resultante", "8-bit", imagem_Original.getWidth(), imagem_Original.getHeight(), 1);
	ImageProcessor processador_rotulado = imagem_rotulada.getProcessor();
	
	int label = 0;
	
	
	Queue<Integer> filaWidth = new LinkedList<>();
	Queue<Integer> filaHeigth = new LinkedList<>();
	
	int posicaoX = 0, posicaoY = 0;


	public void run(String arg) {
		
		for (int x = 0; x < processador_rotulado.getWidth(); x++) {
			for (int y = 0; y < processador_rotulado.getHeight(); y++) {	
				
				processador_rotulado.putPixel(x, y, 0);
				
			}
		}
		
		for (int x = 0; x < processador_Original.getWidth(); x++) {
			for (int y = 0; y < processador_Original.getHeight(); y++) {	
				
				int black_pixel = processador_rotulado.getPixel(x, y);
				int white_pixel = processador_Original.getPixel(x, y);
				
				if(black_pixel == 0 && white_pixel == 255) {
					
					label += 30;

					verifica_pixel(x, y);

					while(filaHeigth.size() != 0) 
					{
					
						posicaoY = filaHeigth.remove();						
						posicaoX = filaWidth.remove();
						
						processador_rotulado.putPixel(posicaoX, posicaoY, label);
						
					}
				}

			}
		}
		imagem_rotulada.updateAndDraw();
		imagem_rotulada.show();
	}	
	
	public void verifica_pixel(int x, int y) {
		
		filaWidth.add(x);
		filaHeigth.add(y);

		processador_rotulado.putPixel(x, y, 155);
		
		
		if(processador_rotulado.getPixel(x+1, y) == 0 && (processador_rotulado.getPixel(x+1, y) != 155)){
			verifica_pixel(x+1, y);
		}
		
		if(processador_rotulado.getPixel(x, y+1) == 0 && (processador_rotulado.getPixel(x, y+1)!= 155)){
			verifica_pixel(x, y+1);
		}
		
		if(processador_rotulado.getPixel(x, y-1) == 0 && (processador_rotulado.getPixel(x, y-1)!= 155)){
			verifica_pixel(x, y-1);
		}
		
		if(processador_rotulado.getPixel(x-1, y) == 0 && (processador_rotulado.getPixel(x-1, y)!= 155)){
			verifica_pixel(x-1, y);
		}
		
	}

}
