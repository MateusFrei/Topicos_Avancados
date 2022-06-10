import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import ij.gui.DialogListener;
import ij.gui.GenericDialog;
import java.awt.AWTEvent;
import ij.IJ;
import ij.ImagePlus;

public class Limiar_Binary implements PlugIn, DialogListener {
	public void run(String arg) {
		apresentarInterfaceGrafica();
	}
	
	ImagePlus imagem = IJ.getImage();
	ImageProcessor processador_Original = imagem.getProcessor();
	ImageProcessor processador_Alterado = imagem.getProcessor().duplicate();	
   
	
	public void apresentarInterfaceGrafica() {

		GenericDialog interfaceGrafica = new GenericDialog("Exemplo de uso do Generic Dialog");
		interfaceGrafica.addDialogListener(this);
		
		interfaceGrafica.addSlider("Binarização: ", 0, 255, 128, 1);
		
		interfaceGrafica.showDialog();
		
		if (interfaceGrafica.wasCanceled()) {
	        for(int x = 0; x < processador_Original.getWidth(); x++) {
	        	for(int y = 0; y < processador_Original.getHeight(); y++) {
	        		
	        		processador_Original.putPixel(x, y, processador_Alterado.getPixel(x, y));
	        	}
	        }
	        imagem.updateAndDraw();
    		
			IJ.showMessage("PlugIn cancelado!");
		}
		else {
			if (interfaceGrafica.wasOKed()) {
				
		        IJ.showMessage("Plugin encerrado com sucesso!");
			}
		}
	}

	@Override
	public boolean dialogItemChanged(GenericDialog interfaceGrafica, AWTEvent e) {
		if (interfaceGrafica.wasCanceled()) return false;	

		int Binarização	 =  (int)interfaceGrafica.getNextNumber();

        for(int x = 0; x < processador_Alterado.getWidth(); x++) {
        	for(int y = 0; y < processador_Alterado.getHeight(); y++) {
        		
        		int pixel = processador_Alterado.getPixel(x, y);
        		
        		if(pixel > Binarização) {
        			processador_Original.putPixel(x, y, 255);
        		}else {
        			processador_Original.putPixel(x, y, 0);
        		}
        		
        	}
        }
        
        imagem.updateAndDraw();      
        return true;

		
    }
}