/**
*
* @author joker 
* @date 创建时间：2018年5月18日 下午4:51:54
* 
*/
package com.tmall;

/**
* 
* @author joker 
* @date 创建时间：2018年5月18日 下午4:51:54
*/
public class T
{
	public static void main(String[] args)
	{
		String adminPrivateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALzwX7HwgAW8oGvG\r\n" + 
				"aJXSqApQFfHJ4vSEVeCUFHilD5M5ff4KMX59wopX7xff0NEQ7J8dUtxbX0zS94/q\r\n" + 
				"2Kct6/lONSQMiCVc+dKg62PN47E1q9HBqZ52bXrSRdjjwOOHSXRW9BhBdmcc3qeS\r\n" + 
				"U1Y9AYYfheEAwlLDvq2q7FtEXsM/AgMBAAECgYAmPf0Qlh5Div9pcRpgIOhUVgtr\r\n" + 
				"CjL+zP5Y24AdP0OcyAlBbyy/bpL+z3ecfHfWqTdLqXLJGVF7ykPDhIRfqkLVtTsE\r\n" + 
				"Xp2mY1zVDMh8n7fb3pTnzOOJ4a11L1tRevR5UGl03hP/YFmw241ZCJ2MfCD1Ud+v\r\n" + 
				"uBvNvl3a6Hats5jbWQJBAOCOWPF695dSI0Af5iz9SI8ivOlRGrrYQgLs/CDUEhXe\r\n" + 
				"dpC0fdDxgsCioYaEleeOtUE2FBsTRzaPFQp34aJCSisCQQDXZUMTI3gNp61W//7F\r\n" + 
				"2R6rWGIGgJByUVS9Mkl3hm6ndZS9S95dbmKvrdFmQBeDXxCACVQg69vcUbYb/smG\r\n" + 
				"gMU9AkEAruvTtw/NzE9MbEgs+gC3Q+CQfcMS6ldJXJQUdtIb4HMxstkWZOusqbhF\r\n" + 
				"0M55Iy6UXN7uXITw5Twh7Myjs8yljwJAH7+xT1TfEUQx7Kz3JLqIJsF0UXkJ0Y/j\r\n" + 
				"aa/L3G5YSR6vuedA84ydxz9bqjKwRJDyoay+VcvuMF9q9F1t3wGEEQJAXcFUnf3p\r\n" + 
				"rudb8BSZPlJVXV+c9dilIkdGxwGfloavYrlzJxxKWEjEsv7OYrN7v6K6jOmmmN0x\r\n" + 
				"bdBlTOOa3LK15g==";
		String adminPublicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbH6mMEQ2Ze3tz9Ktvj9UyGjfI\r\n" + 
				"2GYyZzrgs21HR2MPSuSwGl4G8YOx2pBKV1asaBgEhpgS4ugg62hk52t9ShoN9SUv\r\n" + 
				"HDFt43r7WpTZorxPkL1DTx3oGzs0u3pHu6wjjE4A1tMf0KPAeQNi78tG+rGyTXbU\r\n" + 
				"d7YxxYjU4Cb6zVgp7QIDAQAB";
		String payPrivateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJsfqYwRDZl7e3P0\r\n" + 
				"q2+P1TIaN8jYZjJnOuCzbUdHYw9K5LAaXgbxg7HakEpXVqxoGASGmBLi6CDraGTn\r\n" + 
				"a31KGg31JS8cMW3jevtalNmivE+QvUNPHegbOzS7eke7rCOMTgDW0x/Qo8B5A2Lv\r\n" + 
				"y0b6sbJNdtR3tjHFiNTgJvrNWCntAgMBAAECgYAdO2Q57uS/LZPgEBVWWqZ0YJhu\r\n" + 
				"ksj/apCf03AeZId4VEawIOfjKxQEKgnS2I6Owrbz82Eo/fV8bqAnk6yf2Zz7BT+0\r\n" + 
				"QHviG0i1n9vUMVBORlD2HSQy4gnBWekTL1LVtasJgVRWVSpmt0TDfPYXf4oBGzaO\r\n" + 
				"F76Jd6jqJWnyjAx7iQJBAMp25MQDJmqASoWDpdcThnfAlNyCimi0FquHw0UrR4ni\r\n" + 
				"vV8YfTY73eRi1YdV6ixyfZQbm8/pOHlawjlhI8BSETsCQQDEJDSzIr3kdix8+zqS\r\n" + 
				"Jb83w6shAxeWfUNhht7/b+HC+TsrKQsgtn2uDSfCjeVVM4sDaAvK0bdgR2hLuvAC\r\n" + 
				"I/73AkBRbmZua/HsjfLrJlryWVkRGzUER8DRgfVIQk+Ip3Kbg8W01M2vnX6OmC3X\r\n" + 
				"w/durxHRGxWdFulu7dEGcQqHFLr3AkEAtOxAwEx3pNlsyAxlRpITBDk1DUpw45vA\r\n" + 
				"H6Hs6Oom8VY8Qd+mKBKtc3MnDcsdk63ruMSsu/UelWzIw7I32YGimQJBAME+MaCD\r\n" + 
				"5Jmf2nVEFpfkkK0arWJm4b9UcMBRGGHxF1V8pImVKogIosa/6nFgcL+THZL6jeST\r\n" + 
				"xeyfQgWWMNlT/As=";
		String payPublicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbH6mMEQ2Ze3tz9Ktvj9UyGjfI\r\n" + 
				"2GYyZzrgs21HR2MPSuSwGl4G8YOx2pBKV1asaBgEhpgS4ugg62hk52t9ShoN9SUv\r\n" + 
				"HDFt43r7WpTZorxPkL1DTx3oGzs0u3pHu6wjjE4A1tMf0KPAeQNi78tG+rGyTXbU\r\n" + 
				"d7YxxYjU4Cb6zVgp7QIDAQAB";
		System.out.println(adminPrivateKey.equals(payPrivateKey));
		System.out.println(adminPublicKey.equals(payPublicKey));
	}
}
