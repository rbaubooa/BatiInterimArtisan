/** Application BatimInterim Artisan
* L'application permet à l'artisan de poser ses congés et de consulter les missions
* qui lui sont attribuées.
* @author Raïd BAUBOOA
* @since Octobre 2012
* @version 0.1
*/

import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import javax.swing.*;



/**La classe Connexion permet de créer l'interface graphique de l'application
*
* @author raid
*
*/

public class Connexion extends JFrame implements ActionListener {
	private JPanel p_connexion, p_congee, p_accueil, p_mission, p_mesConge, p_mesMission ;
	private JLabel dateDebut,dateFin,label,labelId,labelMdp, label_mission, label_conge ;
	private JTextField jtf_debut,jtf_fin ;
	private JButton valider,annuler,connexion,annuler2,retour, nouveauConge, consulterMission, consulterConge ;
	private JMenuBar barreMenu ;
	private JMenu fichier,aide,consulter, quitter ;
	private JMenuItem item_mission,item_conge, item_apropos, mes_conge, fermer;
	final JTextField identifiant,mdp;
	private String prenom, nom, num_artisan ;
	private JList conge_enregistre, mission_enregistre;
	private JScrollPane defil;
	private DefaultListModel liste_conge, liste_mission;
	private Date dateDateDebut, dateDateFin;
	
	/**La méthode Connexion permet d'initialiser les composants de la fenêtre BatiInterim
	*
	*/
	public Connexion() {
	
		barreMenu = new JMenuBar() ;
		setJMenuBar(barreMenu);
		
		fichier = new JMenu("Fichier");
		fichier.setMnemonic('F');
		
		aide = new JMenu("Aide");
		aide.setMnemonic('i');
		aide.addActionListener(this);
		
		consulter = new JMenu("Consulter");
		
		item_apropos = new JMenuItem();
		item_apropos.setText("A propos...");
		item_apropos.addActionListener(this);
		
		aide.add(item_apropos);
		
		mes_conge = new JMenuItem();
		mes_conge.setText("Mes congés");
		mes_conge.addActionListener(this);
		
		fichier.add(consulter) ;
		consulter.setEnabled(false);
		
		item_mission = new JMenuItem("Mes missions");
		item_mission.addActionListener(this);
		consulter.add(item_mission);
		consulter.add(mes_conge);
		
		
		fichier.addSeparator();
		
		
		fermer = new JMenuItem();
		fermer.setText("Fermer");
		fermer.addActionListener(this);
		quitter = new JMenu();
		quitter.setText("Quitter");
		quitter.add(fermer);
		fichier.add(quitter);
		
		barreMenu.add(fichier);
		barreMenu.add(aide);
		
		
		labelId = new JLabel();
		labelId.setText("Identifiant :");
		identifiant = new JTextField(15);
		
		labelMdp = new JLabel();
		labelMdp.setText("Mot de passe :");
		mdp = new JPasswordField(15);
		 
		connexion = new JButton("Envoyer");
		connexion.addActionListener(this);
		annuler2 = new JButton("Annuler");
		annuler2.addActionListener(this);
		  
		p_connexion = new JPanel(new GridLayout(3,1));
		p_connexion.add(labelId);
		p_connexion.add(identifiant);
		p_connexion.add(labelMdp);
		p_connexion.add(mdp);
		p_connexion.add(connexion);
		p_connexion.add(annuler2);
		setTitle("Batim Interim");
		this.validate();
		this.setContentPane(p_connexion) ;
		
		retour = new JButton();
		retour.addActionListener(this);
		retour.setText("Menu");
		
		consulterConge = new JButton();
		consulterConge.setText("Consulter mes congés");
		consulterConge.addActionListener(this);
	
	
	}
	
