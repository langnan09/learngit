package information;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;  
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;  
import java.net.Socket;  
  
public class members {  
  
    private Socket socket;  
    private BufferedReader br;  
    private BufferedWriter bw;
    private PrintWriter pw;  

    public Socket getSocket() {  
        return socket;  
    }  
  
    
    public BufferedReader getBr() {  
        return br;  
    }  
  
    
    public void setBr(BufferedReader br) {  
        this.br = br;  
    }  
  
    
    public PrintWriter getPw() {  
        return pw;  
    }  
  
    
    public void setPw(PrintWriter pw) {  
        this.pw = pw;  
    }  
  
    public members(final Socket socket) throws IOException {  
        
        this.socket = socket;  
        this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));  //InputStreamReader ： 是字节流与字符流之间的桥梁，能将字节流输出为字符流，并且能为字节流指定字符集，可输出一个个的字符；
        this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));   
        this.pw = new PrintWriter(bw,true); 
    }  
 
    
    
}  