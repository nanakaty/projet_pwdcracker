

public class PasswordHashing {

        public static void main(String[] args) { 
            System.out.println(doHashing("12345"));
        }

        public static String doHashing (String Password) {
        try {
            MessageDigest MessageDigest = MessageDigest.getInstance("MD5");

            MessageDigest.update(Password.getBytes());

            byte[] resultByteArray = MessageDigest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x" , b));

            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        } 
        return "";

}