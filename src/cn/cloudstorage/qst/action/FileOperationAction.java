package cn.cloudstorage.qst.action;

import java.io.File;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import cn.cloudstorage.qst.util.MenuoOperation;

@Component("fileOperationAction")
@Scope("prototype")
public class FileOperationAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String currentPath;    	//当前所在目录
	private String des_dir;			//目标目录名称
	private String target_file;		//目标文件名称
	private String oldName;			//老的文件名
	private String newName;			//新的文件名
	private String dirName;			//新建文件夹名称
	//private String message;		//返回的消息
	/**
	 * API list
	 * changeFileName(String currentPath,String oldName,String newName)		修改文件名
	 * copyFileOrDir(String currentPath, String des_dir, String target_file)复制文件
	 * deleteFile(String currentPath,String target_file)					删除文件
	 * cutFileOrDir(String currentPath, String des_dir,String target_file)	剪切文件
	 * createDir(String currentPath, String dirName)						创建文件夹
	 */
	//新建文件夹
	public String createDir(){
		try {
			String _currentPath=getRealPath()+File.separator+currentPath.replace("/", File.separator);
			boolean result=MenuoOperation.createDir(_currentPath, dirName);
			if(result){
				addActionMessage("创建文件夹成功");
				return SUCCESS;
			}
			addActionMessage("创建文件夹失败");
			return SUCCESS;
		} catch (Exception e) {
			addActionMessage("创建文件夹失败");
			return ERROR;
		}
	}
	//修改文件名称
	public String changeFileName(){
		try {
			String _currentPath=getRealPath()+File.separator+currentPath;
			boolean result=MenuoOperation.changeFileOrDirName(_currentPath, oldName, newName);
			if(result){
				addActionMessage("修改文件名成功");
				return SUCCESS;
			}
			setCurrentPath(this.currentPath);
			addActionMessage("修改文件名失败");
			return SUCCESS;
		} catch (Exception e) {
			addActionMessage("修改文件名失败");
			return ERROR;
		}
	}
	//拷贝文件
	public String copyFileOrDir(){
		try {
			String _currentPath=getRealPath()+File.separator+currentPath;
			String _des_dirPath=getRealPath()+File.separator+des_dir;
			//setCurrentPath(des_dir);//当前路径设置成目标路径----需要修改result的转发方式
			MenuoOperation.copyFileOrDir(_currentPath, _des_dirPath, target_file);
			addActionMessage("文件拷贝成功");
			return SUCCESS;
		} catch (Exception e) {
			addActionMessage("文件拷贝失败");
			return ERROR;
		}
	}
	//剪切文件
	public String cutFileOrDir(){
		try {
			String _currentPath=getRealPath()+File.separator+currentPath;
			String _des_dirPath=getRealPath()+File.separator+des_dir;
			//setCurrentPath(des_dir);//当前路径设置成目标路径----需要修改result的转发方式
			boolean result=MenuoOperation.cutFileOrDir(_currentPath, _des_dirPath, target_file);
			if(result){
				addActionMessage("文件剪切成功");
				return SUCCESS;
			}
			return ERROR;
		} catch (Exception e) {
			addActionMessage("文件剪切失败");
			return ERROR;
		}
	}
	//删除文件
	public String deleteFile(){
		try {
			String _currentPath=getRealPath()+File.separator+currentPath;
			boolean result=MenuoOperation.deleteFileOrDir(_currentPath, target_file);
			if(result){
				addActionMessage("文件删除成功");
				return SUCCESS;
			}
			return ERROR;
		} catch (Exception e) {
			addActionMessage("文件删除失败");
			return ERROR;
		}
	}
	//取得当前真实路径
	private String getRealPath(){
		String uploadpath= ServletActionContext.getServletContext().getRealPath("/upload");
		return uploadpath;
	}
	
	//setter--setter
	/*@JSON(serialize=false)*/
	public String getCurrentPath() {
		return currentPath;
	}
	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}
	@JSON(serialize=false)
	public String getDes_dir() {
		return des_dir;
	}
	public void setDes_dir(String des_dir) {
		this.des_dir = des_dir;
	}
	@JSON(serialize=false)
	public String getTarget_file() {
		return target_file;
	}
	public void setTarget_file(String target_file) {
		this.target_file = target_file;
	}
	@JSON(serialize=false)
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	@JSON(serialize=false)
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	@JSON(serialize=false)
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
}
