package ebsynergy.prog.fhbgds;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EBSynergy {
	private static File lib = new File("CodefhbgdsIO.jar");
	private static Proxy proxy = Proxy.NO_PROXY;
	
	public static HashMap<String, String[]> grades = new HashMap<String, String[]>();
	
	public EBSynergy(){
		boolean b = false;
		try{
			if(!lib.exists()){
				try{
					System.err.println("Missing library detected! Attempting to download...");
					downloadLibs();
					b = true;
				}catch (Exception e){
					System.err.println("Could not download required library \"" + lib.getName() + "\": " + e.getMessage());
					b = true;
					return;
				}
			}
			
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
			b = true;
		}
		initUI(b);
	}

	public static void main(String[] args){
		new EBSynergy();
	}
	
	public static void downloadLibs() throws Exception{
		URL url = new URL("https://github.com/fhbgds14531/SaveToFile/raw/master/CodefhbgdsIO.jar");
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection(proxy);
		con.setConnectTimeout(30000);
		con.setReadTimeout(10000);
		con.connect();
		InputStream stream = con.getInputStream();
		FileOutputStream out = new FileOutputStream(new File("CodefhbgdsIO.jar"));
		
        byte[] buffer = new byte[65536];
        try {
          int read = stream.read(buffer);
          System.out.println("Reading");
          while (read >= 1) {
            out.write(buffer, 0, read);
            read = stream.read(buffer);
          }
        } finally {
          stream.close();
          out.close();
          con.disconnect();
          System.out.println("Successfully downloaded all libraries.");
        }

	}
	
	public static void initUI(final boolean disableButtons){
		try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
                if(disableButtons){
                	UI.outputArea.append("An error occurred or a library\nhad to be downloaded, please restart.");
                	UI.disableAllButtons();
                }
            }
        });
	}

}
