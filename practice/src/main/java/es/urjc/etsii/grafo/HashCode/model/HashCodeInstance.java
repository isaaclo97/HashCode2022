package es.urjc.etsii.grafo.HashCode.model;

import es.urjc.etsii.grafo.io.Instance;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class HashCodeInstance extends Instance {

    int C;
    HashSet<String> ingredients = new HashSet<>();
    ArrayList<ArrayList<String>> ingredient_per_client = new ArrayList<>();
    ArrayList<ArrayList<String>> ingredient_dislike_per_client = new ArrayList<>();
    public HashCodeInstance(String name, BufferedReader br) {
        super(name);
        try {
            C = Integer.parseInt(br.readLine());
            for (int i = 0; i < C * 2; i++) {
                ArrayList<String> ingredient = new ArrayList<>();
                if(i%2==0) {
                    ingredient_per_client.add(ingredient);
                    String[] ing = br.readLine().split(" ");
                    int total = Integer.parseInt(ing[0]);
                    for (int j = 1; j <= total; j++) {
                        ingredient_per_client.get(i/2).add(ing[j]);
                        ingredients.add(ing[j]);
                    }
                }
                else{
                    ingredient_dislike_per_client.add(ingredient);
                    String[] ing = br.readLine().split(" ");
                    int total = Integer.parseInt(ing[0]);
                    for (int j = 1; j <= total; j++) {
                        ingredient_dislike_per_client.get(i/2).add(ing[j]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getC() {
        return C;
    }

    public ArrayList<ArrayList<String>> getIngredient_per_client() {
        return ingredient_per_client;
    }

    public ArrayList<ArrayList<String>> getIngredient_dislike_per_client() {
        return ingredient_dislike_per_client;
    }

    public HashSet<String> getIngredients() {
        return ingredients;
    }
}
