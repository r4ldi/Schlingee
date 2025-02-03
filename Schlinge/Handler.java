import java.io.*;

public class Handler  
{
    
    String file;
    
    public Handler(String file){
        this.file = "scores/"+file+".txt";
    }
    
    public void write(int newScore) throws IOException{
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(Integer.toString(newScore));
        }
    }

    public int read() throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return Integer.parseInt(br.readLine());
        }
    }
}