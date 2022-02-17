package br.com.ebr.phonetizer.commons;

import java.util.ArrayList;

public class PhonetizeUtils {
	
	public static String phonetize(String str) {
        char[] foncmp = new char[256];
        char[] fonwrk = new char[256];
        char[] fonaux = new char[256];
        char[] fonfon = new char[256];
        int i, j, x, k, desloc, endfon, copfon, copmud, newmud; 
        i = 0;
        j = 0;
        
        str = removeMultiple(str);

        ArrayList<String> component = StringUtils.strToVector(str);

        for (desloc = 0; desloc < component.size(); desloc++) {
            for (i = 0; i < 256; i++) {
                fonwrk[i] = ' ';
                fonfon[i] = ' ';
            }

            foncmp = component.get(desloc).toCharArray();
            fonaux = foncmp;
            j = 0;

            if (component.get(desloc).length() == 1) {
                fonwrk[0] = foncmp[0];
                if (foncmp[0] == '_') {
                    fonwrk[0] = ' ';
                } else if ((foncmp[0] == 'E') || (foncmp[0] == '&') || (foncmp[0] == 'I')) {
                    fonwrk[0] = 'i';
                }
            } else {
                for (i = 0; i < component.get(desloc).length(); i++) {
                    if (foncmp[i] == '_') {
                        fonfon[i] = 'Y';
                    } else if (foncmp[i] == '&') {
                        fonfon[i] = 'i';
                    } else if ((foncmp[i] == 'E') || (foncmp[i] == 'Y') || (foncmp[i] == 'I')) {
                        fonfon[i] = 'i';
                    } else if ((foncmp[i] == 'O') || (foncmp[i] == 'U')) {
                        fonfon[i] = 'o';
                    } else if (foncmp[i] == 'A') {
                        fonfon[i] = 'a';
                    } else if (foncmp[i] == 'S') {
                        fonfon[i] = 's';
                    } else {
                        fonfon[i] = foncmp[i];
                    }
                }

                endfon = 0;
                fonaux = fonfon;

                if (fonaux[3] == ' ') {
                    if ((fonaux[0] == 'a') || (fonaux[0] == 'i') || (fonaux[0] == 'o')) {
                        endfon = 0;
                    } else if ((fonaux[1] == 'a') || (fonaux[1] == 'i') || (fonaux[1] == 'o')) {
                        endfon = 0;
                    } else if ((fonaux[2] == 'a') || (fonaux[2] == 'i') || (fonaux[2] == 'o')) {
                        endfon = 0;
                    } else {
                        endfon = 1;
                        fonwrk[0] = fonaux[0];
                        fonwrk[1] = fonaux[1];
                        fonwrk[2] = fonaux[2];
                    }
                }

                if (endfon != 1) {
                    for (i = 0; i < component.get(desloc).length(); i++) {
                        copfon = 0;
                        copmud = 0;
                        newmud = 0;

                        switch (fonaux[i]) {
                        case 'a': 
                            
                        	if ((fonaux[i + 1] == 's') || (fonaux[i + 1] == 'Z') || (fonaux[i + 1] == 'M') || (fonaux[i + 1] == 'N')) {
                                if (fonaux[i + 2] != ' ') {
                                    copfon = 1;
                                } else {
                                    fonwrk[j] = 'a';
                                    fonwrk[j + 1] = ' ';
                                    j++;
                                    i++;
                                }
                            } else {
                                copfon = 1;
                            }

                            break;

                        case 'B': 
                            copmud = 1;

                            break;

                        case 'C': 
                            x = 0;

                            if (fonaux[i + 1] == 'i') {
                                fonwrk[j] = 's';
                                j++;

                                break;
                            }

                            if ((fonaux[i + 1] == 'o') && (fonaux[i + 2] == 'i') && (fonaux[i + 3] == 's') && (fonaux[i + 4] == ' ')) {
                                fonwrk[j] = 'K';
                                fonwrk[j + 1] = 'a';
                                fonwrk[j + 2] = 'o';
                                i = i + 4;

                                break;
                            }

                            if (fonaux[i + 1] == 'T') {
                                break;
                            }

                            if (fonaux[i + 1] != 'H') {
                                fonwrk[j] = 'K';
                                newmud = 1;

                                if (fonaux[i + 1] == 'K') {
                                    i++;

                                    break;
                                } else {
                                    break;
                                }
                            }

                            if (fonaux[i + 1] == 'H') {
                                if (fonaux[i + 2] == 'i') {
                                    if ((fonaux[i + 3] == 'a') || (fonaux[i + 3] == 'i') || (fonaux[i + 3] == 'o')) {
                                        x = 1;
                                    } else if (fonaux[i + 3] == 'N') {
                                        if (fonaux[i + 4] == 'i') {
                                            if (fonaux[i + 5] == ' ') {
                                                x = 1;
                                            } else {
                                                ;
                                            }
                                        } else {
                                            ;
                                        }
                                    } else if (fonaux[i + 3] == 'T') {
                                        if (fonaux[i + 4] == 'i') {
                                            if (fonaux[i + 5] == ' ') {
                                                x = 1;
                                            }
                                        }
                                    }
                                }
                            }

                            if (x == 1) {
                                fonwrk[j] = 'K';
                                j++;
                                i++;

                                break;
                            }

                            if (j > 0) {
                                if (fonwrk[j - 1] == 's') {
                                    j--;
                                }
                            }

                            fonwrk[j] = 'X';
                            newmud = 1;
                            i++;

                            break;

                        case 'D': 
                            x = 0;

                            if (fonaux[i + 1] != 'o') {
                                copmud = 1;

                                break;
                            } else if (fonaux[i + 2] == 'R') {
                                if (i != 0) {
                                    x = 1;
                                } else {
                                    copfon = 1;
                                }
                            } else {
                                copfon = 1;
                            }

                            if (x == 1) {
                                if (fonaux[i + 3] == 'i') {
                                    if (fonaux[i + 4] == 's') {
                                        if (fonaux[i + 5] != ' ') {
                                            x = 0;
                                        } else {
                                            ;
                                        }
                                    } else {
                                        x = 0;
                                    }
                                } else if (fonaux[i + 3] == 'a') {
                                    if (fonaux[i + 4] != ' ') {
                                        if (fonaux[i + 4] != 's') {
                                            x = 0;
                                        } else if (fonaux[i + 5] != ' ') {
                                            x = 0;
                                        } else {
                                            ;
                                        }
                                    } else {
                                        ;
                                    }
                                } else {
                                    x = 0;
                                }
                            } else {
                                x = 0;
                            }

                            if (x == 1) {
                                fonwrk[j] = 'D';
                                fonwrk[j + 1] = 'o';
                                fonwrk[j + 2] = 'R';
                                i = i + 5;
                            } else {
                                copfon = 1;
                            }

                            break;

                        case 'F':
                            copmud = 1;

                            break;

                        case 'G': 
                            if (fonaux[i + 1] == 'o') {
                                if (fonaux[i + 2] == 'i') {
                                    fonwrk[j] = 'G';
                                    fonwrk[j + 1] = 'i';
                                    j += 2;
                                    i += 2;
                                } else {
                                    copmud = 1;
                                }
                            } else if (fonaux[i + 1] == 'L') {
                                if (fonaux[i + 2] == 'i') {
                                    if ((fonaux[i + 3] == 'a') || (fonaux[i + 3] == 'i') || (fonaux[i + 3] == 'o')) {
                                        fonwrk[j] = fonaux[i + 1];
                                        fonwrk[j + 1] = fonaux[i + 2];
                                        j += 2;
                                        i += 2;
                                    } else if (fonaux[i + 3] == 'N') {
                                        fonwrk[j] = fonaux[i + 1];
                                        fonwrk[j + 1] = fonaux[i + 2];
                                        j += 2;
                                        i += 2;
                                    } else {
                                        copmud = 1;
                                    }
                                } else {
                                    copmud = 1;
                                }
                            } else if (fonaux[i + 1] == 'N') {
                                if ((fonaux[i + 2] != 'a') && (fonaux[i + 2] != 'i') && (fonaux[i + 2] != 'o')) {
                                    copmud = 1;
                                } else {
                                    fonwrk[j] = 'N';
                                    fonwrk[j + 1] = 'i';
                                    j += 2;
                                    i++;
                                }
                            } else if (fonaux[i + 1] == 'H') {
                                if (fonaux[i + 2] == 'i') {
                                    fonwrk[j] = 'G';
                                    fonwrk[j + 1] = 'i';
                                    j += 2;
                                    i += 2;
                                } else {
                                    copmud = 1;
                                }
                            } else {
                                copmud = 1;
                            }

                            break;

                        case 'H': 
                            break;

                        case 'i': 
                            if (fonaux[i + 2] == ' ') {
                                if (fonaux[i + 1] == 's') {
                                    fonwrk[j] = 'i';

                                    break;
                                } else if (fonaux[i + 1] == 'Z') {
                                    fonwrk[j] = 'i';

                                    break;
                                }
                            }

                            if (fonaux[i + 1] != 'X') {
                                copfon = 1;
                            } else if (i != 0) {
                                copfon = 1;
                            } else if ((fonaux[i + 2] == 'a') || (fonaux[i + 2] == 'i') || (fonaux[i + 2] == 'o')) {
                                fonwrk[j] = 'i';
                                fonwrk[j + 1] = 'Z';
                                j += 2;
                                i++;

                                break;
                            } else if ((fonaux[i + 2] == 'C') || (fonaux[i + 2] == 's')) {
                                fonwrk[j] = 'i';
                                j++;
                                i++;

                                break;
                            } else {
                                fonwrk[j] = 'i';
                                fonwrk[j + 1] = 's';
                                j += 2;
                                i++;

                                break;
                            }

                            break;

                        case 'J': 
                            fonwrk[j] = 'G';
                            fonwrk[j + 1] = 'i';
                            j += 2;

                            break;

                        case 'K': 
                            if (fonaux[i + 1] != 'T') {
                                copmud = 1;
                            }

                            break;

                        case 'L': 
                            if ((fonaux[i + 1] == 'a') || (fonaux[i + 1] == 'i') || (fonaux[i + 1] == 'o')) {
                                copfon = 1;
                            } else if (fonaux[i + 1] != 'H') {
                                fonwrk[j] = 'o';
                                j++;

                                break;
                            } else if ((fonaux[i + 2] != 'a') && (fonaux[i + 2] != 'i') && (fonaux[i + 2] != 'o')) {
                                copfon = 1;
                            } else {
                                fonwrk[j] = 'L';
                                fonwrk[j + 1] = 'i';
                                j += 2;
                                i++;

                                break;
                            }

                            break;

                        case 'M': 
                            if (((fonaux[i + 1] != 'a') && (fonaux[i + 1] != 'i') && (fonaux[i + 1] != 'o')) || (fonaux[i + 1] == ' ')) {
                                fonwrk[j] = 'N';
                                j++;
                            } else {
                                copfon = 1;
                            }

                            break;

                        case 'N': 
                            if ((fonaux[i + 1] == 'G') && (fonaux[i + 2] == 'T')) {
                                fonaux[i + 1] = 'N';
                                copfon = 1;
                            } else if (fonaux[i + 1] == 'H') {
                                if ((fonaux[i + 2] != 'a') && (fonaux[i + 2] != 'i') && (fonaux[i + 2] != 'o')) {
                                    copfon = 1;
                                } else {
                                    fonwrk[j] = 'N';
                                    fonwrk[j + 1] = 'i';
                                    j += 2;
                                    i++;
                                }
                            } else {
                                copfon = 1;
                            }

                            break;

                        case 'o': 
                            if ((fonaux[i + 1] == 's') || (fonaux[i + 1] == 'Z')) {
                                if (fonaux[i + 2] == ' ') {
                                    fonwrk[j] = 'o';

                                    break;
                                } else {
                                    copfon = 1;
                                }
                            } else {
                                copfon = 1;
                            }

                            break;

                        case 'P': 
                            if (fonaux[i + 1] == 'H') {
                                fonwrk[j] = 'F';
                                i++;
                                newmud = 1;
                            } else {
                                copmud = 1;
                            }

                            break;

                        case 'Q': 
                            if (fonaux[i + 1] == 'o') {
                                if (fonaux[i + 2] == 'i') {
                                    fonwrk[j] = 'K';
                                    j++;
                                    i++;

                                    break;
                                }
                            }
                            fonwrk[j] = 'K';
                            j++;

                            break;

                        case 'R': 
                            copfon = 1;

                            break;

                        case 's': 
                            if (fonaux[i + 1] == ' ') {
                                break;
                            }

                            if ((fonaux[i + 1] == 'a') || (fonaux[i + 1] == 'i') || (fonaux[i + 1] == 'o')) {
                                if (i == 0) {
                                    copfon = 1;

                                    break;
                                } else if ((fonaux[i - 1] != 'a') && (fonaux[i - 1] != 'i') && (fonaux[i - 1] != 'o')) {
                                    copfon = 1;

                                    break;
                                } else if ((fonaux[i + 1] == 'o') && (fonaux[i + 2] == 'L') && (fonaux[i + 3] == ' ')) {
                                    copfon = 1;

                                    break;
                                } else {
                                    fonwrk[j] = 'Z';
                                    j++;

                                    break;
                                }
                            }

                            if (fonaux[i + 1] == 's') {
                                if (fonaux[i + 2] != ' ') {
                                    copfon = 1;
                                    i++;

                                    break;
                                } else {
                                    fonaux[i + 1] = ' ';
                                    
                                    break;
                                }
                            }

                            if (i == 0) {
                                if (!((fonaux[i + 1] == 'C') && (fonaux[i + 2] == 'i'))) {
                                    if (fonaux[i + 1] != 'H') {
                                        if (!((fonaux[i + 1] == 'C') && (fonaux[i + 2] == 'H') && ((fonaux[i + 3] != 'a') && (fonaux[i + 3] != 'i') && (fonaux[i + 3] != 'o')))) {
                                            fonwrk[j] = 'i';
                                            j++;
                                            copfon = 1;

                                            break;
                                        }
                                    }
                                }
                            }

                            if (fonaux[i + 1] == 'H') {
                                fonwrk[j] = 'X';
                                i++;
                                newmud = 1;

                                break;
                            }

                            if (fonaux[i + 1] != 'C') {
                                copfon = 1;

                                break;
                            }

                            if (fonaux[i + 2] == 'H') {
                                fonwrk[j] = 'X';
                                i += 2;
                                newmud = 1;

                                break;
                            }

                            if (fonaux[i + 2] != 'i') {
                                copfon = 1;

                                break;
                            }

                            if (fonaux[i + 3] == ' ') {
                                fonwrk[j] = 'X';
                                fonwrk[j + 1] = 'i';
                                i = i + 3;

                                break;
                            }

                            if ((fonaux[i + 3] == 'a') || (fonaux[i + 3] == 'i') || (fonaux[i + 3] == 'o')) {
                                fonwrk[j] = 'X';
                                j++;
                                i += 2;

                                break;
                            }

                            fonwrk[j] = 's';
                            fonwrk[j + 1] = 'i';
                            j += 2;
                            i += 2;

                            break;

                        case 'T': 
                            if (fonaux[i + 1] == 's') {
                                break;
                            } else if (fonaux[i + 1] == 'Z') {
                                break;
                            } else {
                                copmud = 1;
                            }

                            break;

                        case 'V': 
                        case 'W': 
                            if ((fonaux[i + 1] == 'a') || (fonaux[i + 1] == 'i') || (fonaux[i + 1] == 'o')) {
                                if (i == 0) {
                                    fonwrk[j] = 'o';
                                    j++;
                                } else {
                                    fonwrk[j] = 'V';
                                    newmud = 1;
                                }
                            } else {
                                fonwrk[j] = 'V';
                                newmud = 1;
                            }

                            break;

                        case 'X': 
                            copmud = 1;

                            break;

                        case 'Y': 
                        	
                            break;

                        case 'Z': 
                            if (fonaux[i + 1] == ' ') {
                                break;
                            } else if ((fonaux[i + 1] == 'a') || (fonaux[i + 1] == 'i') || (fonaux[i + 1] == 'o')) {
                                copfon = 1;
                            } else {
                                fonwrk[j] = 's';
                                j++;
                            }

                            break;

                        default: 
                            fonwrk[j] = fonaux[i];
                            j++;

                            break;
                        }

                        if (copfon == 1) {
                            fonwrk[j] = fonaux[i];
                            j++;
                        }
                        
                        if (copmud == 1) {
                            fonwrk[j] = fonaux[i];
                        }

                        if ((copmud == 1) || (newmud == 1)) {
                            j++;
                            k = 0;

                            while (k == 0) {
                                if (fonaux[i + 1] == ' ') {
                                    fonwrk[j] = 'i';
                                    k = 1;
                                } else if ((fonaux[i + 1] == 'a') || (fonaux[i + 1] == 'i') || (fonaux[i + 1] == 'o')) {
                                    k = 1;
                                } else if (fonwrk[j - 1] == 'X') {
                                    fonwrk[j] = 'i';
                                    j++;
                                    k = 1;
                                } else if (fonaux[i + 1] == 'R') {
                                    k = 1;
                                } else if (fonaux[i + 1] == 'L') {
                                    k = 1;
                                } else if (fonaux[i + 1] != 'H') {
                                    fonwrk[j] = 'i';
                                    j++;
                                    k = 1;
                                } else {
                                    i++;
                                }
                            }
                        }
                    }
                }
            }

            for (i = 0; i < component.get(desloc).length() + 3; i++) {
                if (fonwrk[i] == 'i') {
                    fonwrk[i] = 'I';
                } else if (fonwrk[i] == 'a') {
                    fonwrk[i] = 'A';
                } else if (fonwrk[i] == 'o') {
                    fonwrk[i] = 'U';
                } else if (fonwrk[i] == 's') {
                    fonwrk[i] = 'S';
                } else if (fonwrk[i] == 'E') {
                    fonwrk[i] = ' ';
                } else if (fonwrk[i] == 'Y') {
                    fonwrk[i] = '_';
                }
            }

            component.set(desloc, String.copyValueOf(fonwrk));
            j = 0;
        }

        str = StringUtils.vectorToStr(component);
        str = removeMultiple(str);
        return str.toUpperCase().trim();
    }

	private static String removeMultiple(String str) {
	    char[] foncmp = new char[256];
	    char[] fonaux = new char[256];
	    char[] tip = new char[1];
	    int i, j;
	
	    j = 0;	
	    tip[0] = ' ';
	
	    fonaux = str.toCharArray();
	
	    for (i = 0; i < str.length(); i++) {	
	        if ((fonaux[i] != tip[0]) || (fonaux[i] == ' ') || ((fonaux[i] >= '0') && (fonaux[i] <= '9')) || ((fonaux[i] == 'S') && (fonaux[i - 1] == 'S') && ((i > 1) && (fonaux[i - 2] != 'S')))) {	
	            foncmp[j] = fonaux[i];	
	            j++;	
	        }
	        tip[0] = fonaux[i];
	    }
	    str = String.copyValueOf(foncmp);
	    return str.trim();
	}

}
