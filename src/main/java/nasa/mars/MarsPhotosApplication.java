package nasa.mars;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.datetime.DateFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class MarsPhotosApplication {

	private static final Logger log = LoggerFactory.getLogger(MarsPhotosApplication.class);
	private DateFormatter dateFormatter = new DateFormatter();
	
	public static void main(String[] args) {
		MarsPhotosApplication app = new MarsPhotosApplication();
		app.run();
	}
	
	public void run() {
		String[] datesArray = {"15-Dec-17",
		                       "1-Mar-16",
		                       "22-Feb-15",
		                       "3-Jul-17",
		                       "5-May-19"};
		Date date;
		try {
			for(String dateStr : datesArray) {
				dateFormatter.setPattern("dd-MMM-yy");
				date = dateFormatter.parse(dateStr, Locale.US);
				dateFormatter.setPattern("yyyy-MM-dd");
				log.info(dateStr + "-->" + dateFormatter.print(date, Locale.US));
				getMarsImages(dateFormatter.print(date, Locale.US));
			}
		} catch(IOException ioe) {
			log.error(ioe.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public  void getMarsImages(String dateStr) throws IOException {
		StringBuilder urlString = new StringBuilder();
		
		urlString.append("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?api_key=DEMO_KEY&earth_date=").append(dateStr);
		
        ObjectMapper objectMapper = new ObjectMapper();

        MarsPhotos photos = objectMapper.readValue(new URL(urlString.toString()), MarsPhotos.class);
        
        
        //log.info(photos.toString());

        if(photos.getImages().size()>0) {
        	log.info("Reading image-->"+photos.getImages().get(0).getImg_src());
        	int last_slash_loc = photos.getImages().get(0).getImg_src().lastIndexOf("/");
        	String imgName = photos.getImages().get(0).getImg_src().substring(last_slash_loc+1);
        	        	
            URL url = new URL(photos.getImages().get(0).getImg_src());
            
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf)))
            {
               out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();

            File imgDir =  new File("C://temp//mars images");
            if(!imgDir.exists()) {
            	imgDir.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(imgDir.getAbsolutePath() + "//" + dateStr + "_" + imgName);
            fos.write(response);
            fos.close();
        }


	}
}
