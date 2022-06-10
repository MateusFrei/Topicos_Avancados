import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import ij.gui.DialogListener;
import ij.gui.GenericDialog;
import java.awt.AWTEvent;
import ij.IJ;
import ij.ImagePlus;

public class Plugin_PontoaPonto implements PlugIn, DialogListener {
	public void run(String arg) {
		apresentarInterfaceGrafica();
	}
	
	ImagePlus imagem = IJ.getImage();	
	
	ImageProcessor processador_Original = imagem.getProcessor();
	ImageProcessor processador_Alterado = imagem.getProcessor().duplicate();	

	int vetor[] = new int[3];

public void copia() {
	
	for(int x = 0; x < processador_Original.getWidth(); x++) {
		for(int y = 0; y < processador_Original.getHeight(); y++) {
			
    		vetor[0] = processador_Original.getPixel(x, y, null) [0];
    		vetor[1] = processador_Original.getPixel(x, y, null) [1];
    		vetor[2] = processador_Original.getPixel(x, y, null) [2];
			
			processador_Alterado.putPixel(x, y, vetor);
			
		}
	}

}
    
	
	public void apresentarInterfaceGrafica() {
		copia();
		GenericDialog interfaceGrafica = new GenericDialog("Exemplo de uso do Generic Dialog");
		interfaceGrafica.addDialogListener(this);
		int vetor[] = new int[3];
		
		interfaceGrafica.addSlider("brilho", -255, 255, 0, 1);
		interfaceGrafica.addSlider("contraste", -255, 255, 0, 1);
		interfaceGrafica.addSlider("dessaturação", 0, 1, 1, 0.1);
		interfaceGrafica.addSlider("solarização", 0, 255, 255, 1);
		
		interfaceGrafica.showDialog();
		
		if (interfaceGrafica.wasCanceled()) {
	        for(int x = 0; x < processador_Original.getWidth(); x++) {
	        	for(int y = 0; y < processador_Original.getHeight(); y++) {
	        		
	        		vetor[0] = processador_Alterado.getPixel(x, y, null) [0];
	        		vetor[1] = processador_Alterado.getPixel(x, y, null) [1];
	        		vetor[2] = processador_Alterado.getPixel(x, y, null) [2];
	        		
	        		processador_Original.putPixel(x, y, vetor);
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
		
		int brilho =  (int)interfaceGrafica.getNextNumber();
		double fator = 0;
		double contraste =  interfaceGrafica.getNextNumber();
		double dessaturação =  interfaceGrafica.getNextNumber();
		int solarização	 =  (int)interfaceGrafica.getNextNumber();
		int vetor[] = new int[3];
		

        for(int x = 0; x < processador_Original.getWidth(); x++) {
        	for(int y = 0; y < processador_Original.getHeight(); y++) {
        		//brilho
        		
        		vetor[0] = brilho + processador_Alterado.getPixel(x, y, null) [0];   
        		
        		if(vetor[0] > 255) {
        			vetor[0] = 255;
        		}else if(vetor[0] < 0){
        			vetor[0] = 0;
        		}
        		
        		vetor[1] = brilho + processador_Alterado.getPixel(x, y, null) [1];
        		if(vetor[1] > 255) {		
        			vetor[1] = 255;
        		}else if(vetor[1] < 0){	
        			vetor[1] = 0;
        		}
        		
        		vetor[2] = brilho + processador_Alterado.getPixel(x, y, null) [2];
        		if(vetor[2] > 255) {		
        			vetor[2] = 255;
        		}else if(vetor[2] < 0){
        			vetor[2] = 0;
        		}
        		
        		//Contraste
        		fator = (259 * (contraste + 255)) / (255 * (259 - contraste));
        		
        		vetor[0] = (int)(fator * (vetor[0] - 128) + 128); 
        		
        		if(vetor[0] > 255) {
        			
        			vetor[0] = 255;
        			
        		}else if(vetor[0] < 0){
        			
        			vetor[0] = 0;
        			
        		}
        		
        		vetor[1] = (int)(fator * (vetor[1] - 128) + 128);
        		
        		if(vetor[1] > 255) {	
        			
        			vetor[1] = 255;
        			
        		}else if(vetor[1] < 0){	
        			
        			vetor[1] = 0;
        			
        		}
      		
        		vetor[2] = (int)(fator * (vetor[2] - 128) + 128);
        		
        		if(vetor[2] > 255) {	
        			
        			vetor[2] = 255;
        		}else if(vetor[2] < 0){
        			vetor[2] = 0;
        		}
        		
        		//solarização
        		
        		if(vetor[0] > solarização) {
        			
        			vetor[0] = 255 - vetor[0]; 
        			
        		}else if(vetor[0] > 255){
        			vetor[0] = 255;
        		}else if(vetor[0] < 0){
        			vetor[0] = 0;
        		}
        		
         		if(vetor[1] > solarização) {	
         			
         			vetor[1] = 255 - vetor[1]; 
         			
        		}else if(vetor[1] > 255){
        			vetor[1] = 255;
        		}else if(vetor[1] < 0){
        			vetor[1] = 0;
        		}
        		
        		if(vetor[2] > solarização) {	
        			
        			vetor[2] = 255 - vetor[2];
        			
        		}else if(vetor[2] > 255){
        			vetor[2] = 255;
        		}else if(vetor[2] < 0){
        			vetor[2] = 0;
        		}
        		
        		//dessaturação

                double peso = 0;
                
                peso = (double)(0.299 * vetor[0] + 0.587 * vetor[1] + 0.114 * vetor[2]);
                
                vetor[0] = (int)(peso + dessaturação * (vetor[0] - peso));
			
                vetor[1] = (int)(peso + dessaturação * (vetor[1] - peso));

                vetor[2] = (int)(peso + dessaturação * (vetor[2] - peso));
				
        		
        		processador_Original.putPixel(x, y, vetor);
        		
        	}
        }
		
        imagem.updateAndDraw();
        
        return true;

		
    }
}