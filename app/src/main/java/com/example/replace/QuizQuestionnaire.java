package com.example.replace;

public class QuizQuestionnaire {

    //1: ~450-900,
    //2: Τύπου 2 - Υψηλής πυκνότητας πολυαιθυλίνη. --τυπου 1 έχει πολυαιθυλήνη χαμηλής πυκνότητας και PET--, τύπου 3 πολυβινυλοχλωρίδιο, κανένα από τα παραπάνω.
    //3: 83 λιτρα νερο
    //4: 8.3 δις τόνους
    //5: 75%
    public String questions[] = {"Πόσα χρόνια χρειάζονται περίπου για την αποσύνθεση ενός πλαστικού μπουκαλιού νερού;",
            "Για την κατασκευή ενός κιλού πλαστικό, απαιτούνται περίπου πόσα λίτρα νερού;", "Από την δεκαετία του 1950, πόσους περίπου τόνους πλαστικό έχουμε κατασκευάσει;",
            "Τι ποσοστό των σκουπιδιών στις θάλασσες αποτελούν τα πλαστικά;", "Τα καπάκια από τα πλαστικά μπουκάλια κατασκευάζονται με πλαστικό τύπου:"};

    public String choices[][] = {
            {"10-50", "50-200", "200-400", "400-1,000"}, {"1-5 λίτρα", "5-20 λίτρα", "20-50 λίτρα", "50-100 λίτρα"}, {"Περίπου 500 εκατομύρια τόνοι", "1-5 δις τόνοι", "5-10 δις τόνοι", "10-20 δις τόνοι"},
            {"0-20%", "20-50%", "50-70%", "70-90%"}, {"Τύπου 1 - Πολυαιθυλίνη χαμηλής πυκνότητας και PET", "Τύπου 2 - Υψηλής πυκνότητας πολυαιθυλίνη", "Τύπου 3 - Πολυβινυλοχλωρίδιο",
            "Κανένα από τα παραπάνω"}
    };

    public String correctAnswer[] = {
            "400-1,000",
            "50-100 λίτρα",
            "5-10 δις τόνοι",
            "70-90%",
            "Τύπου 2 - Υψηλής πυκνότητας πολυαιθυλίνη"
    };
    public String getQuestion(int a){
        String question = questions[a];
        return question;
    }
    public String getchoice1(int a){
        String choice = choices[a][0];
        return choice;
    }
    public String getchoice2(int a){
        String choice = choices[a][1];
        return choice;
    }
    public String getchoice3(int a){
        String choice = choices[a][2];
        return choice;
    }
    public String getchoice4(int a){
        String choice = choices[a][3];
        return choice;
    }
    public String getCorrectAnswer(int a){
        String answer = correctAnswer[a];
        return answer;
    }

}
