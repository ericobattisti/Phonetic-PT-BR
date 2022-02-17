package br.com.ebr.phonetizer.generator;

import java.util.ArrayList;

import br.com.ebr.phonetizer.commons.StringUtils;

public class CodeGenerator {
	
	public static String randomize(String str) {
		long fon09 = 0;
		long fon11 = 0;
		long fon12 = 0;

		char[] reg09 = new char[4];
		char[] reg11 = new char[4];
		char[] reg12 = new char[4];

		char[] fonrnd = new char[5];
		char[] finalRand = new char[10];

		char[] work = new char[2]; 
		int i, j, k; 
		int w0, w1; 

		char[] foncmp = new char[256];
		char[] fonaux = new char[256];

		String auxStr = "";

		fon09 = 0;
		fon11 = 0;
		fon12 = 0;

		ArrayList<String> component = StringUtils.strToVector(str);

		for (i = 0; i < component.size(); i++) {
			auxStr = component.get(i);
			foncmp = auxStr.toCharArray();
			if (foncmp[0] != ' ') {
				for (j = 0; j < 256; j++) {
					fonaux[j] = ' '; 
				}
				
				j = 0;
				
				while (j < auxStr.length()) {
					if ((foncmp[0] == 'I') || (foncmp[0] == 'A')
							|| (foncmp[0] == 'U')) {
						fonaux[0] = 'R';
						for (j = 0; j < auxStr.length(); j++)
							fonaux[j + 1] = foncmp[j];
					} else {
						if ((foncmp[0] == 'G') && (auxStr.length() > 1)) {
							if (foncmp[1] == 'I') {
								for (j = 0; j < auxStr.length() - 1; j++)
									fonaux[j] = foncmp[j + 1];
								j++;
							} else {
								for (j = 0; j < auxStr.length(); j++)
									fonaux[j] = foncmp[j];
							}
						} else {
							for (j = 0; j < auxStr.length(); j++) {
								fonaux[j] = foncmp[j];
							}
						}
					}
				}

				auxStr = String.copyValueOf(fonaux).trim();
				foncmp = auxStr.toCharArray();

				for (j = 0; j < 256; j++)
					fonaux[j] = ' ';

				j = 0;
				k = 0;
				while (j < auxStr.length()) {
					if ((j + 2 == auxStr.length()) && (j != 0) && ((foncmp[j] == 'B') || (foncmp[j] == 'D')	|| (foncmp[j] == 'F') || (foncmp[j] == 'G') || (foncmp[j] == 'J') || (foncmp[j] == 'P')	|| (foncmp[j] == 'K') || (foncmp[j] == 'T') || (foncmp[j] == 'V'))) {
						if (foncmp[j + 1] == 'I') {
							j = j + 2;
						} else {
							fonaux[k] = foncmp[j];
							j++;
							k++;
						}
					} else if ((j + 3 <= auxStr.length()) && ((foncmp[j] == 'N') || (foncmp[j] == 'L'))) {
						if ((foncmp[j + 1] == 'I') && ((foncmp[j + 2] == 'A') || (foncmp[j + 2] == 'E') || (foncmp[j + 2] == 'O') || (foncmp[j + 2] == 'U'))) {
							fonaux[k] = foncmp[j];
							fonaux[k + 1] = foncmp[j + 2];
							j = j + 3;
							k = k + 2;
						} else {
							fonaux[k] = foncmp[j];
							j++;
							k++;
						}
					} else if ((foncmp[j] == 'R') && (j > 0)) {
						if ((foncmp[j - 1] != 'A') && (foncmp[j - 1] != 'E') && (foncmp[j - 1] != 'I') && (foncmp[j - 1] != 'O') && (foncmp[j - 1] != 'U')) {
							j++;
						} else {
							fonaux[k] = foncmp[j];
							j++;
							k++;
						}
					} else {
						fonaux[k] = foncmp[j];
						j++;
						k++;
					}
				}

				auxStr = String.copyValueOf(fonaux).trim();
				foncmp = auxStr.toCharArray();
				
				for (j = 0; j < 256; j++) {
					fonaux[j] = ' ';
				}

				for (j = 0; j < auxStr.length(); j++) {
					if (foncmp[j] == 'V') {
						fonaux[j] = 'F';
					} else if ((foncmp[j] == 'X') || (foncmp[j] == 'Z') || (foncmp[j] == 'K')) {
						fonaux[j] = 'S';
					} else if (foncmp[j] == 'G') {
						fonaux[j] = 'D';
					} else {
						fonaux[j] = foncmp[j];
					}
				}
			}

			component.set(i, String.copyValueOf(fonaux).trim());
		}

		for (i = 0; i < component.size(); i++) {
			auxStr = component.get(i);

			if (auxStr.length() > 7) {
				foncmp = auxStr.toCharArray();
				
				for (j = 7; j < auxStr.length(); j++) {
					foncmp[j] = ' ';
				}
				
				auxStr = String.valueOf(foncmp).trim();
				component.set(i, auxStr);
			}

			fon11 += randomic(tabEbc(auxStr));
			fon12 += randomic(tabNor(tabEbc(auxStr)));
		}

		component = StringUtils.strToVector(str);
		for (i = 0; i < component.size(); i++) {
			fon09 += randomic(component.get(i));
		}

		reg09 = fonreg(fon09);
		reg11 = fonreg(fon11);
		reg12 = fonreg(fon12);
		fonrnd[0] = reg12[2];
		fonrnd[1] = reg11[1];
		fonrnd[2] = reg11[2];
		if ((fonrnd[0] == 0) && (fonrnd[1] == 0) && (fonrnd[2] == 0)) {
			fonrnd[0] = reg12[1];
			fonrnd[1] = reg11[0];
			fonrnd[2] = reg11[3];
		}
		fonrnd[3] = reg09[1];
		fonrnd[4] = reg09[2];
		if ((fonrnd[3] == 0) && (fonrnd[4] == 0)) {
			fonrnd[3] = reg09[0];
			fonrnd[4] = reg09[3];
			if ((fonrnd[3] == 0) && (fonrnd[4] == 0)) {
				fon09 = fon11 + fon12;
				reg09 = fonreg(fon09);
				fonrnd[3] = reg09[1];
				fonrnd[4] = reg09[2];
			}
		}

		j = 0;
		for (i = 0; i < 5; i++) {
			auxStr = String.valueOf(fonrnd[i]);
			w0 = (int) fonrnd[i];
			w0 = w0 >>> 4;
			work[0] = (char) w0;
			
			if (work[0] <= '\u0009') {
				finalRand[j] = (char) ((int) work[0] + 48);
			} else {
				finalRand[j] = (char) ((int) work[0] - 10 + 97);
			} 
			
			w1 = (int) fonrnd[i];
			w1 = w1 << 28;
			w0 = w1 >>> 28;
			work[0] = (char) w0;
			
			if (work[0] <= '\u0009') {
				finalRand[j + 1] = (char) ((int) work[0] + 48);
			} else {
				finalRand[j + 1] = (char) ((int) work[0] - 10 + 97);
			}
			
			j += 2;
		}

		return String.valueOf(finalRand);
	}

