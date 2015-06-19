public class EightRooks{
    public static String isCorrect(String[] board){
        String correct = "Correct";
        String incorrect = "InCorrect";
        byte mark = 0;
        if(board == null) return incorrect;
        int size = board.length;
        if(size != 8) return incorrect;
        for(int i = 0; i < size; i++){
            String str = board[i];
            if(str.length() != 8) return incorrect;
            else{
                int num = 0;
                int pos = 0;
                for(int j = 0; j < 8; j++){
                    char ch = str.charAt(j);
                    if(ch == 'R'){
                        num++;
                        pos = j;
                    }else if(ch == '.'){
                        continue;
                    }else{
                        return incorrect;
                    }
                }
                if(num == 1){
		    System.out.println("line" + i + ": " + pos);
                    mark |= (1 << pos);
		    System.out.println(mark);
                }else{
                    return incorrect;
                }
            }
        }
        if(mark == (byte)0xff){
            return correct;
        }else{
            return incorrect;
        }
    }

	public static void main(String[] args){
		System.out.println(isCorrect(args));
	}
}
