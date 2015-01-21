package hangout;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Andrew Pinion
 */
public class gameFrame extends javax.swing.JFrame
{
    private final wordManager wordMgr;
    private String word = "";
    private String dispWord = "";
    private final String usedLetterTitle = "Used Letters: ";
    private String usedLetterStr = "";
    private boolean errors = false;
    
    public boolean errors() { return errors; }
    
    public gameFrame()
    {
        this.setDefaultCloseOperation(gameFrame.HIDE_ON_CLOSE);
        initComponents();
        
        try
        {
            Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png"));
            setIconImage(image);
        }
        catch(Exception e)
        {
            System.err.println("Icon Image NOT Found :(");
        }
        
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
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Hangout");
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangout/0.png"))); // NOI18N

        jButton2.setText("New Game");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wordDisplay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usedLetters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputBox)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_inputBoxActionPerformed
    {//GEN-HEADEREND:event_inputBoxActionPerformed
        submitInput();
    }//GEN-LAST:event_inputBoxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        manPieces = 0;
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangout/0.png")));
        word = wordMgr.getRandomWord();
        dispWord = "";
  
        for(int i = 0; i< word.length(); i++) 
            if(word.charAt(i) == ' ') dispWord += " "; 
            else dispWord += "-";
        wordDisplay.setText(dispWord);
        usedLetterStr = "";
        usedLetters.setText(usedLetterTitle + "" + usedLetterStr);
        inputBox.setEnabled(true);
        inputBox.setOpaque(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void submitInput()
    {
        if (manPieces == 6)
        {
           return;
        }
        String input = inputBox.getText();
        String toLowerCase = input.toLowerCase();
        input = toLowerCase;
        if (input.equals("")) return;
        if (input.charAt(0) == '!') 
        {
            if (input.contains("kill")) manPieces = 5;
            //if (input.contains("cheat")) manPieces = 0;
        }
        
        String oldDispWord = dispWord;
        char letter = input.charAt(0);
        
        //check to make sure this letter wasn't attempted yet
        CharSequence s = "" + letter;
        if (usedLetterStr.contains(s)) 
        {
            inputBox.setText(null);
            return;
        }
        
        dispWord = "";
        boolean letterFound = false;

        usedLetterStr += input.charAt(0);
        usedLetters.setText(usedLetterTitle + "" + usedLetterStr);
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
            addHangmanPiece();
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
        
        checkWinCondition();
    }
    
    void kill()
    {
        inputBox.setText(null);
        inputBox.setEnabled(false);
        inputBox.setOpaque(false);
        wordDisplay.setText("FAIL: The word was " + word);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangout/6.png")));
    }
    
    void checkWinCondition()
    {
        Boolean dashesFound = false;
        for(int i=0;i<dispWord.length();i++)
        {
            if (dispWord.charAt(i) == '-') dashesFound = true;
        }
        if (!dashesFound)
        {
            //we won
            inputBox.setText(null);
            inputBox.setEnabled(false);
            inputBox.setOpaque(false);
            wordDisplay.setText("Victory! Word is " + word);
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangout/7.png")));
        }
    }
    
    int manPieces = 0;
    
    //add a new body part to the hangman image, when a guessed letter is incorrect
    void addHangmanPiece()
    {
        if      (manPieces == 0) jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangout/1.png")));
        else if (manPieces == 1) jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangout/2.png")));
        else if (manPieces == 2) jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangout/3.png")));
        else if (manPieces == 3) jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangout/4.png")));
        else if (manPieces == 4) jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangout/5.png")));
        else if (manPieces == 5) jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hangout/6.png")));
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
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel usedLetters;
    private javax.swing.JLabel wordDisplay;
    // End of variables declaration//GEN-END:variables
}
