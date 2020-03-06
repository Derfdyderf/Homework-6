import java.util.*;

public class MyMiniSearchEngine {
    // default solution. OK to change.
    // do not change the signature of index()
    /*
    instead of using a list of lists to track the document and location, i
    used another map with the document as the key and single list for locations.
    lists of lists are still a bit confusing for me, so this seemed a bit easier
    */
    private Map<String, Map<Integer, List<Integer>>> indexes;

    // disable default constructor
    private MyMiniSearchEngine() {
    }

    public MyMiniSearchEngine(List<String> documents) {
        index(documents);
    }

    // each item in the List is considered a document.
    // assume documents only contain alphabetical words separated by white spaces.
    private void index(List<String> texts) {

        indexes = new HashMap<String, Map<Integer, List<Integer>>>();
        for (int i = 0; i < texts.size(); i++) {

            Map<String, List<Integer>> secondary = new HashMap<String, List<Integer>>();
            String[] words = texts.get(i).split(" ");


            for (int j = 0; j < words.length; j++) {

                if (!(secondary.containsKey(words[j])))
                    secondary.put(words[j], new ArrayList<Integer>());
                if (!(indexes.containsKey(words[j])))
                    indexes.put(words[j], new HashMap<Integer, List<Integer>>());
                if (secondary.containsKey(words[j]))
                    secondary.get(words[j]).add(j);
            }


            for (int j = 0; j < words.length; j++) {
                if (indexes.containsKey(words[j]))
                    indexes.get(words[j]).put(i, secondary.get(words[j]));
            }
        }
        System.out.println(indexes);

    }

    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    /*
    I set it to return -1 on no match
     */
    public List<Integer> search(String keyPhrase) {
        String[] words = keyPhrase.split(" ");



        List<Integer> doc = new ArrayList<>();
        List<List<Integer>> w1 = new ArrayList<>();
        int foundVal = 0;


        for (int i = 0; i < words.length; i++) {
            if (indexes.containsKey(words[i])) {

                for (int key : indexes.get(words[i]).keySet()) {
                    doc.add(key);
                    foundVal++;
                }

            } else {
                List<Integer> fail = new ArrayList<>();
                fail.add(-1);
                return fail;
            }



        }

        System.out.println(doc);
        return doc;
    }
}