	/** La méthode actionPerformed() intercepte les évènements afin d'enclencher une action
	* @author raid
	*
	*/
	public void actionPerformed(ActionEvent evt) {
		String value1 = identifiant.getText();
		String value2 = mdp.getText();
		
		if (evt.getSource() == connexion) {
		
			if (value1.equals("") && value2.equals("")){
			
				JOptionPane.showMessageDialog(this,"Les champs sont vides.",
				"Erreur",JOptionPane.ERROR_MESSAGE);
			}
			
			else {
				if (value1.equals("")){
				
				JOptionPane.showMessageDialog(this,"Entrez un nom d'utilisateur",
				"Erreur",JOptionPane.ERROR_MESSAGE);
				}
				else {
					if (value2.equals("")){
					
						JOptionPane.showMessageDialog(this,"Entrez un mot de passe",
						"Erreur",JOptionPane.ERROR_MESSAGE);
					}
					else {
					
						String nomfic = "/media/f9d0b29a-0009-481b-9b48-f8a7aefba8b7/SLAM4/BatiInterim/src/artisans.txt";
						//String nomfic = "/media/raid/f9d0b29a-0009-481b-9b48-f8a7aefba8b7/SLAM4/batiCOURS/src/artisans.txt";
						
						try {
							
							FileInputStream fr = new FileInputStream(nomfic);
							InputStreamReader isr = new InputStreamReader(fr,"ISO-8859-1");
							
							BufferedReader buf = new BufferedReader(isr);
							
							String ligne ;
							
							ligne = buf.readLine();
							
							boolean connecte = false ;
							
							while (ligne != null) {
							
								String str[] = ligne.split(",");
								
								if (value1.equals(str[8]) && value2.equals(str[9])) {
								
								
									nom = str[1];
									prenom = str[2];
									num_artisan = str[0];
									consulter.setEnabled(true);
									
									setTitle("Batim Interim               "+nom+" "+prenom);
									p_accueil = new JPanel() ;
									p_accueil = new JPanel(new GridLayout(4,1));
									
									
									JOptionPane.showMessageDialog(this,"Bienvenue "+" "+nom+" "+prenom,"Bienvenue",JOptionPane.INFORMATION_MESSAGE);
									
									JLabel labelt = new JLabel() ;
									consulterMission = new JButton();
									consulterMission.setText("Consulter missions");
									consulterMission.addActionListener(this);
									nouveauConge = new JButton();
									nouveauConge.addActionListener(this);
									nouveauConge.setText("Nouveau Congé");
									p_accueil.add(labelt);
									p_accueil.add(nouveauConge);
									p_accueil.add(consulterMission);
									p_accueil.add(consulterConge);
									labelt.setText("Vous pouvez planifier un congé,ou consulter vos missions");
									this.setContentPane(p_accueil);
									this.validate() ;
									connecte = true ;
								
								}
								
								ligne = buf.readLine();
							}
							if (connecte == false) {
							
								JOptionPane.showMessageDialog(this,"Combinaison login/mot de passe incorrecte",
								"Erreur",JOptionPane.ERROR_MESSAGE);
								mdp.setText("") ;
							}
							fr.close();
						}
						catch(FileNotFoundException e){
						
							JOptionPane.showMessageDialog(this,"Le fichier de log est introuvable",
							"Erreur",JOptionPane.ERROR_MESSAGE);
						}
						catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					}
			}
		}
		else if (evt.getSource() == annuler2) {
		
			identifiant.setText("") ;
			mdp.setText("") ;
		}
		
		
		else if (evt.getSource() == nouveauConge) {
		
			p_congee = new JPanel() ;
			this.setContentPane(p_congee);
			dateDebut = new JLabel("Date début:") ;
			p_congee.add(dateDebut) ;
			jtf_debut = new JTextField(10) ;
			p_congee.add(jtf_debut) ;
			
			SimpleDateFormat dateCourante = new SimpleDateFormat("dd/MM/yyyy") ;
			String dateCouranteString = dateCourante.format(new Date());
			
			jtf_debut.setText(dateCouranteString);
			dateFin = new JLabel("Date fin: ") ;
			p_congee.add(dateFin) ;
			jtf_fin = new JTextField(10) ;
			jtf_fin.setText(dateCouranteString);
			p_congee.add(jtf_fin) ;
			
			valider = new JButton() ;
			valider.setText("Valider");
			p_congee.add(valider);
			valider.addActionListener(this);
			annuler = new JButton() ;
			annuler.setText("Annuler");
			p_congee.add(annuler) ;
			annuler.addActionListener(this);
			
			this.validate();
		}
		
		else if (evt.getSource() == valider ) {
		
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
			
			
			try {
				Date dateDebut = dateFormat.parse(jtf_debut.getText());
				Date dateFin = dateFormat.parse(jtf_fin.getText());
			
			if(dateFin.before(dateDebut)){
				JOptionPane.showMessageDialog(this,"Veuillez vérifier la date de fin","Erreur",JOptionPane.ERROR_MESSAGE);
			}
			else if(dateFin.after(dateDebut)){
				JOptionPane.showMessageDialog(this,"Votre congé à été enregistré","Information",JOptionPane.INFORMATION_MESSAGE);
				
				
				String nomfic2 = "/media/f9d0b29a-0009-481b-9b48-f8a7aefba8b7/SLAM4/batiCOURS/src/conge.txt";
				//String nomfic2 = "/home/raid/Bureau/conge.txt";
				
				try {
					OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(nomfic2,true),"ISO-8859-1");
					
					PrintWriter pw = new PrintWriter(out);
					
					String ligne2 = identifiant.getText()+ "," + jtf_debut.getText() + "," + jtf_fin.getText() + "\r\n";
					
					pw.write(ligne2) ;
					
					pw.close();
				}
				catch (NullPointerException e) {
					System.out.println("Impossible");
				}
				catch (FileNotFoundException e) {
					System.out.println("Impossible d'ouvrir");
				}
				catch (UnsupportedEncodingException e) {
					System.out.println("Probleme encodage");
				}
				
				this.setContentPane(p_accueil);
				this.validate() ;
			
			}
			else {
				JOptionPane.showMessageDialog(this,"Les dates sont identiques.","Erreur",JOptionPane.ERROR_MESSAGE);
			
			}
			
			
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}		
		
		
		
		else if (evt.getSource() == consulterMission || evt.getSource() == item_mission) {
		
		
			String fichierConge = "/media/f9d0b29a-0009-481b-9b48-f8a7aefba8b7/SLAM4/batiCOURS/src/mission.txt";
			//String fichierConge = "/media/raid/f9d0b29a-0009-481b-9b48-f8a7aefba8b7/SLAM4/batiCOURS/src/mission.txt";
			
			try {
			
				FileInputStream fr = new FileInputStream(fichierConge);
				InputStreamReader isr = new InputStreamReader(fr,"ISO-8859-1");
				
				BufferedReader buf = new BufferedReader(isr);
				
				String ligne ;
				
				ligne = buf.readLine();
				
				liste_mission = new DefaultListModel();
				
				while (ligne != null) {
				
					String str[] = ligne.split(",");
				
					if (str[0].equals(num_artisan) ){
						liste_mission.addElement(str[1]);
					}
					ligne = buf.readLine();
				}
				fr.close();
			}
			catch(FileNotFoundException e){
			
				JOptionPane.showMessageDialog(this,"Le fichier mission.txt est introuvable",
				"Erreur",JOptionPane.ERROR_MESSAGE);
			}
			catch (IOException e) {
			
				e.printStackTrace();
			}
		
			mission_enregistre = new JList(liste_mission);
			mission_enregistre.setSelectionMode(2);
			mission_enregistre.setSelectedIndex(1);
			mission_enregistre.setVisibleRowCount(5);
			defil = new JScrollPane(mission_enregistre);
			
			label_mission = new JLabel();
			label_mission.setText("Liste de vos missions:");
			
			p_mesMission = new JPanel();
			p_mesMission = new JPanel(new GridLayout(3,1));
			this.setContentPane(p_mesMission);
			
			p_mesMission.add(label_mission);
			p_mesMission.add(defil);
			p_mesMission.add(retour);
			this.validate();
		}
		
		
		else if (evt.getSource() == retour || evt.getSource() == annuler) {
		
			this.setContentPane(p_accueil);
			this.validate() ;
		}
		
		
		else if (evt.getSource() == item_apropos){
		JOptionPane.showMessageDialog(this,"BatiInterimArtisan version 0.1","A propos",JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if (evt.getSource() == quitter){
			System.exit(0);
		}
		
		
		
		else if (evt.getSource() == consulterConge || evt.getSource() == mes_conge){
		
		
			String fichierConge = "/media/f9d0b29a-0009-481b-9b48-f8a7aefba8b7/SLAM4/batiCOURS/src/conge.txt";
			//String fichierConge = "/home/raid/Bureau/conge.txt";
			
			try {
			
				FileInputStream fr = new FileInputStream(fichierConge);
				InputStreamReader isr = new InputStreamReader(fr,"ISO-8859-1");
			
				BufferedReader buf = new BufferedReader(isr);
			
				String ligne ;
			
				ligne = buf.readLine();
				
				liste_conge = new DefaultListModel();
			
				while (ligne != null) {
			
					String str[] = ligne.split(",");
			
					if(str[0].equals(value1)){
						liste_conge.addElement("du " + str[1] + " au " + str[2]);
					}
					
			
					ligne = buf.readLine();
				}
			
			
			fr.close();
			}
			catch(FileNotFoundException e){
			
				JOptionPane.showMessageDialog(this,"Le fichier conge.txt est introuvable",
				"Erreur",JOptionPane.ERROR_MESSAGE);
			}
			catch (IOException e) {
			
				e.printStackTrace();
			}
			
			conge_enregistre = new JList(liste_conge);
			conge_enregistre.setSelectionMode(1);
			conge_enregistre.setSelectedIndex(1);
			conge_enregistre.setVisibleRowCount(3);
			defil = new JScrollPane(conge_enregistre);
			//defil.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			label_conge = new JLabel();
			label_conge.setText("Liste de vos congés:");
			
			p_mesConge = new JPanel();
			this.setContentPane(p_mesConge);
			
			p_mesConge.add(label_conge);
			p_mesConge.add(defil);
			p_mesConge.add(retour);
			this.validate();
		
		}
		
		else if (evt.getSource() == fermer){
			System.exit(0);
		}
	}
}