package dossier;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Fenetre extends JFrame {

	private JPanel contentPane;
	private JTextField txtnom;
	private JTextField txtprenom;
	private JTextField txtAge;
	private JTextField txtChercher;
	private JTable tableauEtudiant;
	ArrayList<Etudiant> etudiants = new ArrayList<>();
	
	
	DefaultTableModel modele;
	
	//les fonctions pour savoir quel bouton a été cliqué
	
			private void ajouterEtudiant() {
				modele = (DefaultTableModel)tableauEtudiant.getModel(); //lié le model avec le tableau
				boolean ajout = true;
				if(txtnom.getText().equals("") || txtprenom.getText().equals("") || txtAge.getText().equals("")) {
					//affiche boite de dialogue pr avertissement
					
					JOptionPane.showMessageDialog(this,"Remplisser tous les champs svp","Champs vides",JOptionPane.WARNING_MESSAGE);
					ajout= false;
				}
				//je doit empecher la redandance boucle for
				for(Etudiant e: etudiants) {
					if(e.getNom().equals(txtnom.getText()) && e.getPrenom().equals(txtprenom.getText())) {
						JOptionPane.showMessageDialog(this, "cet élève est déja inscrit", "Eleve existant", JOptionPane.ERROR_MESSAGE);
						ajout =false;
						viderChamps(); 
					}
					
				}
				if(ajout) {
					Etudiant etudiant = new Etudiant(txtnom.getText(), txtprenom.getText(), Integer.valueOf(txtAge.getText()));
					//ajouter etudiant dans tablea
					
					etudiants.add(etudiant);
					
					
					// afficher etudiant dans interface il faut un modle
					modele.addRow(new Object[] {etudiant.getInscription(),
						etudiant.getNom(), etudiant.getPrenom(), etudiant.getAge()});
					viderChamps();
				}
			}
			
			//fonction pour supprimer un etudiant
			public void supprimerEtudiant() {
				//supprimer la selection
				modele = (DefaultTableModel)tableauEtudiant.getModel();	
				int ligne = tableauEtudiant.getSelectedRow(); //recup num lign select
				//-1 veut dire on a rien select
				if(ligne != -1) {
					etudiants.remove(ligne);
					modele.removeRow(ligne);
					viderChamps();
				}
				
			}

			//boutton modifier
			
			public void modifierEtudiant() {
				modele= (DefaultTableModel)tableauEtudiant.getModel();
				int ligne=tableauEtudiant.getSelectedRow();
				
				if(ligne != -1) {
					modele.setValueAt(txtnom.getText(), ligne, 1);// colone 1 pr nom
					modele.setValueAt(txtprenom.getText(), ligne, 2);
					modele.setValueAt(txtAge.getText(), ligne, 3);
					etudiants.get(ligne).setNom(txtnom.getText());
					etudiants.get(ligne).setPrenom(txtprenom.getText());
					etudiants.get(ligne).setAge(Integer.valueOf(txtAge.getText()));
					
				}
			}
			
			
			
			private void viderChamps() {
				txtnom.setText("");
				txtprenom.setText("");
				txtAge.setText("");
				txtnom.requestFocus();
			}
			
			//boutton recherche
			
			public void recherche() {
				
				modele= (DefaultTableModel)tableauEtudiant.getModel();
				boolean trouve =false;
				int indice =0;
				
				if(txtChercher.getText().equals("")) {
					JOptionPane.showMessageDialog(this,"Entrer un nom svp","champs vide",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					String nomChercher = txtChercher.getText();
					
					
					for(int i=0; i<etudiants.size();i++) {
						Etudiant e = etudiants.get(i);
						if(nomChercher.equals(e.getNom()) || nomChercher.equals(e.getPrenom())){
							trouve=true;
							indice=i;
							break;
						}
					}	
				}
				if(trouve) {
					txtnom.setText(etudiants.get(indice).getNom());
					txtprenom.setText(etudiants.get(indice).getPrenom());
					txtAge.setText(String.valueOf(etudiants.get(indice).getAge()));
					tableauEtudiant.setRowSelectionInterval(indice,indice);//colorier ligne
				}
				else {
					JOptionPane.showMessageDialog(this,"Eleve n'est pas dans la liste","Eleve inexistant",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
			
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fenetre frame = new Fenetre();
					frame.setVisible(true);
					frame.setTitle("Gestion des étudiants");
					frame.setResizable(false);
					frame.setLocation(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the frame.
	 */
	public Fenetre() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 706, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 10, 669, 485);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 10, 233, 138);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 26, 61, 27);
		panel_1.add(lblNewLabel);
		
		JLabel label = new JLabel("prenom");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 59, 61, 27);
		panel_1.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("age");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 84, 71, 39);
		panel_1.add(lblNewLabel_1);
		
		txtnom = new JTextField();
		txtnom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char touche = e.getKeyChar(); //te donne touche du clavier sur laquel j'ai cliqué
				
				if(touche==KeyEvent.VK_ENTER && !txtnom.getText().equals("")) {
					//si j'ai cliqué sur entrer et si nom plein (diff de vide)
					txtprenom.requestFocus();//mettre curseur sur prenom
				}
				if(Character.isDigit(touche)) {
					//isDigit veut dire un nombre
					e.consume(); //consume veut dire n'applique pas
				}
					
			}
		});
		txtnom.setBounds(105, 32, 96, 19);
		panel_1.add(txtnom);
		txtnom.setColumns(10);
		
		txtprenom = new JTextField();
		txtprenom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char touche = e.getKeyChar(); //te donne touche du clavier sur laquel j'ai cliqué
				
				if(touche==KeyEvent.VK_ENTER && !txtprenom.getText().equals("")) {
					//si j'ai cliqué sur entrer et si nom plein (diff de vide)
					
					txtAge.requestFocus();//mettre curseur sur prenom
				}
				if(Character.isDigit(touche)) {
					e.consume();
				}
			}
		});
		txtprenom.setBounds(105, 65, 96, 19);
		panel_1.add(txtprenom);
		txtprenom.setColumns(10);
		
		txtAge = new JTextField();
		txtAge.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char touche = e.getKeyChar(); //te donne touche du clavier sur laquel j'ai cliqué
				
				if(touche==KeyEvent.VK_ENTER && !txtAge.getText().equals("")) {
					//si j'ai cliqué sur entrer et si nom plein (diff de vide)
					
					ajouterEtudiant();//on ajoute l'etudiant
			}
				if(Character.isLetter(touche)) {
					e.consume();
				}
		}});
		
		txtAge.setBounds(105, 96, 96, 19);
		panel_1.add(txtAge);
		txtAge.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(264, 10, 170, 138);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton bouttonAjouter = new JButton("Ajouter");
		bouttonAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterEtudiant();
				
			}
			

		});
		
		bouttonAjouter.setBounds(41, 29, 85, 21);
		panel_2.add(bouttonAjouter);
		
		JButton btnModdifier = new JButton("Modifier");
		btnModdifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifierEtudiant();
			}
		});
		btnModdifier.setBounds(41, 60, 85, 21);
		panel_2.add(btnModdifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerEtudiant();
			}
		});
		btnSupprimer.setBounds(41, 91, 85, 21);
		panel_2.add(btnSupprimer);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(468, 10, 191, 138);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label_1 = new JLabel("Chercher un élève");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(20, 24, 127, 28);
		panel_3.add(label_1);
		
		txtChercher = new JTextField();
		txtChercher.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char touche = e.getKeyChar();
				if(touche==KeyEvent.VK_ENTER && !txtChercher.getText().equals("")) {
					
					recherche();
				}
			}
		});
		txtChercher.setColumns(10);
		txtChercher.setBounds(20, 54, 146, 28);
		panel_3.add(txtChercher);
		
		JButton btnChercher = new JButton("Valider");
		btnChercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recherche();
			}
		});
		btnChercher.setBounds(52, 107, 85, 21);
		panel_3.add(btnChercher);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modele= (DefaultTableModel)tableauEtudiant.getModel();
				int ligne=tableauEtudiant.getSelectedRow();
				
				if(ligne != -1) {
					txtnom.setText(etudiants.get(ligne).getNom());
					txtprenom.setText(etudiants.get(ligne).getPrenom());
					txtAge.setText(String.valueOf(etudiants.get(ligne).getAge()));
				}
				
				
				
			}
		});
		scrollPane.setBounds(23, 180, 625, 295);
		panel.add(scrollPane);
		
		tableauEtudiant = new JTable();
		tableauEtudiant.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N inscription", "nom", "prenom", "Age"
			}
		));
		scrollPane.setViewportView(tableauEtudiant);
		
		
	}
}
