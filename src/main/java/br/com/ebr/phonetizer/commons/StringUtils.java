package br.com.ebr.phonetizer.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringUtils {
	
	public static String replaceNumbers(String str) {
		int soma = 0;
		ArrayList<String> palavras = StringUtils.strToVector(str);
		
		for (int i = 0; i < palavras.size(); i++) {
			if ("E".equals(palavras.get(i))) {
				palavras.remove(i);
				i--;
			}

			else {
				if ("MIL".equals(palavras.get(i))) {
					if (soma == 0)
						soma = 1000;
					else {
						soma = soma * 1000;
						palavras.remove(i);
						i--;
					}
				} else {
					Integer valor = null;
					try {
						valor = Integer.parseInt(palavras.get(i).toString());
					} catch (NumberFormatException e) {}
					if (valor != null) {
						if (soma != 0) {
							palavras.remove(i - 1);
							i--;
						}
						soma += valor;
					} else {
						if (soma != 0) {
							palavras.set(i-1, ""+soma);
						}
						soma = 0;
					}
				}
			}
		}
		
		if(soma != 0){
			palavras.set(palavras.size()-1,""+soma);
		}
		
		return StringUtils.vectorToStr(palavras);
	}
	
	public static String strangeRemover(String str) {
		char[] foncmp = new char[256];
		char[] fonaux = new char[256];
		int i, j, first; 
		j = 0;
		first = 1;
		fonaux = str.toCharArray();

		for (i = 0; i < 256; i++) {
			foncmp[i] = ' ';
		}

		for (i = 0; i < str.length(); i++) {
			if (((fonaux[i] >= 'A') && (fonaux[i] <= 'Z')) ||
			((fonaux[i] >= 'a') && (fonaux[i] <= 'z')) ||
			((fonaux[i] >= '0') && (fonaux[i] <= '9')) ||
			(fonaux[i] == '&') || (fonaux[i] == '_') ||
			((fonaux[i] == ' ') && (first == 0))) {
				foncmp[j] = fonaux[i];
				j++;
				first = 0;
			}
		}
		str = String.valueOf(foncmp);
		return str.trim();
	}
	
	public static String replaceChars(String str, Map<Character, Character> map){
        char aux[] = str.toCharArray();
        int i;
        for (i = 0; i < aux.length; i++) {
        	Character rep = map.get(aux[i]);
        	if(rep != null){
        		aux[i] = rep;
        	}
        }
        str = String.copyValueOf(aux).trim();
        return str;
	}
	
	public static String removeStrings(String str, Set<String> toRemove) {
        ArrayList<String> palavra = StringUtils.strToVector(str);
        for(int i=0; i<palavra.size(); ){
        	if(toRemove.contains(palavra.get(i))){
        		palavra.remove(i);
        	} else {
        		i++;
        	}
        }
        return StringUtils.vectorToStr(palavra);
    }
	
	public static String replaceStrings(String str, Map<String, String> map){
		ArrayList<String> palavras = StringUtils.strToVector(str);
		for(int i=0; i<palavras.size(); i++){
			String rep = map.get(palavras.get(i).toString());
			if(rep != null){
				palavras.set(i, rep);
			}
		}
		return StringUtils.vectorToStr(palavras);
	}

	public static ArrayList<String> strToVector(String str) {
		str = str.trim();
		char[] fonaux = new char[256];
		char[] foncmp = new char[256];

		ArrayList<String> component = new ArrayList<String>();

		int i, j, pos, rep, first;
		first = 1;
		pos = 0;
		rep = 0;

		fonaux = str.toCharArray();

		for (j = 0; j < 256; j++) {
			foncmp[j] = ' ';
		}

		for (i = 0; i < str.length(); i++) {
			if ((fonaux[i] == ' ') && (first != 1)) {
				if (rep == 0) {
					component.add(String.copyValueOf(foncmp).trim());
					pos = 0;
					rep = 1;
					for (j = 0; j < 256; j++) {
						foncmp[j] = ' ';
					}
				}
			} else {
				foncmp[pos] = fonaux[i];
				first = 0;
				pos++;
				rep = 0;
			}
		}

		if (foncmp[0] != ' ') {
			component.add(String.copyValueOf(foncmp).trim());
		}

		return component;
	}

	public static String vectorToStr(ArrayList<String> vtr) {
		char[] foncmp = new char[256];
		char[] auxChar = new char[256];
		String auxStr = new String();
		String str = new String();
		int i, j, desloc;
		desloc = 0;
		
		for (i = 0; i < 256; i++) {
			foncmp[i] = ' ';
		}

		for (j = 0; j < vtr.size(); j++) {
			auxStr = (vtr.get(j)).trim();
			auxChar = auxStr.toCharArray();

			for (i = 0; i < auxStr.length(); i++) {
				foncmp[desloc + i] = auxChar[i];
			}
			desloc = desloc + auxStr.length() + 1;
		}
		str = String.valueOf(foncmp);
		return str.trim();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map toMap(Object[][] array) {
		Map m = new HashMap();
		for(Object[] pair : array){
			m.put(pair[0], pair[1]);
		}
		return m;
	}

	public static Set<String> toSet(String[] strings) {
		Set<String> result = new HashSet<String>();
		for(String s : strings){
			result.add(s);
		}
		return result;
	}

}
