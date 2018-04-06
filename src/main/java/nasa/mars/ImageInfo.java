package nasa.mars;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageInfo {
	
	Integer id;
	Integer sol;
	String img_src;
	Date earth_date;
	
	@Override
	public String toString() {
		return "ImageInfo [id=" + id + ", sol=" + sol + ", img_src=" + img_src + ", earth_date=" + earth_date + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSol() {
		return sol;
	}
	public void setSol(Integer sol) {
		this.sol = sol;
	}
	public String getImg_src() {
		return img_src;
	}
	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}
	public Date getEarth_date() {
		return earth_date;
	}
	public void setEarth_date(Date earth_date) {
		this.earth_date = earth_date;
	}
	
	

}
