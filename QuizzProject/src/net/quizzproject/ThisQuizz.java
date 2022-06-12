package net.quizzproject;

import java.util.LinkedList;
import java.util.List;

public class ThisQuizz {
	int playerScore;// Le score en cours
	int nbrOfQuestions;// Nombres de questions suivant la difficulté du jeu
	List<Integer> isAllreadyRead = new LinkedList<Integer>();// Ce souvenir des quizz déjà faits
	List<String> Answers = new LinkedList<String>();// sert a stocker les réponses a choisir, de 2 a 4 maximum
	String Reponse;// Stocke la réponse
	String Difficult;// Stock la difficulté

	public ThisQuizz() {
		// TODO Auto-generated constructor stub
	}

	// Renvoie ou définie le score du joueur
	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public int getPlayerScore() {
		return this.playerScore;
	}

	// Renvoie le nombres de questions suivant la difficulté
	public int getNbrOfQuestions() {
		int questionsNumber = 0;
		if (getDifficult() == "Facile") {
			questionsNumber = 10;
		} else if (getDifficult() == "Normal") {
			questionsNumber = 15;
		} else if (getDifficult() == "Difficile") {
			questionsNumber = 20;
		} else if (getDifficult() == "Expert") {
			questionsNumber = 30;
		}
		return questionsNumber;
	}

	// Renvoie ou définie les questions qui on deja etait lu pour ne pas les relire
	// une seconde fois
	public void setIsAllreadyRead(List<Integer> isAllreadyRead, int lineNbr) {
		isAllreadyRead.add(lineNbr);
		this.isAllreadyRead = isAllreadyRead;
	}

	public List<Integer> getIsAllreadyRead() {
		return this.isAllreadyRead;
	}

	// Renvoie ou définie les réponses à afficher
	public void setAnswers(List<String> Answers) {
		this.Answers = Answers;
	}

	public List<String> getAnswers() {
		return this.Answers;
	}

	// Renvoie ou définie la reponse à comparer
	public void setReponse(String Reponse) {

		this.Reponse = Reponse;
	}

	public boolean getReponse(String thisResponse) {
		Reponse = thisResponse.substring(0, 1);
		if (Reponse.contains("1") == true) {
			return true;
		} else {
			return false;
		}

	}

	// Renvoie ou définie la difficulté selectionner
	public void setDifficult(String Difficult) {
		this.Difficult = Difficult;
	}

	public String getDifficult() {
		return this.Difficult;
	}
}
