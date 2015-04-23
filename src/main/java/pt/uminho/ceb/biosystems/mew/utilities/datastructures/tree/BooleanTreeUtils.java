package pt.uminho.ceb.biosystems.mew.utilities.datastructures.tree;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BooleanTreeUtils {
	

	private static final String var = "[a-zA-Z0-9][a-zA-Z0-9\\._\\-]*[a-zA-Z0-9]";

	private static final Pattern notPattern = Pattern.compile("(?i)not\\s+(.+)");
	private static final Pattern parentesisPattern = Pattern.compile("\\((.*)\\)");
	private static final Pattern compPattern = Pattern.compile("(?i)\\s*("+var+")\\s+((and|or)\\s+(.+))+");
	private static final Pattern textPattern = Pattern.compile("\\s*("+var+")\\s*");

	
	public static boolean valExpression(String exp){
		
		Matcher notMatcher = notPattern.matcher(exp);
		Matcher parentesisMatcher = parentesisPattern.matcher(exp);
		Matcher compMatcher = compPattern.matcher(exp);
		Matcher textMatcher = textPattern.matcher(exp);
		boolean res = true;
		
		// group the expression at the same level
		ArrayList<String> subexps = tokens(exp);
		for(int i=0; i<subexps.size();i++){
			// evaluate each expression
//			System.out.println(subexps.get(i).substring(1, subexps.get(i).length()-1));
			res = res && valExpression(subexps.get(i).substring(1, subexps.get(i).length()-1));
			if(!res) return false;
			//replace the evaluated expression by string "exp"
			exp = exp.replace(subexps.get(i), " exp ");
			notMatcher = notPattern.matcher(exp);
			parentesisMatcher = parentesisPattern.matcher(exp);
			compMatcher = compPattern.matcher(exp);
			textMatcher = textPattern.matcher(exp);
		}
		if(notMatcher.matches()){
			return valExpression(notMatcher.group(1));			
		}
		else if(parentesisMatcher.matches()){
			return valExpression(parentesisMatcher.group(1));
		}
		else
			if(compMatcher.matches()){
//				System.out.println("Match comp; exp (AND|OR exp)+");
//				System.out.println("\t Match1-->"+compMatcher.group(1));
//				System.out.println("\t Match2-->"+compMatcher.group(4));
				return valExpression(compMatcher.group(1)) && valExpression(compMatcher.group(4));
			}
			else if(textMatcher.matches()){
				String str = textMatcher.group(1);
				if(str.equals("NOT")||str.equals("AND")||str.equals("OR"))
					return false;				
				return true;
			}
			
		return false;
	}
	
	// Return expressions at same level
	public static ArrayList<String>  tokens(String exp){
		ArrayList<String> array = new  ArrayList<String>();
		String subexp = "";
		int numPar = 0;
		int start=0;
		boolean open=false;
		for(int i=0; i< exp.length();i++){
			if(exp.charAt(i)=='('){
				open=true;
				if(numPar==0) start = i;
				numPar++;
			}
			else if(exp.charAt(i)==')'){
				numPar--;
			}
			if(numPar==0 && open){
				open=false;
				subexp = exp.substring(start, i+1);
				array.add(subexp);
			}			
		}
		return array;
	}
	
	
	
	
	public static void main(String[] args){

//		String rule = "(NOT (Gene1 OR gen*e.-zcx3)) AND (GAGAGA OR Gene2)";
		String rule = "3_1_3_45";	


		System.out.println("****************\nReturn "+valExpression(rule));

		
	}

}
