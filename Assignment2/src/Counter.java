/**
 * This class will be used to calculate the number of points from a Cribbage hand.
 *
 * Class: ICS4U1
 * Date: Feb 27 2023
 * @author James Wong
*/

public class Counter {

    /** hand of cards class */
    private PowerSet <Card> cardps;

    /** starter card */
    private Card starter;

    /**
     * Name: Counter
     * @param hand hand object
     * @param starter starter object
     */
    public Counter(Card[] hand, Card starter) {
        this.starter = starter;
        this.cardps = new PowerSet<Card>(hand);
    }

    /**
     * Name: countPoints
     * basically all the scoring for the game
     * @return totalPoints
     */
    public int countPoints(){
        int totalPoints = 0;    // total points variable

        /** Pairs
         * Two cards with the same number/letter label are considered a pair and it is worth 2 points. If you
         * have more than 2 cards with the same number/letter label, each pair would score
         */
        for (int i=0; i<cardps.getLength(); i++) {      // loop through all the sets in powerSet
            if (cardps.getSet(i).getLength() == 2) {    // check for all sets of 2
                if (cardps.getSet(i).getElement(0).getLabel() == cardps.getSet(i).getElement(1).getLabel()) {   // if the cards have the same number
                    totalPoints += 2;                   // add two points
                }
            }
        }



        /** Runs
         * Three or more cards with consecutive ranks (remember that J, Q, and K have ranks of 11, 12,
         * and 13 respectively for this scoring category) are considered to form a run. Only the longest run
         * will count and will score points equal to the length of the run.
        */
        for (int i=0; i<cardps.getLength(); i++) {      // loop through all the sets in powerSet
            if (isRun(cardps.getSet(i))) {              // if the set of cards is a run
                boolean test = false;                   // set up a boolean variable
                for (int j=0; j<cardps.getLength(); j++) {  // run through all the sets in the powerSet again to compare
                    if (isRun(cardps.getSet(j))) {          // if another set of cards is a run
                        if (cardps.getSet(j).getLength() > cardps.getSet(i).getLength()){   // compare both sets of cards to see which is larger
                            test = true;        // return true
                        }
                    }
                }
                if (!test){
                    totalPoints += cardps.getSet(i).getLength();    // if true then all the number of cards in that set to the total points
                }
            }
         }


        /** Fifteen
         * Any combination of cards that add up to 15 will score 2 points. For example, 6 and 9 add up to
         * 15 so that would score 2 points. Queen, Ace, and 4 add up to 15 and would also score 2 points.
         * Remember that J, Q, and K are all worth 10 in this scoring category.
         */

        for (int i=0; i<cardps.getLength(); i++) {      // loop through all the sets in powerSet
            int calc = 0;                               // create variable of the calculations
            for(int j = 0; j < cardps.getSet(i).getLength(); j++) {     // loop through each set's length
                calc += cardps.getSet(i).getElement(j).getFifteenRank();    // add the fifteenRank value to the calculation
            }
            if (calc == 15) {       // if the calculation is equal to 15
                totalPoints += 2;   // then add 2 points
            }
        }



        /** Flush
         * If all 4 cards in the hand have the same suit, it is a flush worth 4 points. If the starter also has
         * the same suit as the 4 cards in the hand, then it scores 5 points instead of 4.
         * */
        for (int i=0; i<cardps.getLength(); i++) {      // loop through all the sets in powerSet
            int tempPoints = 0;                         // create variable to hold the number of points
            if (cardps.getSet(i).getLength() == 4) {    // if the card set length is equal to 4 cards
                if (!(cardps.getSet(i).contains(starter))) {                   // if the set doesnt contain the starter
                    String hold = cardps.getSet(i).getElement(0).getSuit(); // variable to hold the suit of the first card
                    for (int j=0; j<4; j++) {           // loop through all 4 cards
                        if (hold == cardps.getSet(i).getElement(j).getSuit()) {     // if the suit is equal to each element's suit
                            tempPoints++;       // add to the temporary points
                        }
                    }
                    if (tempPoints == 4) {      // if all 4 cards have the same suit
                        if (hold == starter.getSuit()) {    // check if the starter has the same suit
                            tempPoints++;       // add one more to the temporary points
                        }
                    }
                }
            }
            if (tempPoints == 4 || tempPoints == 5){    // if the 4 of the cards or all 4 and the starter have the same suit
                totalPoints += tempPoints;      // add to the total points
            }
        }

        /** His Knobs
         * If there is a Jack within the 4 cards of the hand and its suit matches the suit of the starter card, it
         * is called "His Knobs" and is worth 1 point.
         */
        for (int i=0; i<cardps.getLength(); i++) {     // loop through all the sets in powerSet
            if (cardps.getSet(i).getLength() == 1) {   // if the length of the set if 1 bc we only need each individual "card"
                if (cardps.getSet(i).getElement(0).getLabel() == "J") {     // the card is equal to Jack
                    if (this.starter.getSuit() == cardps.getSet(i).getElement(0).getSuit()){    // if they have the same suit
                        totalPoints += 1;   // add a point
                    }
                }
            }
        }

        return totalPoints;     // return total points
    }

    private boolean isRun (Set<Card> set) {
        // In this method, we are going through the given set to check if it constitutes a run of 3 or more
        // consecutive cards. To do this, we are going to create an array of 13 cells to represent the
        // range of card ranks from 1 to 13. We go through each card and increment the cell corresponding to
        // each card's rank. For example, an Ace (rank 1) would cause the first (index 0) cell to increment.
        // An 8 would cause the 8th (index 7) cell to increment. When this loop is done, the array will
        // contain 5 or less cells with values of 1 or more to represent the number of cards with each rank.
        // Then we can use this array to search for 3 or more consecutive non-zero values to represent a run.

        int n = set.getLength();

        if (n <= 2) return false; // Run must be at least 3 in length.

        int[] rankArr = new int[13];
        for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.

        for (int i = 0; i < n; i++) {
            rankArr[set.getElement(i).getRunRank()-1] += 1;
        }

        // Now search in the array for a sequence of n consecutive 1's.
        int streak = 0;
        int maxStreak = 0;
        for (int i = 0; i < 13; i++) {
            if (rankArr[i] == 1) {
                streak++;
                if (streak > maxStreak) maxStreak = streak;
            } else {
                streak = 0;
            }
        }
        if (maxStreak == n) { // Check if this is the maximum streak.
            return true;
        } else {
            return false;
        }

    }
}