	private static long randomic(String str) {
		int i;
		long i01, i02;
		char[] fonaux = new char[256];

		i01 = 0;

		if (str.length() > 1) {
			fonaux = str.toCharArray();
			i01 = fonaux[0] * 0x0100 + fonaux[1];
			for (i = 1; i < 256; i++) {
				if (i == (str.length() - 1))
					break;
				i02 = (fonaux[i] * 0x0100) + fonaux[i + 1];
				i01 *= i02;
				i01 = i01 >>> 8;
			}
		} else {
			fonaux = str.toCharArray();
			i01 = fonaux[0] * 0x0100;
			i01 = i01 >>> 8;
		}
		return i01;
	}

	private static char[] fonreg(long i03) {
		long i01, i02;
		char[] fonaux = new char[4];

		i02 = i03;
		fonaux[3] = (char) (i02 % 0x0100);
		i01 = (i02 - fonaux[3]) / 0x0100;
		fonaux[2] = (char) (i01 % 0x0100);
		i02 = (i01 - fonaux[2]) / 0x0100;
		fonaux[1] = (char) (i02 % 0x0100);
		i01 = (i02 - fonaux[1]) / 0x0100;
		fonaux[0] = (char) (i01 % 0x0100);

		return fonaux;
	}
	
    private static String tabEbc (String str) {
        char[] fonaux = new char [256];
        int i;

        fonaux = str.toCharArray();
        for (i = 0; i < str.length(); i++)
          switch (fonaux[i]) {
            case 'A': fonaux[i] = '\u00c1';
                    break;
            case 'B': fonaux[i] = '\u00c2';
                    break;
            case 'C': fonaux[i] = '\u00c3';
                    break;
            case 'D': fonaux[i] = '\u00c4';
                    break;
            case 'E': fonaux[i] = '\u00c5';
                    break;
            case 'F': fonaux[i] = '\u00c6';
                    break;
            case 'G': fonaux[i] = '\u00c7';
                    break;
            case 'H': fonaux[i] = '\u00c8';
                    break;
            case 'I': fonaux[i] = '\u00c9';
                    break;
            case 'J': fonaux[i] = '\u00d1';
                    break;
            case 'K': fonaux[i] = '\u00d2';
                    break;
            case 'L': fonaux[i] = '\u00d3';
                    break;
            case 'M': fonaux[i] = '\u00d4';
                    break;
            case 'N': fonaux[i] = '\u00d5';
                    break;
            case 'O': fonaux[i] = '\u00d6';
                    break;
            case 'P': fonaux[i] = '\u00d7';
                    break;
            case 'Q': fonaux[i] = '\u00d8';
                    break;
            case 'R': fonaux[i] = '\u00d9';
                    break;
            case 'S': fonaux[i] = '\u00e2';
                    break;
            case 'T': fonaux[i] = '\u00e3';
                    break;
            case 'U': fonaux[i] = '\u00e4';
                    break;
            case 'V': fonaux[i] = '\u00e5';
                    break;
            case 'W': fonaux[i] = '\u00e6';
                    break;
            case 'X': fonaux[i] = '\u00e7';
                    break;
            case 'Y': fonaux[i] = '\u00e8';
                    break;
            case 'Z': fonaux[i] = '\u00e9';
                    break;
            case '0': fonaux[i] = '\u00f0';
                    break;
            case '1': fonaux[i] = '\u00f1';
                    break;
            case '2': fonaux[i] = '\u00f2';
                    break;
            case '3': fonaux[i] = '\u00f3';
                    break;
            case '4': fonaux[i] = '\u00f4';
                    break;
            case '5': fonaux[i] = '\u00f5';
                    break;
            case '6': fonaux[i] = '\u00f6';
                    break;
            case '7': fonaux[i] = '\u00f7';
                    break;
            case '8': fonaux[i] = '\u00f8';
                    break;
            case '9': fonaux[i] = '\u00f9';
                    break;
            default:  fonaux[i] = '\u0040';
        }
        str = String.copyValueOf(fonaux);

        return str;
      }

