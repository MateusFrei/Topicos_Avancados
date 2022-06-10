import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import ij.gui.GenericDialog;

import ij.IJ;
import ij.ImagePlus;

public class Morfologia_matematica implements PlugIn {

    ImagePlus imagem = IJ.getImage();
    ImageProcessor processador_original = imagem.getProcessor();

    ImagePlus objeto_estruturante = IJ.createImage("resultado", "8-bit", imagem.getWidth(), imagem.getHeight(), 1);
    ImageProcessor processador_estruturante = objeto_estruturante.getProcessor();
    
    int[] elemento_estruturante = new int[9];
    
    public void run(String arg) {
        apresentarInterfaceGrafica();
        
    }


	
    public void apresentarInterfaceGrafica() {

        GenericDialog interfaceGrafica = new GenericDialog("Exemplo de uso do Generic Dialog");

        String[] estrategia = {"Abrir_imagem", "Erosão", "Dilatação", "Fechar_imagem", "Outline"};

        interfaceGrafica.addRadioButtonGroup("Botoes para escolher uma dentre varias estrategias", estrategia, 1, 5, "Abrir_imagem");
        interfaceGrafica.showDialog();
        
        
        if (interfaceGrafica.wasCanceled()) {
            IJ.showMessage("PlugIn cancelado!");
        } else {

            if (interfaceGrafica.wasOKed()) {

                String opcaoSelecionada = interfaceGrafica.getNextRadioButton();

                if (opcaoSelecionada == "Abrir_imagem") 
                {
                	
                	abertura();

                }

                if (opcaoSelecionada == "Erosão") 
                {
                	
                	erosao();
                }

                
				if (opcaoSelecionada == "Dilatação") 
                {		
					dilatacao();
                }
                if (opcaoSelecionada == "Fechar_imagem") 
                {
                	
                	fechamento();

                }

                if (opcaoSelecionada == "Outline") 
                {

                	outline();

                }

                IJ.showMessage("Plugin encerrado com sucesso!");              
                
            }
        }

    }
    public void dilatacao(){
    	for (int x = 0; x < processador_original.getWidth(); x++) {
    		for (int y = 0; y < processador_original.getHeight(); y++) {

    			elemento_estruturante[0] = processador_original.getPixel(x - 1, y + 1);
    			elemento_estruturante[1] = processador_original.getPixel(x + 1, y + 1);
    			elemento_estruturante[2] = processador_original.getPixel(x, y + 1);
    			elemento_estruturante[3] = processador_original.getPixel(x - 1, y - 1);
    			elemento_estruturante[4] = processador_original.getPixel(x + 1, y - 1);
    			elemento_estruturante[5] = processador_original.getPixel(x - 1, y);
    			elemento_estruturante[6] = processador_original.getPixel(x + 1, y);
    			elemento_estruturante[7] = processador_original.getPixel(x, y - 1);
    			elemento_estruturante[8] = processador_original.getPixel(x, y);

    			if (elemento_estruturante[8] == 0) {

    				processador_estruturante.putPixel(x-1,y+1, 0);
    				processador_estruturante.putPixel(x+1,y+1, 0);
    				processador_estruturante.putPixel(x, y+1, 0);
    				processador_estruturante.putPixel(x-1, y-1, 0);
    				processador_estruturante.putPixel(x+1,y-1, 0);
    				processador_estruturante.putPixel(x-1, y, 0);
    				processador_estruturante.putPixel(x+1, y, 0);
    				processador_estruturante.putPixel(x, y-1, 0);
    				processador_estruturante.putPixel(x, y, 0);

    			}

    		}
    	}
    	
    	imagem.setProcessor(processador_estruturante);

    }
    
    public void erosao(){
 
    	for (int x = 0; x < processador_original.getWidth(); x++) {
    		for (int y = 0; y < processador_original.getHeight(); y++) {

    			elemento_estruturante[0] = processador_original.getPixel(x - 1, y + 1);
    			elemento_estruturante[1] = processador_original.getPixel(x + 1, y + 1);
    			elemento_estruturante[2] = processador_original.getPixel(x, y + 1);
    			elemento_estruturante[3] = processador_original.getPixel(x - 1, y - 1);
    			elemento_estruturante[4] = processador_original.getPixel(x + 1, y - 1);
    			elemento_estruturante[5] = processador_original.getPixel(x - 1, y);
    			elemento_estruturante[6] = processador_original.getPixel(x + 1, y);
    			elemento_estruturante[7] = processador_original.getPixel(x, y - 1);
    			elemento_estruturante[8] = processador_original.getPixel(x, y);
    		
				if(area_interesse(elemento_estruturante) == 1) {
					processador_estruturante.putPixel(x, y, 0);
				}

    		}
    	}
    	
    	imagem.setProcessor(processador_estruturante);
    }
    
	public int area_interesse(int vetor[]) {
	
		for(int i = 0; i < vetor.length; i++) {
			if( vetor[i] != 0) {
				return 0;
			}
		}
		return 1;
	}
    public void fechamento(){
	
    	dilatacao();
    	erosao();

    }
    
    public void abertura(){
    	
		erosao();
		dilatacao();

    }
    
    public void outline(){
    	erosao();
    	int aux = 0;
    	
    	for (int x = 0; x < processador_original.getWidth(); x++) {
    		for (int y = 0; y < processador_original.getHeight(); y++) {
    			
    			aux = processador_estruturante.getPixel(x, y);
    			
    			if(aux == 0) {
    				processador_original.putPixel(x, y, 255);
    			}
			}
		}
    	imagem.setProcessor(processador_original);
    }
    
}