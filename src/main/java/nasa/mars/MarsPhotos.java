package nasa.mars;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarsPhotos {
	List<ImageInfo> photos;
	
	public List<ImageInfo> getImages() {
		return photos;
	}
	public void setPhotos(List<ImageInfo> images) {
		this.photos = images;
	}
	
	public String toString() {
		StringBuilder strBldr = new StringBuilder();
		
		strBldr.append("MarsPhotos : [{");
		for(ImageInfo ii : photos) {
			strBldr.append("'id':'").append(ii.getId()).append("',");
			strBldr.append("'sol':'").append(ii.getSol()).append("',");
			strBldr.append("'img_src':'").append(ii.getImg_src()).append("',");
			strBldr.append("'earth_date':'").append(ii.getEarth_date()).append("'");
		}
		strBldr.append("}]");
		
		return strBldr.toString();
	}

}
