import ij.plugin.ImageCalculator;
import ij.plugin.PlugIn;
import ij.gui.GenericDialog;

import ij.IJ;
import ij.ImagePlus;

public class Operacoes_matematicas implements PlugIn {

	
    public void run(String arg) {
        apresentarInterfaceGrafica();
    }
    
	
	String vetor[] = {"Add", "Subtract", "Multiply", "Divide", "And", "Or", "Xor", "Min", "Max", "Difference", "Copy", "Transparent-zero", "Average"};

    public void operacoes(ImagePlus imagem1, ImagePlus imagem2) {

    	ImagePlus processor;
		for(int i = 0; i < vetor.length; i++){
			
			processor = ImageCalculator.run(imagem1, imagem2, vetor[i] + "create");
			processor.setTitle(imagem1.getTitle() + " " + vetor[i] + " " + imagem2.getTitle());
			IJ.saveAs(processor, "JPG", "C:/Users/mateus/Documents/GitHub/ProgramacaoOO/Imagens/" + processor.getTitle());
			processor.show();
			
		}	
    }
    	

    
    public void apresentarInterfaceGrafica() {
    	
    	ImagePlus imagem1 = IJ.openImage("C:/Users/mateus/Documents/GitHub/ProgramacaoOO/Imagens/Imagem1.jpg");
    	
    	ImagePlus imagem2 = IJ.openImage("C:/Users/mateus/Documents/GitHub/ProgramacaoOO/Imagens/Imagem2.jpg");
    	
    	ImagePlus imagem3 = IJ.openImage("C:/Users/mateus/Documents/GitHub/ProgramacaoOO/Imagens/Imagem3.jpg");
    	
    	ImagePlus imagem4 = IJ.openImage("C:/Users/mateus/Documents/GitHub/ProgramacaoOO/Imagens/Imagem4.jpg");
    	
    	ImagePlus imagem5 = IJ.openImage("C:/Users/mateus/Documents/GitHub/ProgramacaoOO/Imagens/Imagem5.jpg");
    	
    	ImagePlus imagem6 = IJ.openImage("C:/Users/mateus/Documents/GitHub/ProgramacaoOO/Imagens/Imagem6.jpg");
    	

        GenericDialog interfaceGrafica = new GenericDialog("Exemplo de uso do Generic Dialog");

        String[] estrategia = {"Imagem 1 e 2 ", "Imagem 3 e 4 ", "Imagem 5 e 6 "};

        interfaceGrafica.addRadioButtonGroup("Botoes para escolher uma dentre varias estrategias", estrategia, 1, 3, "Média ponderada");
        
        interfaceGrafica.showDialog();
       
        

        if (interfaceGrafica.wasCanceled()) {
        	
        	imagem1.hide();
        	
        	imagem2.hide();
        	
        	imagem3.hide();
        	
        	imagem4.hide();
        	
        	imagem5.hide();
        	
        	imagem6.hide();
        	
            IJ.showMessage("PlugIn cancelado!");
            
        } else {
            if (interfaceGrafica.wasOKed()) 
            {
            	String opcaoSelecionada = interfaceGrafica.getNextRadioButton();
            	if(opcaoSelecionada == "Imagem 1 e 2 ") {
            		
                	imagem1.show();
                	
                	imagem2.show();
                	
                	operacoes(imagem1, imagem2);
            		
            	}else if(opcaoSelecionada == "Imagem 3 e 4 ") {
            		
                	imagem3.show();
                	
                	imagem4.show();
                	
                	operacoes(imagem3, imagem4);
            		
            	}else if(opcaoSelecionada == "Imagem 5 e 6 ") {
            		
                	imagem5.show();
                	
                	imagem6.show();
                	
                	operacoes(imagem5, imagem6);
            		
            	}
            	
               IJ.showMessage("Plugin encerrado com sucesso!");
            }
        }
        

    }

}