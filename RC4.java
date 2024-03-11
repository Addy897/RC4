package RC4;
import java.util.List;
/**
 * RC4 Encryption Algo
 */
public class RC4 {
    void Usage(){
        System.out.println("[-] -e [MSG] -k [key] for encode\n-d [Cipher] -k [key] for decode");
    }
    int[] keySechduling(int[] key ){
        int[] T= new int[256];
        for (int i = 0; i < T.length; i++) {
            T[i]=key[i%key.length];
        }
        int[] S = new int[256];
        for (int j = 0; j < S.length; j++) {
            S[j]=j;
        }
        int j=0;
        for (int i=0;i<S.length;i++) {
            j=(j+S[i]+T[i])%256;

            //swap
            int temp=S[i];
            S[i]=S[j];
            S[j]=temp;

            
        }
        return S;
    }
    
    int[] keyGen(int[] S,int[] key){
        int i=0;
	    int j=0;
	    for(int p=0;p<key.length;p++){
            i=(i+1)%256;
            j=(j+S[i])%256;
            int temp=S[i];
            S[i] = S[j];
            S[j]=temp;
            int t=(S[i]+S[j])%256;
            key[p]=S[t];
        }
        return key;
    }
    
    int[] encrypt(int[] key,int msg[]){
        int[] cipher=new int[key.length];
        for(int i=0;i<key.length;i++){
            cipher[i]=key[i]^msg[i];
        }
        return cipher;
    }
    
    public static void main(String[] args) {
        RC4 rc4=new RC4();
        if(args.length<3){
            rc4.Usage();
            return;
        }
        if(args[0].compareTo("-e")==0){
            int[] msg=new int[args[1].length()];
            int[] key=new int[args[1].length()];
            for (int i = 0; i < args[1].length(); i++) {
                msg[i]=(int)args[1].charAt(i);
            }
            if(args[2].compareTo("-k")==0){
                for (int i = 0; i < args[1].length(); i++) {
                    key[i]=(int)args[3].charAt(i%args[3].length());
                }
                int[] S=rc4.keySechduling(key);
                int[] newKey=rc4.keyGen(S,key);
                int[] cipher=rc4.encrypt(newKey,msg);
                for(int i:cipher){
                    System.out.printf("%d,", i);
                }
            }


        }
        
        if(args[0].compareTo("-d")==0){
            return;
        }
    }
}