package file;

public class FileDTO {
	
	String getFileName;
	String fileRealName;
	public String getGetFileName() {
		return getFileName;
	}
	public void setGetFileName(String getFileName) {
		this.getFileName = getFileName;
	}
	public String getFileRealName() {
		return fileRealName;
	}
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	public FileDTO(String getFileName, String fileRealName) {
		super();
		this.getFileName = getFileName;
		this.fileRealName = fileRealName;
	}
	
	

}
