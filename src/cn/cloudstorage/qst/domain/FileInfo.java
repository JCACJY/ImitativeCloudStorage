package cn.cloudstorage.qst.domain;

import java.io.Serializable;

public class FileInfo implements Serializable,Comparable<FileInfo>{
	/**
	 * 保存用户文件目录结构信息
	 */
	private static final long serialVersionUID = 1L;
	private String filename;		//文件名称
	private String filesize;		//文件大小
	private String filetype;		//文件类型
	private String filedate;		//文件修改日期
	private String fileurl;			//文件的url---以供下载
	private String filepathonserver;//文件在服务端绝对路径
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
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getFiledate() {
		return filedate;
	}
	public void setFiledate(String filedate) {
		this.filedate = filedate;
	}
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	public String getFilepathonserver() {
		return filepathonserver;
	}
	public void setFilepathonserver(String filepathonserver) {
		this.filepathonserver = filepathonserver;
	}
	@Override
	public String toString() {
		return "FileInfo [filename=" + filename + ", filesize=" + filesize
				+ ", filetype=" + filetype + ", filedate=" + filedate
				+ ", fileurl=" + fileurl + ", filepathonserver="
				+ filepathonserver + "]";
	}
	public int compareTo(FileInfo o) {
		if(o.getFiletype().compareTo(this.getFiletype())>0){
			return -1;
		}else if(o.getFiletype().compareTo(this.getFiletype())<0){
			return 1;
		}else{
			return this.filename.compareTo(o.filename);
		}
		
	}
}
