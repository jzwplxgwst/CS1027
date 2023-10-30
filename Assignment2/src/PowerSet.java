/**
 * This class represents the Power Set from a given set. This class must also work with the
 * generic type T.
 *
 * Class: ICS4U1
 * Date: Feb 27 2023
 * @author James Wong
 */
public class PowerSet <T>{

    /** set with generic type T */
    private Set <T>[] set;

    /**
     * PowerSet
     * @param elements T array of elements
     */
    public PowerSet(T[] elements){

        String string;      // create string variable
        set = (Set<T>[]) new Set [(int)Math.pow(2, elements.length)];   // create a set

        for (int i=0; i<set.length; i++){   // loop through the length of the set
            string = Integer.toBinaryString(i);     // parse integer to string

            for (int j=string.length(); j<elements.length; j++) {   // loop through the length of elements
                string = "0" + string;      // padding the string value
            }

            Set<T> tempSet = new Set<T>();  // create a temporary set
            for (int k = 0; k < string.length(); k++){  // loop through the length of the string
                if (string.charAt(k) == '1') {
                    tempSet.add(elements[k]);   // add elements to the temporary set
                }
            }
            set[i] = tempSet;       // make the temporary set equal to the original set
        }
    }

    /**
     * getLength
     * @return set.length returns the length of the set
     */
    public int getLength(){
        return set.length;
    }

    /**
     * getSet
     * @param i ith key of the set
     * @return set[i] the set at position i
     */
    public Set<T> getSet(int i){
        return set[i];
    }
}
