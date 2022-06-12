package net.quizzproject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class QuizzMain extends JFrame {

	private JPanel contentPane;
	private JButton btnStart = new JButton("Jouez");
	private JButton btnNext = new JButton("Valider la réponse");
	private JLabel lblStepsVAL = new JLabel("0/10");
	private JLabel lblScoresVAL = new JLabel("0/10");
	private JComboBox cbxDifficult = new JComboBox();
	private JTextArea txaQuestion = new JTextArea();
	private JRadioButton rdbtnA = new JRadioButton("Réponse A");
	private JRadioButton rdbtnB = new JRadioButton("Réponse B");
	private JRadioButton rdbtnC = new JRadioButton("Réponse C");
	private JRadioButton rdbtnD = new JRadioButton("Réponse D");
	private ThisQuizz thisQuizz = new ThisQuizz();
	private int stepsNBR = 0;
	int countSteps = 0;
	int countAnswer = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub

				try {
					QuizzMain frame = new QuizzMain();
					frame.setLocationRelativeTo(null);
					ThisQuizz thisQuizz = new ThisQuizz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuizzMain() {
		setForeground(Color.LIGHT_GRAY);

		setBackground(Color.WHITE);
		setTitle("Quiz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 320);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblDifficultTXT = new JLabel("Choisissez le niveau de difficulté");
		lblDifficultTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblScoresTXT = new JLabel("Score");
		lblScoresTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblStepsTXT = new JLabel("Etapes");
		lblStepsTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblAnswersTXT = new JLabel("Réponses");
		lblAnswersTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));

		cbxDifficult.setModel(new DefaultComboBoxModel(new String[] { "Facile", "Normal", "Difficile", "Expert" }));
		cbxDifficult.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblScoresVAL.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblScoresVAL.setHorizontalAlignment(SwingConstants.CENTER);

		lblStepsVAL.setHorizontalAlignment(SwingConstants.CENTER);
		lblStepsVAL.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnA.setSelected(true);

		rdbtnA.setFont(new Font("Tahoma", Font.PLAIN, 14));

		rdbtnB.setFont(new Font("Tahoma", Font.PLAIN, 14));

		rdbtnC.setFont(new Font("Tahoma", Font.PLAIN, 14));

		rdbtnD.setFont(new Font("Tahoma", Font.PLAIN, 14));

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnA);
		bg.add(rdbtnB);
		bg.add(rdbtnC);
		bg.add(rdbtnD);

		JLabel lblQuestionTXT = new JLabel("Question");
		lblQuestionTXT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisQuizz.setPlayerScore(0);
				thisQuizz.isAllreadyRead.clear();
				thisQuizz.Answers.clear();
				cbxDifficult.setEnabled(false);
				stepsNBR = 0;

				startGame();
			}
		});

		txaQuestion.setText("Petit jeu de Quiz, amusez-vous bien!!");
		txaQuestion.setLineWrap(true);
		txaQuestion.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txaQuestion.setEditable(false);

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String thisResponse = "";

				if (rdbtnA.isSelected() == true) {
					thisResponse = thisQuizz.getAnswers().get(0);
				} else if (rdbtnB.isSelected() == true) {
					thisResponse = thisQuizz.getAnswers().get(1);
				} else if (rdbtnC.isSelected() == true) {
					thisResponse = thisQuizz.getAnswers().get(2);
				} else if (rdbtnD.isSelected() == true) {
					thisResponse = thisQuizz.getAnswers().get(3);
				}
				// On ajoute +1 au score si la condition est vrai
				if (thisQuizz.getReponse(thisResponse) == true) {
					thisQuizz.setPlayerScore(thisQuizz.getPlayerScore() + 1);
				}
				// On reinitialise certain element de la class thisQuizz
				thisQuizz.Answers.clear();
				stepsNBR++;
				startGame();

			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNext.setEnabled(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(5)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblQuestionTXT, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblDifficultTXT, GroupLayout.PREFERRED_SIZE, 221,
												GroupLayout.PREFERRED_SIZE)
										.addGap(36).addComponent(lblScoresTXT, GroupLayout.PREFERRED_SIZE, 91,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(cbxDifficult, GroupLayout.PREFERRED_SIZE, 193,
												GroupLayout.PREFERRED_SIZE)
										.addGap(54).addComponent(lblScoresVAL, GroupLayout.PREFERRED_SIZE, 56,
												GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStepsTXT, GroupLayout.PREFERRED_SIZE, 64,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblStepsVAL, GroupLayout.PREFERRED_SIZE, 56,
												GroupLayout.PREFERRED_SIZE)))
						.addComponent(txaQuestion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnA, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
						.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
						.addComponent(lblAnswersTXT, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnStart, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
						.addComponent(rdbtnB, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
						.addComponent(rdbtnC, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
						.addComponent(rdbtnD, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(6)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDifficultTXT, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblScoresTXT, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStepsTXT, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAnswersTXT, GroupLayout.PREFERRED_SIZE, 21,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(7)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(4).addComponent(cbxDifficult,
								GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblStepsVAL,
												GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblScoresVAL, GroupLayout.PREFERRED_SIZE, 24,
										GroupLayout.PREFERRED_SIZE)))
				.addGap(8)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(11).addComponent(rdbtnA)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(rdbtnB, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(rdbtnC, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(rdbtnD, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGap(13).addComponent(btnNext).addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnStart))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblQuestionTXT, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(txaQuestion, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}

	public void startGame() {

		btnNext.setEnabled(true);
		btnStart.setEnabled(false);
		// On efface les anciennes réponses
		this.rdbtnA.setText("Réponse A");
		this.rdbtnA.setSelected(true);
		this.rdbtnB.setText("Réponse B");
		this.rdbtnC.setText("Réponse C");
		this.rdbtnD.setText("Réponse D");
		// WaitForCheckBox();
		// On défini le niveau de difficulté
		thisQuizz.setDifficult(cbxDifficult.getSelectedItem().toString());

		// On lit le fichier ressource et on met a jours les affichages
		proceedFile();

		// On met a jour le score, les etapes et les valeurs mamimal
		lblScoresVAL.setText(
				String.valueOf(thisQuizz.getPlayerScore()) + "/" + String.valueOf(thisQuizz.getNbrOfQuestions()));
		lblStepsVAL.setText(stepsNBR + "/" + String.valueOf(thisQuizz.getNbrOfQuestions()));

		// Si la partie est finie on reinitialise
		if (stepsNBR == thisQuizz.getNbrOfQuestions()) {

			btnStart.setEnabled(true);
			btnNext.setEnabled(false);
			// On efface les anciennes réponses
			this.rdbtnA.setText("Réponse A");
			this.rdbtnA.setSelected(true);
			this.rdbtnB.setText("Réponse B");
			this.rdbtnC.setText("Réponse C");
			this.rdbtnD.setText("Réponse D");
			txaQuestion.setText("Petit jeu de Quiz, amusez-vous bien!!");
			countSteps = 0;
			countAnswer = 0;
			btnStart.setEnabled(true);
			btnNext.setEnabled(false);
			this.cbxDifficult.setEnabled(true);
			// On affiche un message message box si le joueur a fait un sans faute
			if (thisQuizz.getPlayerScore() == stepsNBR) {
				JOptionPane.showMessageDialog(null, "Félicitation, vous avez fait un score parfait !!!", "Résultats",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane
						.showMessageDialog(null,
								"Vous avez fait un score de: " + thisQuizz.getPlayerScore() + " sur " + stepsNBR
										+ " , vous pouvez vou améliorer !!",
								"Résultats", JOptionPane.INFORMATION_MESSAGE);
			}

			return;
		}
	}

	public void proceedFile() {
		// On genere un nombre aléatoire pour chercher une questions dans la liste
		Random random = new Random();
		int randomValue = random.nextInt(35 + 0) + 0;
		// On regarde si le nombre est deja dans la liste
		while (thisQuizz.getIsAllreadyRead().contains(randomValue) == true) {

			randomValue = random.nextInt(35 + 0) + 0;
		}

		thisQuizz.setIsAllreadyRead(thisQuizz.getIsAllreadyRead(), randomValue);
		// On lit le fichier a la ligne generer par la fonction random
		InputStream stream = QuizzMain.class.getResourceAsStream("/Quizz.txt");

		if (stream == null)
			JOptionPane.showMessageDialog(null, "Resource not located.");

		Scanner input = null;
		try {

			input = new Scanner(stream, "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Scanner error");
		}

		// On lit le fichier resource

		String line;

		for (int i = 0; i < 35; i++) {
			line = input.nextLine();
			// on cherche la ligne texte qui correspond au nombre aléatoire

			if (i == randomValue) {
				// On decoupe la chaine pour séparer la question des réponses
				String[] words = line.split("=");
				String question = words[0]; // La question
				String answers = words[1]; // Les reponses
				txaQuestion.setText(question);
				// On redécoupe pour séparer les réponses
				String[] theRest = answers.split(";");

				String answerA = theRest[0];
				thisQuizz.Answers.add(answerA);
				this.rdbtnA.setText(answerA.substring(2, answerA.length()));

				String answerB = theRest[1];
				thisQuizz.Answers.add(answerB);
				this.rdbtnB.setText(answerB.substring(2, answerB.length()));

				if (theRest.length == 2) {
					this.rdbtnC.setEnabled(false);
					this.rdbtnD.setEnabled(false);
				} else if (theRest.length == 3) {
					String answerC = theRest[2];
					thisQuizz.Answers.add(answerC);
					this.rdbtnC.setText(answerC.substring(2, answerC.length()));
					this.rdbtnC.setEnabled(true);
					this.rdbtnD.setEnabled(false);
				} else if (theRest.length == 4) {
					String answerC = theRest[2];
					thisQuizz.Answers.add(answerC);
					this.rdbtnC.setText(answerC.substring(2, answerC.length()));
					this.rdbtnC.setEnabled(true);
					///////////////////////////////////////////////////////////
					String answerD = theRest[3];
					thisQuizz.Answers.add(answerD);
					this.rdbtnD.setText(answerD.substring(2, answerD.length()));
					this.rdbtnD.setEnabled(true);
				}

			}
		}
		System.out.println(thisQuizz.getAnswers());

	}

}
