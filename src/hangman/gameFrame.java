/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangman;

import java.io.FileNotFoundException;

/**
 *
 * @author Peetseys
 */
public class gameFrame extends javax.swing.JFrame
{
    private final wordManager wordMgr;
    private String word = "";
    private String dispWord = "";
    private String usedLetterStr = "Used Letters: ";
    private boolean errors = false;
    
    public boolean errors() { return errors; }
    
    public gameFrame()
    {
        this.setDefaultCloseOperation(gameFrame.HIDE_ON_CLOSE);
        initComponents();
        wordMgr = new wordManager();
        if (wordMgr.fileExists()) errors = true;
        word = wordMgr.getRandomWord();
        dispWord = "";
  
        for(int i = 0; i< word.length(); i++) 
            if(word.charAt(i) == ' ') dispWord += " "; 
            else dispWord += "-";
        wordDisplay.setText(dispWord);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        wordDisplay = new javax.swing.JLabel();
        usedLetters = new javax.swing.JLabel();
        inputBox = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Hangman");
        setResizable(false);

        wordDisplay.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        wordDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wordDisplay.setText("<WORD>");

        usedLetters.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usedLetters.setText("Used Letters:");

        inputBox.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                inputBoxActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangman/0.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wordDisplay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usedLetters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputBox))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(200, 200, 200))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(wordDisplay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usedLetters)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inputBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_inputBoxActionPerformed
    {//GEN-HEADEREND:event_inputBoxActionPerformed
        submitInput();
    }//GEN-LAST:event_inputBoxActionPerformed

    private void submitInput()
    {
        if (manPieces == 6)
        {
           return;
        }
        String input = inputBox.getText();
        input.toLowerCase();
        
        String oldDispWord = dispWord;
        char letter = input.charAt(0);
        dispWord = "";
        boolean letterFound = false;

        usedLetterStr += input.charAt(0);
        usedLetters.setText(usedLetterStr);
        for(int i = 0;i<word.length(); i++)
        {
            if (oldDispWord.charAt(i) == '-')
            {
                if (letter == word.charAt(i))
                {
                    dispWord += letter;
                    letterFound = true;
                }
                else dispWord += "-";
            }
            else dispWord += oldDispWord.charAt(i);
        }
        if (!letterFound) 
        {
            drawHangman();
            if (manPieces == 6)
            {
                inputBox.setText(null);
                inputBox.setEnabled(false);
                inputBox.setOpaque(false);
                wordDisplay.setText("FAIL: The word was " + word);
                return;
            }
        }
        wordDisplay.setText(dispWord);
        inputBox.setText(null);
    }
    
    int manPieces = 0;
    void drawHangman()
    {
        if (manPieces == 0) jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangman/1.png")));
        else if (manPieces == 1) jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangman/2.png")));
        else if (manPieces == 2) jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangman/3.png")));
        else if (manPieces == 3) jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangman/4.png")));
        else if (manPieces == 4) jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangman/5.png")));
        else if (manPieces == 5) jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangman/6.png")));
        manPieces++;
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(gameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(gameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(gameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(gameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                
                new gameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField inputBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel usedLetters;
    private javax.swing.JLabel wordDisplay;
    // End of variables declaration//GEN-END:variables
}
