package csen1002.main.task5;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Write your info here
 * 
 * @name Mostafa Mohamed Abdelnasser
 * @id 43-8530
 * @labNumber 11
 */
public class CFG {
	/**
	 * CFG constructor
	 * 
	 * @param description is the string describing a CFG
	 */
	HashMap<String, ArrayList> CFG = new HashMap<String, ArrayList>();
	ArrayList<String> varIndex = new ArrayList<String>();
	public CFG(String description) {
		String[] rules = description.split(";");
		for(String rule : rules){
			//Start parsing productions
			String[] productions = rule.split(",");
			//First string is the variable name
			String var = productions[0];
			varIndex.add(var);
			//Initialize the arraylist of productions
			ArrayList<String> productionsList = new ArrayList<String>();

			//Loop on the parsed productions and add them to the list
			for(int i = 1;i < productions.length;i++){
				String production = productions[i];
				productionsList.add(production);
			}
			//Add the list to the CFG HashMap
			CFG.put(var,productionsList);
		}
	}

	/**
	 * Returns a string of elimnated left recursion.
	 * 
	 * @paraminput is the string to simulate by the CFG.
	 * @return string of elimnated left recursion.
	 */
	public String lre() {
		boolean addedSymbol = false;
		for(int i = 0;i < varIndex.size();i++){
			if(addedSymbol){
				addedSymbol = false;
				continue;
			}
			String vari = varIndex.get(i);
			ArrayList<String> listi = CFG.get(vari);

			for(int j = 0;j < i;j++){

				String varj = varIndex.get(j);
				ArrayList<String> listj = CFG.get(varj);

				//Replacing productions pointing from i to j
				ArrayList<String> newiList = new ArrayList<String>();
				for(String production : (ArrayList<String>)CFG.get(vari)){
					//Check if the production starts with the j variable
					if((production.charAt(0)+"").equals(varj)){
						//Start replacing
						for(String jProduction : (ArrayList<String>)CFG.get(varj)){
							//Create the new production
							String newProduction = production.substring(1);
							newProduction = jProduction + newProduction;
							//Add it to the new list
							newiList.add(newProduction);
						}
					}
					else{
						//The production will not be changed
						newiList.add(production);
					}
				}
				//Replace the old list with the new one
				CFG.remove(vari);
				CFG.put(vari,newiList);
			}
			//Elimination of immediate LR
			//Initialize lists of alphas, the betas will remain in the old variable
			ArrayList<String> alpha = new ArrayList<String>();
			//Check if the symbol needs elimination
			boolean elim = false;
			for (String production : (ArrayList<String>)CFG.get(vari)){
				if((production.charAt(0)+"").equals(vari)){
					elim = true;
				}
			}
			//Get alphas & betas
			if(elim){
				ArrayList<String> newList = new ArrayList<String>();
				for(String production : (ArrayList<String>)CFG.get(vari)){
					if((production.charAt(0)+"").equals(vari)){
						//That's an alpha,
						//so we remove it from the list and add it
						//to the alpha list to our new variable
						alpha.add(production.substring(1)+vari+"\'");
					}
					else
					{
						//That's a beta,
						//so we just add our new variable
						//in the end of the production
						newList.add(production + vari + "\'");
					}
				}
				//Update listi
				CFG.remove(vari);
				CFG.put(vari,newList);
				//Don't forget the epsilon
				alpha.add("e");
				//Add the alpha list to the HashSet
				CFG.put(vari+"\'",alpha);
				//Now add the new variable next to the original
				varIndex.add(varIndex.get(varIndex.size()-1));
				for(int l=varIndex.size()-2;l >= i+1;l--){
					//Shift the entries
					varIndex.set(l+1,varIndex.get(l));
				}
				varIndex.set(i+1,vari+"\'");
				addedSymbol = true;
			}
		}
		//Formulate the output
		String out = "";
		for(String var : varIndex){
			ArrayList<String> productions = CFG.get(var);
			out += var;
			for(String production : productions){
				out += "," + production;
			}
			out += ";";
		}
		return out.substring(0,out.length()-1);
	}
}