	private static String tabNor (String str) {	
	     char[] fonaux = new char [256];
	     int i;
	
	     fonaux = str.toCharArray();
	     for (i = 0; i < str.length(); i++)
	       switch (fonaux[i]) {
	         case '\u00c1': fonaux[i] = '\u0013';
	                     break;
	         case '\u00c2': fonaux[i] = '\u0016';
	                     break;
	         case '\u00c3': fonaux[i] = '\u0019';
	                     break;
	         case '\u00c4': fonaux[i] = '\u001c';
	                     break;
	         case '\u00c5': fonaux[i] = '\u0011';
	                     break;
	         case '\u00c6': fonaux[i] = '\u0014';
	                     break;
	         case '\u00c7': fonaux[i] = '\u0017';
	                     break;
	         case '\u00c8': fonaux[i] = '\u001a';
	                     break;
	         case '\u00c9': fonaux[i] = '\u001d';
	                     break;
	         case '\u00d1': fonaux[i] = '\u0033';
	                     break;
	         case '\u00d2': fonaux[i] = '\u0036';
	                     break;
	         case '\u00d3': fonaux[i] = '\u0039';
	                     break;
	         case '\u00d4': fonaux[i] = '\u003c';
	                     break;
	         case '\u00d5': fonaux[i] = '\u0031';
	                     break;
	         case '\u00d6': fonaux[i] = '\u0034';
	                     break;
	         case '\u00d7': fonaux[i] = '\u0037';
	                     break;
	         case '\u00d8': fonaux[i] = '\u003a';
	                     break;
	         case '\u00d9': fonaux[i] = '\u003d';
	                     break;
	         case '\u00e2': fonaux[i] = '\u0053';
	                     break;
	         case '\u00e3': fonaux[i] = '\u0056';
	                     break;
	         case '\u00e4': fonaux[i] = '\u0059';
	                     break;
	         case '\u00e5': fonaux[i] = '\u005c\';
	                     break;
	         case '\u00e6': fonaux[i] = '\u0054';
	                     break;
	         case '\u00e7': fonaux[i] = '\u0057';
	                     break;
	         case '\u00e8': fonaux[i] = '\u005a';
	                     break;
	         case '\u00e9': fonaux[i] = '\u005d';
	                     break;
	         case '\u00f0': fonaux[i] = '\u0070';
	                     break;
	         case '\u00f1': fonaux[i] = '\u0071';
	                     break;
	         case '\u00f2': fonaux[i] = '\u0072';
	                     break;
	         case '\u00f3': fonaux[i] = '\u0073';
	                     break;
	         case '\u00f4': fonaux[i] = '\u0074';
	                     break;
	         case '\u00f5': fonaux[i] = '\u0075';
	                     break;
	         case '\u00f6': fonaux[i] = '\u0076';
	                     break;
	         case '\u00f7': fonaux[i] = '\u0077';
	                     break;
	         case '\u00f8': fonaux[i] = '\u0078';
	                     break;
	         case '\u00f9': fonaux[i] = '\u0079';
	                     break;
	         default      : fonaux[i] = '\u0040';
	       }
	
	       str = String.copyValueOf(fonaux);
	
	       return str;
	   }
}
