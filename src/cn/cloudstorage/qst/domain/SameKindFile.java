package cn.cloudstorage.qst.domain;

import java.io.Serializable;

public class SameKindFile implements Serializable{
	private static final long serialVersionUID = 1L;
	private String filename;		//文件名
	private String filesize;		//文件大小
	private String filedate;		//文件日期
	private String filetype;		//文件类型
	private String filepath;		//文件URL
	private String filepathonserver;//文件在服务端绝对路径
	public SameKindFile() {}
	public SameKindFile(String filename, String filesize,
		String filedate, String filepath) {
		this.filename = filename;
		this.filesize = filesize;
		this.filedate = filedate;
		this.filepath = filepath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public String getFiledate() {
		return filedate;
	}
	public void setFiledate(String filedate) {
		this.filedate = filedate;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFilepathonserver() {
		return filepathonserver;
	}
	public void setFilepathonserver(String filepathonserver) {
		this.filepathonserver = filepathonserver;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	@Override
	public String toString() {
		return "SameKindFile [filename=" + filename + ", filesize=" + filesize
				+ ", filedate=" + filedate + ", filetype=" + filetype
				+ ", filepath=" + filepath + ", filepathonserver="
				+ filepathonserver + "]";
	}
}
