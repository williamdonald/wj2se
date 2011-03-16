import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
Maybe sometime later, use apache common execute framework or apache ant Task...
*/
public class ThreadInputStreamHandler implements Runnable {

    private InputStream inStream;;
    public ThreadInputStreamHandler(InputStream input){
        inStream=input;
   }
   @Override
    public void run() {
        BufferedReader bufferedReader = null;
       try
       {
         bufferedReader = new BufferedReader(new InputStreamReader(inStream));
         String line = null;
         while ((line = bufferedReader.readLine()) != null)
         {
     //      outputBuffer.append(line + "\n");
           System.out.println(line);
         }
       }
       catch (IOException ioe)
       {
         // TODO handle this better
         ioe.printStackTrace();
       }
       catch (Throwable t)
       {
         // TODO handle this better
         t.printStackTrace();
       }
       finally
       {
         try
         {
           bufferedReader.close();
         }
         catch (IOException e)
         {
           // ignore this one
         }
       }
       
   }

}

