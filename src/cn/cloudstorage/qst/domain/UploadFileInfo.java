package cn.cloudstorage.qst.domain;

import java.io.File;
import java.io.Serializable;

public class UploadFileInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String currentPath;		//当前所在路径
	private File file;				//文件
	private String fileFileName;	//文件名
	private String fileContentType;	//文件MIME类型
	//webupload自带的参数
	private String chunk; 			// 当前第几个分片
	private String chunks;			// 总分片个数
	private String size;			// 单个文件的总大小
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getChunk() {
		return chunk;
	}
	public void setChunk(String chunk) {
		this.chunk = chunk;
	}
	public String getChunks() {
		return chunks;
	}
	public void setChunks(String chunks) {
		this.chunks = chunks;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getCurrentPath() {
		return currentPath;
	}
	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}
	@Override
	public String toString() {
		return "UploadFileInfo [file=" + file + ", fileFileName="
				+ fileFileName + ", fileContentType=" + fileContentType
				+ ", chunk=" + chunk + ", chunks=" + chunks + ", size=" + size
				+ "]";
	}
}
