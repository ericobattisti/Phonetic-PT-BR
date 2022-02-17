# Phonetizer BR

Phonetizer BR is a JAVA library to deal with phonetization of words in Portuguese-Brazil.

<span style="color: blue">This solution can be used to improve the performance of database queries that use "LIKE".</span>

## Usage 1
The implementation below shows how to get the "phonetic text".

```java
import br.com.ebr.phonetizer.PhonetizerBR

public class PhonetizerTest {

	public static void main(String[] args) {
	
		String code1 = phonetizerBR.fonetizar("Rafaela Calleri");
		String code2 = phonetizerBR.fonetizar("Raphaela Kaleri");
		String code3 = phonetizerBR.fonetizar("Rafaella Kalery");
		
		System.out.println(code1);
		System.out.println(code2);
		System.out.println(code3);
		
	}

}
```
##### Console output:

```shell
RAFAILA KALIRI
RAFAILA KALIRI
RAFAILA KALIRI
```

## Usage 2
The implementation below shows how to get the "phonetic code".

```java
import br.com.ebr.phonetizer.PhonetizerBR

public class PhonetizerTest {

	public static void main(String[] args) {
	
		String code1 = phonetizerBR.makePhoneticCode("Rafaela Calleri");
		String code2 = phonetizerBR.makePhoneticCode("Raphaela Kaleri");
		
		System.out.println(code1);
		System.out.println(code2);
		
	}

}
```
##### Console output:

```shell
e93baf6f76
e93baf6f76
```

## Usage 3
The implementation below shows how to validate "phonetic text".

```java
import br.com.ebr.phonetizer.PhonetizerBR

public class PhonetizerTest {

	public static void main(String[] args) {
	
		ArrayList<String> codes = phonetizerBR.makeAllPhoneticCodes("Rafaela Calleri França");

		String codeTest = phonetizerBR.makePhoneticCode("Raphaela Kaleri");

		System.out.println(codes);
		System.out.println(codeTest);
		System.out.println("Was the code found? " + codes.contains(codeTest));
		
	}

}
```
##### Console output:

```shell
[c5890ad79a, 24b2a597db, e93baf6f76, 5ca718e072, 223022b80d, 8159bd784e, 46e2c74fe9]
e93baf6f76
Was the code found? true
```

## License
[MIT](https://choosealicense.com/licenses/mit/)