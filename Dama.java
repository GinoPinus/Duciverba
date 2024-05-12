import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboPopup;

import java.awt.*;
import java.awt.event.*;

/*
Versione: 0.81
Data: 12/5/24 
 */

public class Dama
{
    JFrame f;

    // Parte Sinistra: board
    JPanel pnlsinistra;

    JButton btnDamiera[][] = new JButton[8][8];
    JPanel pnlTavolo;

    JLabel lblTurno, lblPunteggioB, lblPunteggioN;
    JPanel pnlTurno, pnlPunteggio;

    JLabel lblBianco = new JLabel();
    JLabel lblNero = new JLabel();

    Pedina n = new Pedina(true);
    Pedina b = new Pedina(false);

    Icon icnTmp;

    

    Color marrone = new Color(69, 47, 28);
    Color beige = new Color(240, 222, 173);
    Dama(){
        f = new JFrame("Dama");
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTurno = new JLabel("E' il tuo turno");
        lblTurno.setFont(new Font("Calibri", Font.PLAIN, 24));
        pnlTurno = new JPanel(new GridLayout(1, 1));
        pnlTurno.add(lblTurno);
        pnlTurno.setBorder(new EmptyBorder(20, 450, 0, 0));

        lblPunteggioB = new JLabel("X "+b.nPedine);
        lblPunteggioB.setFont(new Font("Arial", Font.PLAIN, 75));

        lblPunteggioN = new JLabel("X "+n.nPedine);
        lblPunteggioN.setFont(new Font("Arial", Font.PLAIN, 75));

        pnlPunteggio = new JPanel(new GridLayout(2, 2));

        lblBianco.setIcon(b.imgPedina);
        pnlPunteggio.add(lblBianco);

        pnlPunteggio.add(lblPunteggioB);

        lblNero.setIcon(n.imgPedina);
        pnlPunteggio.add(lblNero);

        pnlPunteggio.add(lblPunteggioN);
        pnlPunteggio.setBorder(new EmptyBorder(0, 0, 0, 200));

        pnlTavolo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlsinistra = new JPanel(new GridLayout(8, 8));
        pnlsinistra.setBorder(new EmptyBorder(30, 50, 50, 50));
        pnlsinistra.setPreferredSize(new Dimension(1000, 1000));

        boolean temp = false; // per alternare il marrone e il biege sulla damiera

        int cN = 0, cB = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                btnDamiera[i][j] = new JButton();
                btnDamiera[i][j].addActionListener(new SelezionePedina());
                if (temp) {
                    btnDamiera[i][j].setBackground(beige);
                    temp = false;
                } else {
                    btnDamiera[i][j].setBackground(marrone);
                    if (cN > 19) {
                        btnDamiera[i][j].setIcon(b.imgPedina);
                        // btnDamiera[i][j].setText("N");
                    }

                    if (cB < 12) {
                        btnDamiera[i][j].setIcon(n.imgPedina);
                        // btnDamiera[i][j].setText("B");
                        cB++;
                    }
                    cN++;
                    temp = true;
                }
                pnlsinistra.add(btnDamiera[i][j]);

            }

            if (temp) {
                temp = false;
            } else {
                temp = true;
            }
        }

        

        pnlTavolo.add(pnlsinistra);

        f.add(pnlTavolo, "Center");
        f.setMinimumSize(new Dimension(1300, 1300));
        
        pnlPunteggio.setBackground(Color.LIGHT_GRAY);
        pnlTurno.setBackground(Color.LIGHT_GRAY);
        pnlsinistra.setBackground(Color.LIGHT_GRAY);
        pnlTavolo.setBackground(Color.LIGHT_GRAY);

        f.add(pnlTurno, "North");
        f.add(pnlPunteggio, "East");
 
    }
    JButton btnPosto1 = null;  
    class SelezionePedina implements ActionListener {   
        int punteggioB=0 , punteggioN=0 ;
        public void actionPerformed(ActionEvent e) {
            JButton btnTemp = (JButton) e.getSource();
            if(btnPosto1 == null){
                if(btnTemp.getIcon()==n.imgPedina||btnTemp.getIcon()==n.imgDama){
                    btnPosto1 = btnTemp;
                }else if(btnTemp.getIcon()==b.imgPedina||btnTemp.getIcon()==b.imgDama){
                    btnPosto1 = btnTemp;

                }
            }else if(btnTemp.getBackground() == marrone && btnTemp.getIcon() == null){
                icnTmp=btnPosto1.getIcon();
                btnPosto1.setIcon(null);
                btnTemp.setIcon(icnTmp);
                for(int i=0;i<8;i++){
                    if (btnDamiera[0][i].getIcon()==b.imgPedina){
                        btnDamiera[0][i].setIcon(b.imgDama);
                    }

                    if (btnDamiera[7][i].getIcon()==n.imgPedina){
                        btnDamiera[7][i].setIcon(n.imgDama);
                    }
                    for(int j=0;j<8;j++){
                        if(btnDamiera[i][j].getIcon()==n.imgPedina||btnDamiera[i][j].getIcon()==n.imgDama){
                            punteggioN++;
                        }
                        if(btnDamiera[i][j].getIcon()==b.imgPedina||btnDamiera[i][j].getIcon()==b.imgDama){
                            punteggioB++;
                        }
                    }
                }
                lblPunteggioB.setText("X "+(Integer.toString(punteggioB)));
                lblPunteggioN.setText("X "+(Integer.toString(punteggioN)));
                punteggioB=0;
                punteggioN=0;
                //qua dobbiamo fare il controllore con le regole del gioco(se la pedina puo andare in quel posto oppure no)
                btnPosto1 = null;
            }
        }
    }

    public static void main(String[] args) {
        new Dama();
    }
}