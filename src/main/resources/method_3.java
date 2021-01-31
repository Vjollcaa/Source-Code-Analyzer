public static boolean containsLetter(String s){
    for(int i=0;i<s.length();i++){
        if (Character.isLetter(s.charAt(i))){
            return true;
        }
    }
    return false;
}