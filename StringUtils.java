
public class StringUtils {
	/**
	 * recursive solution
	 * @param str
	 * @return
	 */
	public static String reverse(String str){
		if ((null == str) || (str.length()  <= 1)) {
			return str;
		}
		return reverse(str.substring(1)) + str.charAt(0);
	}
	/**
	 * using XOR
	 * 
	 * 
<pre>
XOR (Exclusive OR) table
0 XOR 0 = 0
0 XOR 1 = 1
1 XOR 0 = 1
1 XOR 1 = 0
First operation:	x1 = x1 XOR x2

x1:	1	0	0
x2:	1	1	1
New x1:	0	1	1
			 
Second operation	x2 = x2 XOR x1
			 
x1:	0	1	1
x2:	1	1	1
New x2:	1	0	0
			 
Third operation:	x1 = x1 XOR x2
			 
x1:	0	1	1
x2:	1	0	0
New x1:	1	1	1
</pre>
And voila, we now have x1 = 111 and x2 = 100.
and for each pair in array we do this because string chars are in essence binary values :)))
This method is also the quickest way to reverse a string.
Recursive way is almost 4-5 times slower.
	 * @param str : for instance (abcde)
	 * @return reverse result (edcba)
	 */
	public static String reverseStrUsingXOR(String str){
	    // convert the string to char array
	    char[] charArray = str.toCharArray();
	    int len = str.length() - 1;
	    /*
	    now this for is a bit unconventional at first glance because there
	    are 2 variables that we're changing values of: i++ and len--.
	    the order of them is irrelevant. so basicaly we're going with i from 
	    start to end of the array. with len we're shortening the array by one
	    each time. this is probably understandable.
	    */
	    for (int i = 0; i < len; i++, len--)
	    {
	        /*
	        now this is the tricky part people that should know about it don't.
	        look at the table below to see what's going on exactly here.
	        */
	        charArray[i] ^= charArray[len];
	        charArray[len] ^= charArray[i];
	        charArray[i] ^= charArray[len];
	    }
	    return new String(charArray);
	}
	
	public static void main(String[] args) {
		//System.out.println(StringUtils.reverseStrUsingXOR("abcde"));
	}
}
