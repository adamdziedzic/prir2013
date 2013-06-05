package genethicprir;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;

public class MainPanel extends javax.swing.JPanel {

    /**
     * Creates new form MainPanel
     */
    public MainPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        populationLabel = new javax.swing.JLabel();
        population = new javax.swing.JTextField();
        idividualCheckBox = new javax.swing.JCheckBox();
        individual = new javax.swing.JTextField();
        runButton = new javax.swing.JButton();
        //TODO JTextField which returns serverAddr as String

        populationLabel.setText("liczba populacji potomnych: ");

        idividualCheckBox.setText("określ liczbę osobników: ");
        idividualCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idividualCheckBoxActionPerformed(evt);
            }
        });

        individual.setEditable(false);

        runButton.setText("uruchom");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(populationLabel)
                            .addComponent(runButton))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(idividualCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(individual, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                            .addComponent(population))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(populationLabel)
                    .addComponent(population, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idividualCheckBox)
                    .addComponent(individual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(runButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void idividualCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idividualCheckBoxActionPerformed
        if(idividualCheckBox.isSelected())
            individual.setEditable(true);
        else
            individual.setEditable(false);
    }//GEN-LAST:event_idividualCheckBoxActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        int numberOfPopulation = 1;
        int numberOfIndividuals = 100;
        boolean isOk = false;
        
        //read number of populations
        try {
            numberOfPopulation = Integer.parseInt(population.getText());
            isOk = true;
        } catch(NumberFormatException e) {
            System.out.println("ERR: błędny format danych");
        }
        
        //read number of individuals
        if(individual.isEditable() == true) {
            try {
                numberOfIndividuals = Integer.parseInt(individual.getText());
                if(numberOfIndividuals < 100)
                    numberOfIndividuals = 100;
                isOk = true;
            } catch(NumberFormatException e) {
                System.out.println("ERR: błędny format danych");
                isOk = false;
            }
        }
                
        //start
        if(isOk) {
            
            //make window to visualizate
            JFrame frame = new JFrame("Wizualizacja");
            Visualisation visualisation = new Visualisation(0, 0, 0, 0);
            
//            Population mainPopulation = new Population(numberOfIndividuals);
//            Controller controller = new Controller();
            PopulationServerInterface myRemoteObject;
            Registry reg;
            reg = LocateRegistry.getRegistry(serverAddr);
            myRemoteObject = (PopulationServerInterface) reg.lookup("PopulationServer");
            
            //all phases of genetic algorithm repeted in loop
            for(int i = 0; i < numberOfPopulation; i++) {
//                mainPopulation = controller.selection(mainPopulation);
//                mainPopulation = controller.crossingOver(mainPopulation);
//                controller.mutation(mainPopulation);
//                System.out.println("Funkcja przystosowania:"+mainPopulation.getBestIndividual().getAdaptationValue());
//                System.out.println("Liczba miejsc siedzących: "+mainPopulation.getBestIndividual().getSitPlacesNum());
//                System.out.println("Liczba miejsc stojących: "+mainPopulation.getBestIndividual().getStandPlacesNum());
//                System.out.println("Długość: "+mainPopulation.getBestIndividual().getLength());
//                System.out.println("Szerokość: "+mainPopulation.getBestIndividual().getWidth());
                
            	if ( i == 0 )
            		Population mainPopulation = myRemoteObject.getFirstPopulation(numberOfPopulation, numberOfIndividuals);
            	else
            		mainPopulation = myRemoteObject.getNextPopulation(mainPopulation);
            	
                //get the best individual
                Individual best = mainPopulation.getBestIndividual();
                
              //draw visualisation
                controller.visualizate(best, visualisation);                

                frame.add( visualisation );
                frame.setResizable(false);
    		
                frame.pack();
                frame.setVisible( true );
            }
            
        }
    }//GEN-LAST:event_runButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox idividualCheckBox;
    private javax.swing.JTextField individual;
    private javax.swing.JTextField population;
    private javax.swing.JLabel populationLabel;
    private javax.swing.JButton runButton;
    // End of variables declaration//GEN-END:variables
}
